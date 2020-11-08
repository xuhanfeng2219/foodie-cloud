package com.imooc.user.stream;

import com.imooc.springcloud.Account;
import com.imooc.springcloud.AuthResponse;
import com.imooc.springcloud.AuthService;
import com.imooc.springcloud.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;

/**
 * @author 2349
 */
@EnableBinding(
        value = {
                ForceLogoutTopic.class
        }
)
@Slf4j
public class UserMessageConsumer {

    @Autowired
    private AuthService authService;

    @StreamListener(ForceLogoutTopic.INPUT)
    public void consumeLogoutMessage(String payload){
        log.info("Force logout user , userId = {}", payload);

        Account account = Account.builder().userId(payload).isSkipVerification(true).build();

        AuthResponse response = authService.delete(account);

        if (!ErrorCode.SUCCESS.equals(response.getCode())) {
            log.error("Error occurred when deleting user session, userId = {}", response.getAccount().getUserId());

            throw new RuntimeException("Cannot delete user session");
        }

    }

    /**
     * 1、重试、requeue
     * 2、死信队列、服务降级
     * @param message message
     */
    @ServiceActivator(inputChannel = "force-logout-topic.force-logout-group.errors")
    public void fallback(Message<?> message) {
        
        log.error("force logout failed!");

        // TODO: 2020/10/29

    }
}
