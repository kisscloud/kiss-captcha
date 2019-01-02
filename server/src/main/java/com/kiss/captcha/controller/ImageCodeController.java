package com.kiss.captcha.controller;

import com.kiss.captcha.client.ImageCode;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RestController;
import output.ResultOutput;

@RestController
@Api(tags = "ImageCode", description = "图形验证码操作接口")
public class ImageCodeController implements ImageCode {

    @Override
    public ResultOutput GenerateImageCode(Integer len, Integer expired, Integer width, Integer height) {
        return null;
    }

    @Override
    public ResultOutput ValidateImageCode(String token, String code) {
        return null;
    }

}
