package com.kiss.captcha.client;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.NoSuchAlgorithmException;

@RequestMapping
public interface Counter {

    @GetMapping("/counter/set")
    void setCount(@RequestParam("identifier") String identifier, @RequestParam("times") Integer times, @RequestParam("expired") Integer expired);

    @DeleteMapping("/counter/clear")
    void clearCount(String identifier);

    @GetMapping("/counter/get")
    Integer getCount(String identifier);
}
