package com.example.blog.test;

import org.springframework.web.bind.annotation.*;

//User request -> response (File)
//@Controller
//User request -> response (Data)
@RestController
public class HttpControllerTest {

    private static final String TAG = "HttpController Test: ";

    //localhost:8000/blog/http/lombok
    @GetMapping("/http/lombok")
    public String lombokTest() {
        Member m = new Member(1, "1234", "sam", "sam@naver.com");
//        Member m = Member.builder().username("sam").password("1324").email("sam@naver.com");
        System.out.println(TAG + "Getter : " + m.getId());
        m.setId(5000);
        System.out.println(TAG + "Setter : " + m.getId());
        return "lombok test 완료";
    }
    //http://localhost:8080/http/get (select)
    @GetMapping("/http/get")
    public String getTest(Member m) {


        return "GET 요청 : " + m.getId() + ", " + m.getUsername() + ", " + m.getEmail() + ", " + m.getPassword();
    }
    //http://localhost:8080/http/post (insert)
    @PostMapping("/http/post") //text/plain, application/json
    public String postTest(@RequestBody String text) {
        return "POST 요청 : " + text;
    }
    //http://localhost:8080/http/put (update)
    @PutMapping ("/http/put")
    public String putTest() {
        return "PUT 요청";
    }
    //http://localhost:8080/http/delete (delete)
    @DeleteMapping("/http/delete")
    public String deleteTest() {
        return "DELETE 요청";
    }
}
