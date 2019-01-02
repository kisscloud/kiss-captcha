package com.kiss.captcha.output;

import lombok.Data;

@Data
public class ImageCodeOutput {

    private String token;

    private String image;

}
