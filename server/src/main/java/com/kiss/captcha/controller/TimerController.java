package com.kiss.captcha.controller;

import com.kiss.captcha.client.Timer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;

import static org.apache.commons.codec.digest.MessageDigestAlgorithms.MD5;

@RestController
@Api(tags = "Timer", description = "计时器操作接口")
public class TimerController implements Timer {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    @ApiOperation(value = "设置计时器")
    public void setTimer(String identifier, Integer expired) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(MD5);
        md.update(identifier.getBytes());
        String key = new BigInteger(1, md.digest()).toString(16);
        stringRedisTemplate.opsForValue().set(key, "", expired, TimeUnit.MILLISECONDS);
    }

    @Override
    @ApiOperation(value = "检查计时器")
    public Boolean checkTimer(String identifier, Integer expired) throws NoSuchAlgorithmException {

        MessageDigest md = MessageDigest.getInstance(MD5);
        md.update(identifier.getBytes());
        String key = new BigInteger(1, md.digest()).toString(16);
        String value = stringRedisTemplate.opsForValue().get(key);
        return value != null;
    }

    @Override
    @ApiOperation(value = "清除计时器")
    public void clearTimer(String identifier) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(MD5);
        md.update(identifier.getBytes());
        String key = new BigInteger(1, md.digest()).toString(16);
        stringRedisTemplate.delete(key);
    }

    @Override
    @ApiOperation(value = "获取当天剩余的毫秒数")
    public Integer getTodayLeftMilliSeconds() {
        return null;
    }
}
