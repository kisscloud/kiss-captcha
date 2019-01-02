package com.kiss.captcha.controller;

import com.kiss.captcha.client.NumberCode;
import com.kiss.captcha.output.NumberCodeOutput;
import com.kiss.captcha.utils.CryptUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RestController;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@Api(tags = "NumberCode", description = "数字验证码操作接口")
public class NumberCodeController implements NumberCode {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 生成数字验证码
     *
     * @param scene   使用场景，可使用客户端唯一ID
     * @param len     验证码长度，最少4位
     * @param expired 验证码有效期，单位毫秒
     */
    @Override
    @ApiOperation(value = "生成数字验证码")
    public NumberCodeOutput GenerateNumberCode(String scene, Integer len, Integer expired) {

        if (len < 4) {
            len = 4;
        }

        String code = "";
        for (Integer i = 0; i < len; i++) {
            code = String.format("%s%d", code, new Random().nextInt(9));
        }

        String uuid = UUID.randomUUID().toString().replace("-", "");

        NumberCodeOutput numberCodeOutput = new NumberCodeOutput();
        numberCodeOutput.setCode(String.valueOf(code));
        numberCodeOutput.setToken(CryptUtil.md5(scene + uuid));

        stringRedisTemplate.opsForValue().set(numberCodeOutput.getToken(), numberCodeOutput.getCode(), expired, TimeUnit.MILLISECONDS);


        return numberCodeOutput;
    }

    /**
     * 校验数字验证码
     *
     * @param token 验证码令牌
     * @param code  验证码
     */
    @Override
    @ApiOperation(value = "校验数字验证码")
    public Boolean ValidateNumberCode(String token, String code) {

        String redisCode = stringRedisTemplate.opsForValue().get(token);

        return redisCode != null && redisCode.equals(code);
    }

    /**
     * 清除数字验证码
     *
     * @param token 验证码令牌
     */
    @Override
    @ApiOperation(value = "清除数字验证码")
    public void ClearNumberCode(String token) {
        stringRedisTemplate.delete(token);
    }

}
