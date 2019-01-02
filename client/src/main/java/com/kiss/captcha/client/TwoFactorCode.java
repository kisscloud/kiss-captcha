package com.kiss.captcha.client;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import output.ResultOutput;

@RequestMapping
public interface TwoFactorCode {

    @GetMapping("/twoFactorCode/generate")
    ResultOutput GenerateTwoFactorCode(@RequestParam("issuer") String issuer, @RequestParam("label") String label, @RequestParam("secret") String secret);

    @GetMapping("/twoFactorCode/validate")
    ResultOutput ValidateTwoFactorCode(@RequestParam("secret") String secret, @RequestParam("code") Integer code, @RequestParam("once") Boolean once);

}
