package com.kiss.captcha.controller;

import com.kiss.captcha.client.Counter;
import com.kiss.captcha.utils.CryptUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RestController;
import java.util.concurrent.TimeUnit;


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
    public void setCount(String identifier, Integer times, Integer expired) {

        String key = CryptUtil.md5(identifier);
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
    public void clearCount(String identifier) {
        stringRedisTemplate.delete(CryptUtil.md5(identifier));
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
    public Integer getCount(String identifier) {

        String value = stringRedisTemplate.opsForValue().get(CryptUtil.md5(identifier));

        return value == null ? null : Integer.valueOf(value);
    }

}
