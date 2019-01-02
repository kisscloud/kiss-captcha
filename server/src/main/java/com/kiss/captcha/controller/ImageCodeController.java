package com.kiss.captcha.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import com.kiss.captcha.client.ImageCode;
import com.kiss.captcha.output.ImageCodeOutput;
import com.kiss.captcha.utils.CryptUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

@RestController
@Api(tags = "ImageCode", description = "图形验证码操作接口")
public class ImageCodeController implements ImageCode {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public ImageCodeOutput GenerateImageCode(Integer len, Integer expired, Integer width, Integer height) {

        if (len == null) {
            len = 4;
        }

        if (expired == null) {
            expired = 15000;
        }

        if (width == null) {
            width = 130;
        }

        if (height == null) {
            height = 50;
        }

        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        Properties properties = new Properties();
        //边框样式
        properties.setProperty("kaptcha.border", "no");
        properties.setProperty("kaptcha.border.color", "105,179,90");
        //验证码颜色
        properties.setProperty("kaptcha.textproducer.font.color", "88,88,88");
        //图片长宽
        properties.setProperty("kaptcha.image.width", String.valueOf(width));
        properties.setProperty("kaptcha.image.height", String.valueOf(height));
        //字符大小
        properties.setProperty("kaptcha.textproducer.font.size", "30");
        // properties.setProperty("kaptcha.session.key", "code");
        //设置字体个数
        properties.setProperty("kaptcha.textproducer.char.length", String.valueOf(len));
        properties.setProperty("kaptcha.textproducer.font.names", "宋体,楷体,微软雅黑");
        Config config = new Config(properties);
        defaultKaptcha.setConfig(config);

        String code = defaultKaptcha.createText();
        BufferedImage bufferedImage = defaultKaptcha.createImage(code);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            ImageIO.write(bufferedImage, "jpg", outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String base64 = Base64.getEncoder().encodeToString(outputStream.toByteArray());
        String captchaBase64 = "data:image/jpeg;base64," + base64.replaceAll("\r\n", "");
        String token = CryptUtil.uuid();

        stringRedisTemplate.opsForValue().set(token, code, expired, TimeUnit.MILLISECONDS);

        ImageCodeOutput imageCodeOutput = new ImageCodeOutput();
        imageCodeOutput.setImage(captchaBase64);
        imageCodeOutput.setToken(CryptUtil.uuid());

        return imageCodeOutput;
    }

    @Override
    public Boolean ValidateImageCode(String token, String code) {

        String redisCode = stringRedisTemplate.opsForValue().get(token);

        return redisCode != null && redisCode.equals(code);
    }

}
