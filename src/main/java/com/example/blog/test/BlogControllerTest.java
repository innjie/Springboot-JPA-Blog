package com.example.blog.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController  //스프링이 특정 어노테이션의 파일을 new(IoC) 하여 컨테이너에서 관리
public class BlogControllerTest {
    @GetMapping("/test/hello")
    public String hello() {
        return "<h1>hello spring boot</h1>";
    }
}
