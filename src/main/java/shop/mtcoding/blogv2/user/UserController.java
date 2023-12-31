package shop.mtcoding.blogv2.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import shop.mtcoding.blogv2._core.util.ApiUtil;
import shop.mtcoding.blogv2._core.util.Script;

@Controller
public class UserController {

    @Autowired  //di(컴포넌스 스캔이 됐다는 가정하에)

    private UserService userService;

   
    
   @Autowired
    private HttpSession session;

     @GetMapping("/api/check")
    public @ResponseBody ApiUtil<String> checkUsername(String username){
       


        //3,응답
        return userService.checkUsername(username);



    }

    // C - V
    @GetMapping("/joinForm")
    public String joinForm() {
        return "user/joinForm";
    }

    // M - V - C
    @PostMapping("/join")
    public String join(UserRequest.JoinDTO joinDTO) {
        // System.out.println(joinDTO.getPic().getOriginalFilename());
        // System.out.println(joinDTO.getPic().get());
        // System.out.println(joinDTO.getPic().getOriginalFilename());
        // 10초짜리 코드
        userService.회원가입(joinDTO);
        return "user/loginForm"; //persist 초기화 ,퍼시스트 공간이 독립적으로 존재
        //여기서 clear
    }

    @GetMapping("/loginForm")
    public String loginForm() {
        return "user/loginForm";
    }


  //브라우저가 get /logout 요청을 함 (requset1)
  //서버는 /주소를 응답의 헤더에 담음 (location) ,상태코드 302  (재요청 ,redirection)
  //브라우저는 다시 GET /로 재요청을 함 (requst2)
  //index 페이지 응답받고 랜더링 함
  



    @GetMapping("/logout")
    public String logout() {
        session.invalidate(); // 세션 무효화 (내 서랍을 비우는 것)
        return "redirect:/";
    }

    @PostMapping("/login")
    public @ResponseBody String login(UserRequest.LoginDTO loginDTO) {
        User sessionUser = userService.로그인(loginDTO);
       
        session.setAttribute("sessionUser", sessionUser);
        return Script.href("/");
    }

    @GetMapping("/user/updateForm")
    public String updateForm(HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        User user = userService.회원정보보기(sessionUser.getId());
        request.setAttribute("user", user);
        return "user/updateForm";
    }
    @PostMapping("/user/update")
    public String update(UserRequest.updateDTO updateDTO){
        //1,회원수정(서비스) ,핵심로직
        //2,세션동기화
        User sessionUser = (User) session.getAttribute("sessionUser"); //세션을 가져옴 
        User user = userService.회원수정(updateDTO,sessionUser.getId());
        session.setAttribute("sesstionUser", user);  //바뀐걸로 동기화
        return "redirect:/";

    }
}