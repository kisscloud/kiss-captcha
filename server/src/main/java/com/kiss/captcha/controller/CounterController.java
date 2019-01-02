package com.kiss.captcha.controller;

import com.kiss.captcha.client.Counter;
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
@Api(tags = "Counter", description = "计数器操作接口")
public class CounterController implements Counter {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 设置计数器
     *
     * @param identifier 计数唯一标识符
     * @param times      计数次数
     * @param expired    过期时间，传 null 为永不过期
     */
    @Override
    @ApiOperation(value = "设置计数器")
    public void setCount(String identifier, Integer times, Integer expired) throws NoSuchAlgorithmException {

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(identifier.getBytes());
        String key = new BigInteger(1, md.digest()).toString(16);
        if (expired == null) {
            stringRedisTemplate.opsForValue().set(key, String.valueOf(times));
        } else {
            stringRedisTemplate.opsForValue().set(key, String.valueOf(times), expired, TimeUnit.MILLISECONDS);
        }
    }


    /**
     * 清除计数器
     *
     * @param identifier 计数唯一标识符
     */
    @Override
    @ApiOperation(value = "清除计数器")
    public void clearCount(String identifier) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(MD5);
        md.update(identifier.getBytes());
        String key = new BigInteger(1, md.digest()).toString(16);
        stringRedisTemplate.delete(key);
    }

    /**
     * 获取计数器
     * <p>
     * 如果对应计数器不存在，则返回 null
     *
     * @param identifier 计数唯一标识符
     */
    @Override
    @ApiOperation(value = "获取计数器的值")
    public Integer getCount(String identifier) throws NoSuchAlgorithmException {

        MessageDigest md = MessageDigest.getInstance(MD5);
        md.update(identifier.getBytes());
        String key = new BigInteger(1, md.digest()).toString(16);
        String value = stringRedisTemplate.opsForValue().get(key);
        
        return value == null ? null : Integer.valueOf(value);
    }

}
