package com.kiss.captcha.client;

import com.kiss.captcha.output.ImageCodeOutput;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping
public interface ImageCode {

    @GetMapping("/imageCode/generate")
    ImageCodeOutput GenerateImageCode(@RequestParam("len") Integer len, @RequestParam("expired") Integer expired, @RequestParam("width") Integer width, @RequestParam("height") Integer height);

    @GetMapping("/imageCode/validate")
    Boolean ValidateImageCode(@RequestParam("token") String token, @RequestParam("code") String code);
}
