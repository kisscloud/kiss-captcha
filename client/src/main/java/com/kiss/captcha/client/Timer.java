package com.kiss.captcha.client;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.NoSuchAlgorithmException;
import java.text.ParseException;

@RequestMapping
public interface Timer {

    @GetMapping("/timer/set")
    void setTimer(String identifier, Integer expired) throws NoSuchAlgorithmException;

    @GetMapping("/timer/check")
    Boolean checkTimer(String identifier, Integer expired) throws NoSuchAlgorithmException;

    @DeleteMapping("/timer/clear")
    void clearTimer(String identifier) throws NoSuchAlgorithmException;

    @GetMapping("/counter/getTodayLeftMilliSeconds")
    Integer getTodayLeftMilliSeconds() throws ParseException;
}
