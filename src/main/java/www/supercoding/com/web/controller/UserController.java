package www.supercoding.com.web.controller;

import org.springframework.web.bind.annotation.*;
import www.supercoding.com.web.dto.Sign.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    List<User> users = new ArrayList<User>();;

    @PostMapping("/signup")
    public SignUpResponse signup(@RequestBody SignUpRequest req)
    {
        int id = users.size() + 1;
        User newUser = new User(id,req.getEmail(),req.getPassword());
        users.add(newUser);
        // TODO : 유저 패스워드 검증 및 DB 검증
        System.out.println("새로운 유저가 생겼습니다. : " + users.size());
        return new SignUpResponse(newUser.getEmail(), newUser.getPassword());
    }

    @GetMapping("/singin")
    public SigninResponse singin(@RequestBody SigninRequest req)
    {
        User findedUser = users.stream()
                .filter(user -> user.getEmail().equals(req.getEmail()))
                .filter(user-> user.getPassword().equals(req.getPassword()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("아이디 혹은 비밀번호가 일치하지 않습니다."));
        return new SigninResponse("해당 유저를 찾았습니다. email : ");
    }

    @GetMapping("/signout")
    public SignoutResponse signout(){
        return new SignoutResponse("로그아웃 했습니다.");
    }
}
