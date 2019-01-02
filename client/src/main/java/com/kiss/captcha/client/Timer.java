package com.kiss.captcha.client;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.NoSuchAlgorithmException;
import java.text.ParseException;

@RequestMapping
public interface Timer {

    @GetMapping("/timer/set")
    void setTimer(@RequestParam("identifier") String identifier, @RequestParam("expired") Integer expired);

    @GetMapping("/timer/check")
    Boolean checkTimer(String identifier);

    @DeleteMapping("/timer/clear")
    void clearTimer(String identifier);

    @GetMapping("/counter/getTodayLeftMilliSeconds")
    Integer getTodayLeftMilliSeconds();
}
