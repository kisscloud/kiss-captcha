package com.kiss.captcha.controller;

import com.kiss.captcha.client.Timer;
import com.kiss.captcha.utils.CryptUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RestController;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@RestController
@Api(tags = "Timer", description = "计时器操作接口")
public class TimerController implements Timer {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    @ApiOperation(value = "设置计时器")
    public void setTimer(String identifier, Integer expired) {
        stringRedisTemplate.opsForValue().set(CryptUtil.md5(identifier), "", expired, TimeUnit.MILLISECONDS);
    }

    @Override
    @ApiOperation(value = "检查计时器")
    public Boolean checkTimer(String identifier, Integer expired)  {
        String value = stringRedisTemplate.opsForValue().get(CryptUtil.md5(identifier));
        return value != null;
    }

    @Override
    @ApiOperation(value = "清除计时器")
    public void clearTimer(String identifier) {
        String key = CryptUtil.md5(identifier);
        stringRedisTemplate.delete(key);
    }

    @Override
    @ApiOperation(value = "获取当天剩余的毫秒数")
    public Integer getTodayLeftMilliSeconds() throws ParseException {

        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DAY_OF_MONTH, 1);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String str = simpleDateFormat.format(c.getTime());

        Long tomorrow = simpleDateFormat.parse(str).getTime();
        Long now = new Date().getTime();

        return (int) (tomorrow - now);
    }
}
