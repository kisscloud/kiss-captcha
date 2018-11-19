package com.kiss.captcha.client;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import output.ResultOutput;

@RequestMapping
public interface TwoFactorCode {

    @GetMapping("/twoFactorCode/generate")
    ResultOutput GenerateTwoFactorCode(String issuer, String label, String secret);

    @GetMapping("/twoFactorCode/validate")
    ResultOutput ValidateTwoFactorCode(String secret, Integer code, Boolean once);

}
