package com.exam;


import com.exam.controller.websocket.server.WebsocketServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan("com.exam.dao")
@EnableSwagger2
public class TestApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(TestApplication.class, args);
        // 解决Websocket空注入问题
        WebsocketServer.setApplicationContext(applicationContext);

    }
}
