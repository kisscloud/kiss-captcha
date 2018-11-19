package com.kiss.captcha.client;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import output.ResultOutput;

@RequestMapping
public interface NumberCode {

    @GetMapping("/numberCode/generate")
    ResultOutput GenerateNumberCode(String scene, Integer len, Integer expired);

    @GetMapping("/numberCode/validate")
    ResultOutput ValidateNumberCode(String scene, String token, String code);

    @DeleteMapping("/numberCode/clear")
    ResultOutput ClearNumberCode(String token);
}
