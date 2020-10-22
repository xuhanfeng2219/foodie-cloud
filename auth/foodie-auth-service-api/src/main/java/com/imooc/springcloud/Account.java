package com.imooc.springcloud;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 2349
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Account implements Serializable {

    private String username;

    private String token;

    private String refreshToken;
}
