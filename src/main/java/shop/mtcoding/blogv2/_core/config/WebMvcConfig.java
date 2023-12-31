package shop.mtcoding.blogv2._core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

import shop.mtcoding.blogv2._core.intercepter.LoginInterceptor;

//사진 외부경로 정해주는거
@Configuration   //메모리에 띄움 ,
//이러면 오버라이드 가능 xml을?
//입구에서 필터링, 쫓아내는거, 문지기한테 문서를 주는 거
public class WebMvcConfig implements WebMvcConfigurer{

    @Override    
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
         //기존에 하는 일은 두고 그대로 재정의
        WebMvcConfigurer.super.addResourceHandlers(registry);

         //images 하면 .images를 찾으러 감 
        registry.addResourceHandler("/images/**")
         .addResourceLocations("file:"+"./images/")
         .setCachePeriod(10) //10초동안 다시요청해도 다운 안받음 , 브라우저가 캐싱하고 있으니까 .항상 똑같은 사진이니까 ,수정하면 바뀌고
         .resourceChain(true)
         .addResolver(new PathResourceResolver());
    }
 //등록
 @Override
 public void addInterceptors(InterceptorRegistry registry) {
     registry.addInterceptor(new LoginInterceptor())
             .addPathPatterns("/api/**")
             .addPathPatterns("/user/update" ,"/user/updateForm")
             .addPathPatterns("/board/**") // 발동 조건
             .excludePathPatterns("/board/{id:[0-9]+}"); // 발동 제외
 }//필터링

    




    
}
