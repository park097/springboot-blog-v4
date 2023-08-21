package shop.mtcoding.blogv2.board;


import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder.In;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/*
 * save(), findById(), findAll(), count(), deleteById()
 */
//스프링이 실행될 때,boardrepository의 구현체가 ioc컨테이너에 생성된다.
//타입으로 찾는다, 싱글톤임 
public interface BoardRepository extends JpaRepository<Board, Integer> {

    //select id,title,user_id created_at from board_tb b inner join user_tb u on b.user_id = u.id ;
    //fetch를 붙여야 *를 한다 ,연관된 객체에 들어가서 다 뽑아내는 거(프로젝션??)
    @Query("select b from Board b join fetch b.user")
    List<Board> mFindAll();
  
  //   @Modifying
  //   @Query("DELETE FROM Board b WHERE b.id = :id")
  //   void deleteById(@Param("id") Integer id);
  
  @Query("select b from Board b left join fetch b.replies r left join fetch r.user ru where b.id = :id")
  Optional<Board> mFindByIdJoinRepliesInUser(@Param("id") Integer id);


  //   @Modifying
  //  @Query("UPDATE Board b SET b.title = :title, b.content = :content WHERE b.id = :id")
  //    void update(@Param("id") Integer id, @Param("title") String title, @Param("content") String content);
  
    

}
