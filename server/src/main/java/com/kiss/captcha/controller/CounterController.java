package com.kiss.captcha.controller;

import com.kiss.captcha.client.Counter;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "Counter", description = "计数器操作接口")
public class CounterController implements Counter {
    @Override
    public void setCount(String identifier, Integer times, Integer expired) {

    }

    @Override
    public void clearCount(String identifier) {

    }

    @Override
    public Integer getTodayLeftSeconds() {
        return null;
    }
}
