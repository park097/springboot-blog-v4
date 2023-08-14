package shop.mtcoding.blogv2.user;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;



import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;



public interface UserJPQLRepository extends JpaRepository<User, Integer> {

     
     //executeUpdate
    @Query(value = "select u from User u where u.id = :id")  //u는 entity
    Optional<User> mFindById(@Param("id") Integer id);
    
     //executeUpdate
    @Query(value = "select u from User u where u.username = :username")
    User  findByUsername(@Param("username") String username);   //findbuusername 은 정의 jpa에 정의 돼 있지 않음.

    @Modifying  //executeUpdate //insert,update,delete는 jpal 사용못함
    @Query(value = "insert into user_tb(created_at, email, password, username) values(now(), :email, :password, :username)",nativeQuery = true)
    void mSave(@Param("username") String username, @Param("password") String password, @Param("email") String email);
}


    

