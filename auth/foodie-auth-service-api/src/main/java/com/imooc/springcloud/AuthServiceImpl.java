package com.imooc.springcloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author 2349
 */
public class AuthServiceImpl implements AuthService {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private RedisTemplate<String, Account> redisTemplate;


    @Override
    public AuthResponse login(String username, String pwd) {
        return null;
    }

    @Override
    public AuthResponse verify(String username, String token) {
        // TODO: 2020/10/29 检查redis的token是否生效
        boolean success = jwtService.verify(username, token);
        return AuthResponse.builder()
                .code(success ? ErrorCode.SUCCESS : ErrorCode.USER_NOT_FOUND)
                .build();
    }

    @Override
    public AuthResponse refresh(String username, String token) {
        return null;
    }

    private static final String USER_TOKEN = "token";

    @Override
    public AuthResponse delete(Account account) {
        AuthResponse resp = new AuthResponse();
        resp.setCode(ErrorCode.SUCCESS);
        if (account.isSkipVerification()) {
            redisTemplate.delete(USER_TOKEN + account.getUserId());
        } else {
            AuthResponse verify = verify(account.getUsername(), account.getToken());
            if (ErrorCode.SUCCESS.equals(verify.getCode())) {
                redisTemplate.delete(USER_TOKEN + account.getUserId());
                redisTemplate.delete(account.getRefreshToken());
            } else {
                resp.setCode(ErrorCode.USER_NOT_FOUND);
            }
        }
        return resp;
    }
}
