package com.imooc.springcloud;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 2349
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Account {

    private String username;

    private String token;

    private String refreshToken;
}
