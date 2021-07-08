package com.example.blog.test;

import com.example.blog.model.RoleType;
import com.example.blog.model.User;
import com.example.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.function.Supplier;

@RestController
public class DummyControllerTest {
    @Autowired
    private UserRepository userRepository;

    @Transactional
    @PutMapping("/dummy/user/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User requestUser) {
        System.out.println("id: " + id);
        System.out.println("password : " + requestUser.getPassword());
        System.out.println("email : " + requestUser.getEmail());

        User user = userRepository.findById(id).orElseThrow(()-> {
           return new IllegalArgumentException("갱신 실패");
        });
        user.setPassword(requestUser.getPassword());
        user.setEmail(requestUser.getEmail());
        requestUser.setId(id);
        requestUser.setUsername("abc");
        userRepository.save(user);
        return null;
    }
    @GetMapping("/dummy/users")
    public List<User> list() {
        return userRepository.findAll();
    }

    @GetMapping("/dummy/user")
    public List<User> pageList(@PageableDefault(size = 2, sort="id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<User> pagingUser = userRepository.findAll(pageable);
        List<User> users = pagingUser.getContent();
        return users;
    }
    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable int id) {
//        User user = userRepository.findById(id).get();
//        User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
//            @Override
//            public IllegalArgumentException get() {
//                return new IllegalArgumentException("해당 유저는 없습니다");
//            }
//        });
        //람다식
//        User user = userRepository.findById(id).orElseThrow(()-> {
//            return new IllegalArgumentException("X");
//        });
//        return user;

        User user = userRepository.findById(id).orElseGet(new Supplier<User>() {
            @Override
            public User get() {
                return new User();
            }
        });
        //요청 : 웹브라우저
        //user객체 = 자바 오브젝트
        //변환(웹브라우저가 이해할 수 있는 데이터) -> json(Gson라이브러리)
        //스프링부트 = MessageConverter가 응답 시 자동 작동
        //만약에 자바 오브젝트를 리턴하게 되면 Jackson라이브러리를 호출해서
        //user오브젝트를 json으로 변환하여 브라우저에게 전달함
        return user;
    }

    //localhost:8000/blog/dummy/join
    //body에 usernmae, password, email데이터를 가지고 요청
    @PostMapping("/dummy/join")
    public String join(User user) {
        System.out.println("username : " + user.getUsername());
        System.out.println("password : " + user.getPassword());
        System.out.println("email : " + user.getEmail());
        System.out.println("rold : " + user.getRole());
        System.out.println("createDate : " + user.getCreateDate());
        user.setRole(RoleType.USER);
        userRepository.save(user);
        return "회원가입 완료";
    }
}
