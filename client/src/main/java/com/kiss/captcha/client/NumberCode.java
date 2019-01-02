package com.kiss.captcha.client;

import com.kiss.captcha.output.NumberCodeOutput;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.NoSuchAlgorithmException;

@RequestMapping
public interface NumberCode {

    @GetMapping("/numberCode/generate")
    NumberCodeOutput GenerateNumberCode(String scene, Integer len, Integer expired) throws NoSuchAlgorithmException;

    @GetMapping("/numberCode/validate")
    Boolean ValidateNumberCode(String token, String code);

    @DeleteMapping("/numberCode/clear")
    void ClearNumberCode(String token);
}
