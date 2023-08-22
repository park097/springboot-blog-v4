package shop.mtcoding.blogv2._core.error;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import shop.mtcoding.blogv2._core.error.ex.MyApiException;
import shop.mtcoding.blogv2._core.error.ex.MyException;
import shop.mtcoding.blogv2._core.util.ApiUtil;
import shop.mtcoding.blogv2._core.util.Script;

@RestControllerAdvice //throw가 터지면 여기로 다 모임 ,responsebody가 기본적으로 붙어있음 ,다 데이터를 응답할거임 
public class MyexceptionHandler {

    @ExceptionHandler(MyException.class)
    public String error(MyException e ){
        return Script.back(e.getMessage());  //부모거 떄려진거임 (안들고 있으니까 ) ,만약 들고 있었으면 자식게 때려지고

    }
    
    

    
    @ExceptionHandler(MyApiException.class)
    public ApiUtil<String> error(MyApiException e ){
        return new ApiUtil<String>(false, e.getMessage());  

    }
    
    
}
