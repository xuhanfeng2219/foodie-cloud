package com.imooc.springcloud;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author 2349
 */
@FeignClient("foodie-auth-service")
@RequestMapping("auth-service")
public interface AuthService {

    @PostMapping("/login")
    @ResponseBody
    public AuthResponse login(@RequestParam("username") String username,
                              @RequestParam("pwd") String pwd);

    @GetMapping("/verify")
    public AuthResponse verify(@RequestParam("username") String username,
                               @RequestParam("token") String token);

    @PostMapping("/refresh")
    @ResponseBody
    public AuthResponse refresh(@RequestParam("username") String username,
                                @RequestParam("token") String token);

    @DeleteMapping("/delete")
    public AuthResponse delete(@RequestBody Account account);

}
