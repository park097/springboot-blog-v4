package shop.mtcoding.blogv2.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

     @Autowired  //di(컴포넌스 스캔이 됐다는 가정하에)
    private UserService userService;

    @GetMapping("/joinForm")
    public String joinForm(){
        return "user/joinForm";
    }

    //M-V-c
    @PostMapping("/join")
    public String join(UserRequest.JoinDTO joinDTO){
        userService.회원가입(joinDTO);

        return"user/loginForm";  //persist 초기화 ,퍼시스트 공간이 독립적으로 존재
                                  //여기서 clear

    }

}
