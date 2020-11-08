package com.imooc.springcloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author fenggege
 */
public class JwtServiceImpl implements AuthService {

    @Autowired
    private RedisTemplate<String, Account> redisTemplate;

    @Override
    public AuthResponse login(String username, String pwd) {
        return null;
    }

    @Override
    public AuthResponse verify(String username, String token) {
        return null;
    }

    @Override
    public AuthResponse refresh(String username, String token) {
        return null;
    }

    @Override
    public AuthResponse delete(Account account) {
        AuthResponse token = verify(account.getUsername(), account.getToken());
        AuthResponse response = new AuthResponse();
        if (ErrorCode.SUCCESS.equals(token.getCode())) {
            redisTemplate.delete(account.getRefreshToken());
            redisTemplate.delete(account.getUserId());
            response.setCode(ErrorCode.SUCCESS);
        } else {
            response.setCode(ErrorCode.USER_NOT_FOUND);
        }
        return response;
    }
}
