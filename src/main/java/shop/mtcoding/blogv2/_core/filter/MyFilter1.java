package shop.mtcoding.blogv2._core.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
//필터가 언제 동작할지 설정
@Component
public class MyFilter1 implements Filter {

    @Override    //필터를 만듦ㄴ
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)  
    //톰캣에서 받음 ,내가 받은 리퀘스트 리스폰스를 다음 필터에게 전달해줌,더이상 필터가 없으면 디스패쳐서블릿이에게 
    //그유알엘을 컨트롤러를 때림 
            throws IOException, ServletException {
                System.out.println("필터1 실행됨");
                
           HttpServletRequest req = (HttpServletRequest) request;  //다운캐스팅
           HttpServletResponse resp = (HttpServletResponse) response;       

         
              // 1. IP 로그 남기기
        System.out.println("접속자 ip : "+req.getRemoteAddr()); 
        System.out.println("접속자 user agent : "+req.getHeader("User-Agent"));

        // 2. 블랙리스트 추방
        if(req.getHeader("User-Agent").contains("Xbox")){
            //resp.setContentType("text/html; charset=utf-8");
            resp.setHeader("Content-Type", "text/html; charset=utf-8");
            PrintWriter out = resp.getWriter();

            req.setAttribute("name", "홍길동");
            out.println("<h1>나가세요 크롬이면 : "+req.getAttribute("name")+"</h1>");
            return;
        }

        

        chain.doFilter(request, response); // 다음 필터로 request, response 전달 ..필터 없으면 DS로 전달
    }

}




           
    
    

