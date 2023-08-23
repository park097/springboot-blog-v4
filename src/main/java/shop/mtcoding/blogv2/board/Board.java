package shop.mtcoding.blogv2.board;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.bytebuddy.dynamic.TypeResolutionStrategy.Lazy;
import shop.mtcoding.blogv2.reply.Reply;
import shop.mtcoding.blogv2.user.User;

@NoArgsConstructor
@Setter
@Getter
@Table(name = "board_tb")
@Entity // ddl-auto가 create
public class Board {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
   
    @Column(nullable = false, length = 100)
    private String title;
    @Column(nullable = true, length = 10000)
    private String content;


     @JsonIgnoreProperties({"board"})  //제이슨 객체 안에있는 필드들을  
    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY)     //  양방향 매핑 ,포린키가 아님 ,one to many는 lazy 전략 (default)
    private List<Reply> replies = new ArrayList<>();
    
    @JsonIgnore    // 제이슨으로 줄 때 얘는 안주겠다
    @ManyToOne (fetch = FetchType.LAZY)  //컨트롤 스페이스 ,얘를 안 땡겨옴(lazy) ,(eager)을적으면 default 안적어도 있는거,연관된 애를 바로 fetch
    // @ManyToOne (fetch = FetchType.LAZY,  cascade = CascadeType.ALL)  //컨트롤 스페이스 ,얘를 안 땡겨옴(lazy) ,(eager)을적으면 default 안적어도 있는거,연관된 애를 바로 fetch
    private User user;  //1+n  
    
   
    @CreationTimestamp
    private Timestamp createdAt;
    
   
    @Builder
    public Board(Integer id, String title, String content, User user, Timestamp createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.user = user;
        this.createdAt = createdAt;
    }

}