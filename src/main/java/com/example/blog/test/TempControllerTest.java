package com.example.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempControllerTest {
    @GetMapping("/temp/home")
    public String tempHome() {
        System.out.println("tempHome()");
        return "/home.html";
    }

    @GetMapping("/temp/img")
    public String tempImg() {
        return "/b.png";
    }

    @GetMapping("/temp/jsp")
    public String tempJsp() {
        //prefix: /WEB-INF/views/
        //suffix: .jsp

        return "/test";
    }
}
