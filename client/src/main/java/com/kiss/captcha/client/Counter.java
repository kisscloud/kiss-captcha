package com.kiss.captcha.client;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping
public interface Counter {

    @GetMapping("/counter/set")
    void setCount(String identifier, Integer times, Integer expired);

    @DeleteMapping("/counter/clear")
    void clearCount(String identifier);

    @GetMapping("/counter/getTodayLeftSeconds")
    Integer getTodayLeftSeconds();
}
