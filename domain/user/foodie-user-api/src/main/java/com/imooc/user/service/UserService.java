package com.imooc.user.service;

import com.imooc.user.pojo.Users;
import com.imooc.user.pojo.bo.UserBO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient("foodie-user-service")
@RequestMapping("user-api")
public interface UserService {

    /**
     * 判断用户名是否存在
     */
    @GetMapping("queryUsernameIsExist")
    public boolean queryUsernameIsExist(@RequestParam("username") String username);

    /**
     * 判断用户名是否存在
     */
    @PostMapping("createUser")
    public Users createUser(@RequestBody UserBO userBO);

    /**
     * 检索用户名和密码是否匹配，用于登录
     */
    @GetMapping("queryUserForLogin")
    public Users queryUserForLogin(@RequestParam(value = "username", required = false) String username
            , @RequestParam(value = "password", required = false) String password);
}
