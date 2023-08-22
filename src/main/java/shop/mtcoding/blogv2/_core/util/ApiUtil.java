package shop.mtcoding.blogv2._core.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiUtil<T> {
    private boolean sucuess;   //인서트 잘 되면 true
    private T data;    //댓글쓰기 성공
    
    
    public ApiUtil(boolean sucuess, T data) {
        this.sucuess = sucuess;
        this.data = data;
    }    //공통 dto

    

    





    
}
