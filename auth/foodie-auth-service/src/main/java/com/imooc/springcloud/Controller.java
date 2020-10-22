package com.imooc.springcloud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.UUID;

/**
 * @author 2349
 */
@RestController
@Slf4j
public class Controller {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private RedisTemplate<String, Account> redisTemplate;

    @PostMapping("/login")
    @ResponseBody
    public AuthResponse login(@RequestParam("username") String username,
                              @RequestParam("pwd") String pwd) {
        Account account = Account.builder().username(username).build();
        // TODO: 2020/10/22 验证username+password
        String token = jwtService.token(account);
        account.setToken(token);
        account.setRefreshToken(UUID.randomUUID().toString());

        redisTemplate.opsForValue().set(account.getRefreshToken(), account);
        return AuthResponse.builder()
                .account(account)
                .code(ErrorCode.SUCCESS)
                .build();
    }

    @PostMapping("/refreshToken")
    @ResponseBody
    public AuthResponse refreshToken(@RequestParam("refreshToken") String refreshToken) {
        Account account = redisTemplate.opsForValue().get(refreshToken);
        if (Objects.isNull(account)) {
            return AuthResponse.builder()
                    .code(ErrorCode.USER_NOT_FOUND)
                    .build();
        }

        String token = jwtService.token(account);
        account.setToken(token);
        account.setRefreshToken(UUID.randomUUID().toString());

        redisTemplate.delete(refreshToken);
        redisTemplate.opsForValue().set(account.getRefreshToken(), account);

        return AuthResponse.builder()
                .account(account)
                .code(ErrorCode.SUCCESS)
                .build();
    }

    @GetMapping("/verify")
    public AuthResponse verify(@RequestParam String username, @RequestParam String token) {
        boolean verify = jwtService.verify(username, token);

        return AuthResponse
                .builder()
                .code(verify ? ErrorCode.SUCCESS : ErrorCode.USER_NOT_FOUND)
                .build();
    }
}
