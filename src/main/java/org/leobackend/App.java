package org.leobackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created on 2020-12-23.
 * 启动类而已
 *
 * @author Leo
 */
@SpringBootApplication
@ComponentScan (basePackages = {"org.leobackend.*"})
@MapperScan (basePackages = {"org.leobackend.mapper"})
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}
