package com.kiss.captcha.controller;

import com.kiss.captcha.client.TwoFactorCode;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import output.ResultOutput;
import utils.ResultOutputUtil;

@RestController
@Api(tags = "TwoFactor", description = "谷歌验证码")
public class TwoFactorCodeController implements TwoFactorCode {

    private String getOtpAuthTotpURL(String issuer, String label, String secret) {

        if (secret == null || secret.isEmpty()) {
            GoogleAuthenticator gAuth = new GoogleAuthenticator();
            secret = gAuth.createCredentials().getKey();
        }

        URIBuilder uri = (new URIBuilder()).setScheme("otpauth").setHost("totp").setPath("/" + label).setParameter("secret", secret);
        if (issuer != null) {
            if (issuer.contains(":")) {
                throw new IllegalArgumentException("Issuer cannot contain the ':' character.");
            }

            uri.setParameter("issuer", issuer);
        }

        return uri.toString();
    }

    @Override
    @ApiOperation(value = "生成谷歌验证码")
    public ResultOutput GenerateTwoFactorCode(@RequestParam("issuer") String issuer, @RequestParam("label") String label, String secret) {

        return ResultOutputUtil.success(getOtpAuthTotpURL(issuer, label, secret));
    }

    @Override
    @ApiOperation(value = "校验谷歌验证码", notes = "验证码有效期为60S，两个校验周期")
    public ResultOutput ValidateTwoFactorCode(@RequestParam("secret") String secret, @RequestParam("code") Integer code, Boolean once) {

        GoogleAuthenticator gAuth = new GoogleAuthenticator();
        boolean isCodeValid = gAuth.authorize(secret, code);
        return ResultOutputUtil.success(isCodeValid);
    }

}
