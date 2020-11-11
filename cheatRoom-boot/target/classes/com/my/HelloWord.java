package com.my;

        import org.springframework.boot.SpringApplication;
        import org.springframework.boot.autoconfigure.SpringBootApplication;
        import org.springframework.boot.builder.SpringApplicationBuilder;
        import org.springframework.boot.web.support.SpringBootServletInitializer;
        import org.springframework.context.annotation.Bean;
        import org.springframework.web.client.RestTemplate;

/**
 * @author w
 */
@SpringBootApplication
public class HelloWord{
    public static void main(String[] args) {
        //spring应用启动
        SpringApplication.run(HelloWord.class,args);
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
