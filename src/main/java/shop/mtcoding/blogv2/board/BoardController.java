package shop.mtcoding.blogv2.board;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import shop.mtcoding.blogv2._core.util.Script;
import shop.mtcoding.blogv2.board.BoardRequest.UpdateDTO;
import shop.mtcoding.blogv2.reply.Reply;
import shop.mtcoding.blogv2.user.User;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    //where데이터,body, session값
    @PostMapping("/board/{id}/update")
    public String update(@PathVariable Integer id, BoardRequest.UpdateDTO updateDTO) {
        // 1. 인증 검사

        // 2. 권한 체크

        // 3. 핵심 로직
        // update board_tb set title = :title, content = :content where id = :id
        boardService.update(updateDTO, id);

        return "redirect:/board/" + id;
    }

    
    @GetMapping("/board/{id}/updateForm")
    public String updateForm(@PathVariable Integer id, HttpServletRequest request) {
        // 1. 인증 검사

        // 2. 권한 체크

        // 3. 핵심 로직
        Board board = boardService.findById(id);
        request.setAttribute("board", board);

        return "board/updateForm";
    }

 @PostMapping("/board/{id}/delete")
public @ResponseBody String delete(@PathVariable Integer id) {
    
      
        //write는 다 잡는 거
          boardService.deleteById(6);
          return Script.href("/");

      
       
    
   
}


    @GetMapping("/board/{id}")
    public String detail(@PathVariable Integer id,HttpServletRequest request){
        Board board = boardService.상세보기(id);
        request.setAttribute("board", board);
        return "board/detail";
    }
    


    // localhost:8080?page=1&keyword=바나나
    @GetMapping("/")
    public String index(@RequestParam(defaultValue = "0") Integer page, HttpServletRequest request) {
        Page<Board> boardPG = boardService.게시글목록보기(page);
        request.setAttribute("boardPG", boardPG);
        request.setAttribute("prevPage", boardPG.getNumber() - 1);
        request.setAttribute("nextPage", boardPG.getNumber() + 1);

        return "index";
    }

    @GetMapping("/test")
    public @ResponseBody Page<Board> test(@RequestParam(defaultValue = "0") Integer page, HttpServletRequest request) {
        Page<Board> boardPG = boardService.게시글목록보기(page);
        return boardPG; // ViewResolver (X), MessageConverter (O) -> json 직렬화
    }

    @GetMapping("/board/saveForm")
    public String saveForm() {
        return "board/saveForm";
    }

    // 1. 데이터 받기 (V)
    // 2. 인증체크 (:TODO)
    // 3. 유효성 검사 (:TODO)
    // 4. 핵심로직 호출 (V)
    // 5. view or data 응답} (V)
    @PostMapping("/board/save")
    public String save(BoardRequest.SaveDTO saveDTO) {
        boardService.글쓰기(saveDTO, 1);
        return "redirect:/";
    }
}