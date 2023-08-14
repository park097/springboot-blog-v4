package shop.mtcoding.blogv2.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/*
 * save(), findById(), findAll(), count(), deleteById()
 */
//스프링이 실행될 때,boardrepository의 구현체가 ioc컨테이너에 생성된다.
//타입으로 찾는다, 싱글톤임 
public interface BoardRepository extends JpaRepository<Board, Integer> {

}
