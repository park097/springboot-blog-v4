package shop.mtcoding.blogv2.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import shop.mtcoding.blogv2._core.util.Script;
import shop.mtcoding.blogv2.board.BoardService;

@Controller
public class UserController {

    @Autowired  //di(컴포넌스 스캔이 됐다는 가정하에)

    private UserService userService;

    @Autowired
    private HttpSession session;

    // C - V
    @GetMapping("/joinForm")
    public String joinForm() {
        return "user/joinForm";
    }

    // M - V - C
    @PostMapping("/join")
    public String join(UserRequest.JoinDTO joinDTO) {
        // 10초짜리 코드
        userService.회원가입(joinDTO);
        return "user/loginForm"; //persist 초기화 ,퍼시스트 공간이 독립적으로 존재
        //여기서 clear
    }

    @GetMapping("/loginForm")
    public String loginForm() {
        return "user/loginForm";
    }

    @PostMapping("/login")
    public @ResponseBody String login(UserRequest.LoginDTO loginDTO) {
        User sessionUser = userService.로그인(loginDTO);
        if (sessionUser == null) {
            return Script.back("로그인 실패");
        }
        session.setAttribute("sessionUser", sessionUser);
        return Script.href("/");
    }

    @GetMapping("/user/updateForm")
    public String updateForm(HttpServletRequest request){
        User sessionUser = (User) session.getAttribute("sessionUser");  //로그인 했다생각하고 세션유저를 가졍ㅁ 
        User user = userService.회원정보보기(sessionUser.getId());  //회원정보
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