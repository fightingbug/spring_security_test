package com.example.spring_security_04web_verifycode.controller;

import com.google.code.kaptcha.Producer;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
public class KaptchaController {

    private Producer producer;

    @Autowired
    public KaptchaController(Producer producer){
        this.producer = producer;
    }

    @RequestMapping("/vc.jpg")
    public void verifyCode(HttpServletResponse response, HttpSession session) throws IOException {
        //生成验证码
        String verifyCode = producer.createText();
        //保存到session中
        session.setAttribute("kaptcha",verifyCode);
        //生成图片
        BufferedImage bi = producer.createImage(verifyCode);
        //响应图片
        response.setContentType("image/png");
        ServletOutputStream os = response.getOutputStream();
        ImageIO.write(bi,"jpg",os);

    }
}
