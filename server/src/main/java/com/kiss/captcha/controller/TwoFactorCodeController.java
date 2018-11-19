package com.kiss.captcha.controller;

import com.kiss.captcha.client.TwoFactorCode;
import org.springframework.web.bind.annotation.RestController;
import output.ResultOutput;

@RestController
public class TwoFactorCodeController implements TwoFactorCode {

    @Override
    public ResultOutput GenerateTwoFactorCode(String label, String issuer, String secret) {
        return null;
    }

    @Override
    public ResultOutput ValidateTwoFactorCode(String secret, String code, Boolean once) {
        return null;
    }

}
