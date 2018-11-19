package com.kiss.captcha.client;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping
public interface Timer {

    @GetMapping("/timer/set")
    void setTimer(String identifier, Integer expired);

    @GetMapping("/timer/check")
    Boolean checkTimer(String identifier, Integer expired);

    @DeleteMapping("/timer/clear")
    void clearTimer(String identifier);

}
