package com.kiss.captcha.controller;

import com.kiss.captcha.client.Timer;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "Timer", description = "计时器操作接口")
public class TimerController implements Timer {
    @Override
    public void setTimer(String identifier, Integer expired) {

    }

    @Override
    public Boolean checkTimer(String identifier, Integer expired) {
        return null;
    }

    @Override
    public void clearTimer(String identifier) {

    }
}
