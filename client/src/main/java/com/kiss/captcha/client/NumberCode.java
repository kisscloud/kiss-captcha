package com.kiss.captcha.client;

import com.kiss.captcha.output.NumberCodeOutput;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping
public interface NumberCode {

    @GetMapping("/numberCode/generate")
    NumberCodeOutput GenerateNumberCode(@RequestParam("scene") String scene, @RequestParam("len") Integer len, @RequestParam("expired") Integer expired);

    @GetMapping("/numberCode/validate")
    Boolean ValidateNumberCode(@RequestParam("token") String token, @RequestParam("code") String code);

    @DeleteMapping("/numberCode/clear")
    void ClearNumberCode(String token);
}
