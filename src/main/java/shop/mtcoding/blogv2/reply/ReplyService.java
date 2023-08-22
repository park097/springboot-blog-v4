package shop.mtcoding.blogv2.reply;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.mtcoding.blogv2.reply.ReplyRequest.SaveDTO;


@Service
public class ReplyService {

    @Autowired
    private ReplyRepository replyRepository;
    
    @Transactional
    public void 댓글쓰기(SaveDTO saveDTO, Integer id) {

        //1, board id 가 존재하는지 유무

        
        Reply reply = Reply.builder()
        .comment(saveDTO.getComment())
        .board(null)
        .user(null)
        .build();
        replyRepository.save(reply);  //entity :reply객체
    }
    
    @Transactional
    public void deleteById(Integer id) {
        replyRepository.deleteById(id);
    }

    // @Transactional
    // public void 글쓰기(ReplyRequest.ReplyDTO replyDTO, int sessionUserId) {
    //     Reply reply = Reply.builder()
    //             .board(Board.builder().id(replyDTO.getBoardId()).build())
    //             .comment(replyDTO.getComment())
    //             .user(User.builder().id(sessionUserId).build())
    //             .build();

    //     replyRepository.save(reply);
    // }


}




