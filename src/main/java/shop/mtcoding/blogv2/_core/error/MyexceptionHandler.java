package shop.mtcoding.blogv2._core.error;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import shop.mtcoding.blogv2._core.util.Script;

@RestControllerAdvice //throw가 터지면 여기로 다 모임
public class MyexceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public String error(RuntimeException e ){
        return Script.back(e.getMessage());  //부모거 떄려진거임 (안들고 있으니까 ) ,만약 들고 있었으면 자식게 때려지고

    }
    
    
}
