package shop.mtcoding.blogv2.reply;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.mtcoding.blogv2.board.Board;


@Service
public class ReplyService {

    @Autowired
    private ReplyRepository replyRepository;
    


    @Transactional
    public void deleteById(Integer id) {
        replyRepository.deleteById(id);
    }

    @Transactional
    public void 글쓰기(ReplyRequest.RplyDTO rplyDTO, int sessionUserId, Integer boardId ) {
        Reply reply = Reply.builder()
                .board(Board.builder().id(boardId).build())
                .comment(rplyDTO.getComment())
                .build();

        replyRepository.save(reply);
    }


}




