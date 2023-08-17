package shop.mtcoding.blogv2.board;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import shop.mtcoding.blogv2.user.User;

@DataJpaTest // 모든 Repository, EntityManager
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void findById_test(){
    Optional<Board> boardOP = boardRepository.findById(6);
    if (boardOP.isPresent()){   //board가 존재하면!!(null안정성)
        System.out.println("테스트:board가 있습니다.");
        
    }
}


    
    
    @Test
    public void mFindAll_test(){
      System.out.println("조회직전");
       List<Board> boardList = boardRepository.findAll();
       System.out.println("조회 후 : Lazy");
       //행 : 5개 ->객체:5개
       //각행 : Board(id=1, title=제목1, content= 내용1, created_at = 날짜, User(id=1))
       System.out.println(boardList.get(0).getId());//1번 (조회 x)
       System.out.println(boardList.get(0).getUser().getId());//1번(조회 x) -->널이아니니까

       //Lazy Loadig - 지연로딩
       //연관된 객체에 null을 참조하려고 하면 조회가 일어남 (지연로딩)  (조회0)--->널이니까 
    System.out.println(boardList.get(0).getUser().getUsername());
    //null -> ssar
    }


//    @Test
//     public void findAll_paging_test() throws JsonProcessingException{
//         Pageable pageable = PageRequest.of(0, 3, Direction.DESC,"id");
//        Page<Board>boardPG = boardRepository.findAll(pageable);
//        //OBJECTMAPPER 은  BOARDPG의 객체의 GETTER을 호출하면서 JSON을 변환
//        ObjectMapper om = new ObjectMapper();
//        String json= om.writeValueAsString(boardPG);
//        System.out.println(json);
//     }

    @Test
    public void save_test() {
        // 비영속 객체
        Board board = Board.builder()
                .title("제목6")
                .content("내용6")
                .user(User.builder().id(1).build())
                .build();

        // 영속 객체
        boardRepository.save(board); // insert 자동으로 실행됨
        // 디비데이터와 동기화됨
        System.out.println(board.getId());
    }


   
}
