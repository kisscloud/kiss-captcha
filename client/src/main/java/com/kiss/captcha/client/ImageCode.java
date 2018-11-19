package com.kiss.captcha.client;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import output.ResultOutput;

@RequestMapping
public interface ImageCode {

    @GetMapping("/imageCode/generate")
    ResultOutput GenerateImageCode(Integer len, Integer expired, Integer width, Integer height);

    @GetMapping("/imageCode/validate")
    ResultOutput ValidateImageCode(String token, String code);
}
