package com.besmart;

import com.besmart.utils.Constants;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ServerApplication {


    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(ServerApplication.class, args);
        System.out.println(Constants.SERVER_WAS_STARTED_IN_SUCCESSFULLY + applicationContext.toString());

    }
}
