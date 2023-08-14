package shop.mtcoding.blogv2.user;

import java.util.Optional;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;


@Import(UserQueryRepository.class)
@DataJpaTest  //JpaRepository 란 메모리에 올려준다 (ioc에 안올려준다)
public class UserQueryRepositoryTest {

    @Autowired
    private UserQueryRepository userQueryRepository;
    
    @Autowired
    private EntityManager em;

    //persistence (영속화)
 @Test
 public void save_test(){
    User user = User.builder()
        .username("love")
        .password("1234")
        .email("love@nate.com")
        .build();
        userQueryRepository.save(user);
 }


 //1차캐시
  @Test
 public void findById_test(){
    //pc는 비어있다.
    
    System.out.println("1,pc 비어있다");
    userQueryRepository.findById(1);
    System.out.println("2.pc user 1 은 영속화 되어있다");
    em.clear();
    userQueryRepository.findById(1);


    //pc는 user1의 객체가 영속화 되어있다 (조회되면서)



 }

  @Test
     public void update_test(){
      //jpa update알고리즘
      //1,업데이트 할 객체를 영속화
      //2,객체 상태 변경
      //3,em.flush() or @Transactional 정상종료
      User user = userQueryRepository.findById(1); //영속화된 객체
      user.setEmail("ssarmango@nate.com");
      em.flush();



     }   //테스트는 롤백이 무조건 돼서 트랙잭셔널 못 씀


}

