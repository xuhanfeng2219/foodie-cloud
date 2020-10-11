package com.imooc.user.service.center;

import com.imooc.user.pojo.Users;
import com.imooc.user.pojo.bo.center.CenterUserBO;
import org.springframework.web.bind.annotation.*;

@RequestMapping("center-user-api")
public interface CenterUserService {

    /**
     * 根据用户id查询用户信息
     * @param userId
     * @return
     */
    @GetMapping("queryUserInfo")
    public Users queryUserInfo(@RequestParam("userId") String userId);

    /**
     * 修改用户信息
     * @param userId
     * @param centerUserBO
     */
    @PostMapping("updateUserInfo/{userId}")
    public Users updateUserInfo(@PathVariable("userId") String userId,@RequestBody CenterUserBO centerUserBO);

    /**
     * 用户头像更新
     * @param userId
     * @param faceUrl
     * @return
     */
    @PostMapping("updateUserFace")
    public Users updateUserFace(@RequestParam("userId") String userId,@RequestParam("faceUrl") String faceUrl);
}
