package shop.mtcoding.blogv2.reply;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.mtcoding.blogv2._core.error.ex.MyApiException;
import shop.mtcoding.blogv2.board.Board;
import shop.mtcoding.blogv2.reply.ReplyRequest.SaveDTO;
import shop.mtcoding.blogv2.user.User;


@Service
public class ReplyService {

    @Autowired
    private ReplyRepository replyRepository;
    
    @Transactional
    public void 댓글쓰기(SaveDTO saveDTO, Integer sessionID) {

        //1, board id 가 존재하는지 유무
        //비영속객체 
         Board board = Board.builder().id(saveDTO.getBoardId()).build();
         User user = User.builder().id(sessionID).build();

         //비영속 객체
        Reply reply = Reply.builder()
        .comment(saveDTO.getComment())
        .board(null)
        .user(null)
        .build();
        replyRepository.save(reply);  //entity :reply객체

        //리플리는 영속 객체 ,저장되니까 
    }
    
    @Transactional
    public void 댓글삭제(Integer id, Integer sessionUserId) {
        //권한체크
        Optional<Reply> replyOP = replyRepository.findById(id);

        if(replyOP.isEmpty()){
        throw new MyApiException("삭제할 댓글이 없습니다.");}

            Reply reply = replyOP.get();
      
            if(reply.getUser().getId() != sessionUserId){
                throw new MyApiException("해당 댓글을 삭제할 권한이 없습니다.");
            }
       
        
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




