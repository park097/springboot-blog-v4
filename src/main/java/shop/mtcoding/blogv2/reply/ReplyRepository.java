package shop.mtcoding.blogv2.reply;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;



import shop.mtcoding.blogv2.board.Board;


public interface ReplyRepository  extends JpaRepository<Reply, Integer> {



}
