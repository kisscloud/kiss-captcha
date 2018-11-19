package com.kiss.captcha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class KissCaptchaCommonApplication {

    public static void main(String[] args) {
        SpringApplication.run(KissCaptchaCommonApplication.class);
    }

}
