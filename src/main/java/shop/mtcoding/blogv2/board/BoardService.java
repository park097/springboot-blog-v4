package shop.mtcoding.blogv2.board;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.mtcoding.blogv2.user.User;

/**
 * 서비스 역할
 * 1,비지니스 로직처리(핵심로직)
 * 2,트랜잭션 관리
 * 3,예외처리
 * 4,dto변환 (엔티티(모델)도 다 )
 * @param id
 * @return
 */
@Service
public class BoardService  {

    @Autowired
    private BoardRepository boardRepository;

    @Transactional
    public void 글쓰기(BoardRequest.SaveDTO saveDTO, int sessionUserId) {
        Board board = Board.builder()
                .title(saveDTO.getTitle())
                .content(saveDTO.getContent())
                .user(User.builder().id(sessionUserId).build())
                .build();

        boardRepository.save(board);
    }

    public Page<Board> 게시글목록보기(Integer page) {
        Pageable pageable = PageRequest.of(page, 3, Sort.Direction.DESC, "id");
        return boardRepository.findAll(pageable);
    }

    public Board 상세보기(Integer id) {
        //board만 가져오면 된다 그래서 mfind를 안쓴다.
      return  boardRepository.findById(id).get();
        
        
}



@Transactional
public void update(BoardRequest.UpdateDTO updateDTO, Integer id) {
    // 1. 조회(영속화)
    Optional<Board> boardOP= boardRepository.findById(id);   
    if(boardOP.isPresent()){
        Board board = boardOP.get();

  
    board.setTitle(updateDTO.getTitle());
    board.setContent(updateDTO.getContent());


}
}


@Transactional
public void deleteById(Integer id)  {
    
    try {
        boardRepository.deleteById(6);
        
    } catch (Exception e) {
        throw new RuntimeException("6번은 없어요");
    }
 
    
    
}


public  Board findById(Integer id) {
   return boardRepository.findById(id).get();
}


}