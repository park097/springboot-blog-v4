package shop.mtcoding.blogv2.reply;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReplyController {

@Autowired
private ReplyRepository replyRepository;
@Autowired
private HttpSession session;
   
@Autowired
private ReplyService replyService;

@PostMapping("/reply/{id}/delete")
public String delete(@PathVariable Integer id,  Integer boardId) {
    replyService.deleteById(id);
    return "redirect:/board/" + boardId;
}


@PostMapping("/reply/save")
public String save(ReplyRequest.RplyDTO replyDTO ,Integer boardID) {
  

    replyService.글쓰기(replyDTO, 1,boardID);


    return "redirect:/board/" + boardID;
}

}
