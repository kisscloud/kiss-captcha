package com.kiss.captcha.client;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.NoSuchAlgorithmException;

@RequestMapping
public interface Counter {

    @GetMapping("/counter/set")
    void setCount(String identifier, Integer times, Integer expired) throws NoSuchAlgorithmException;

    @DeleteMapping("/counter/clear")
    void clearCount(String identifier) throws NoSuchAlgorithmException;

    @GetMapping("/counter/get")
    Integer getCount(String identifier) throws NoSuchAlgorithmException;
}
