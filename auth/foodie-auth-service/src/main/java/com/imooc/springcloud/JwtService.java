package com.imooc.springcloud;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.JWTVerifier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author 2349
 */
@Slf4j
@Service
public class JwtService {

    private static final String KEY = "changeIT";

    private static final String ISSUE = "xu";

    private static final Long TOKEN_EXPIRE_TIME = 60000L;

    private static final String USERNAME = "username";

    public String token(Account account) {

        Algorithm algorithm = Algorithm.HMAC256(KEY);

        String token = JWT.create()
                .withIssuer(ISSUE)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + TOKEN_EXPIRE_TIME))
                .withClaim(USERNAME, account.getUsername())
                .sign(algorithm);
        log.info("jwt generated username={}", account.getUsername());
        return token;
    }

    public boolean verify(String username, String token) {
        log.info("verify jwt - username={}", username);

        try {
            Algorithm algorithm = Algorithm.HMAC256(KEY);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(ISSUE)
                    .withClaim(USERNAME, username)
                    .build();
            verifier.verify(token);
            return true;
        } catch (Exception e) {
            log.error("auth fail ", e);
        }
        return false;
    }
}
