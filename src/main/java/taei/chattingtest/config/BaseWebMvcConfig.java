package taei.chattingtest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.Charset;

@Configuration
public class BaseWebMvcConfig implements WebMvcConfigurer {

    @Bean
    public HttpHeaderDefaultType httpHeaderDefaultType(){
        HttpHeaderDefaultType httpHeaderDefaultType = new HttpHeaderDefaultType(new MediaType("application", "json", Charset.forName("UTF-8")));
        return httpHeaderDefaultType;
    }
}
