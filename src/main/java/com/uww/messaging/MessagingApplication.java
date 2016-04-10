package com.uww.messaging;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
@Configuration
public class MessagingApplication {

    @Value("${messaging.user.download.directory}")
    public String userDownloadDir;

    @Value("${messaging.team.download.directory}")
    public String teamDownloadDir;

    public static void main(String[] args) {
        SpringApplication.run(MessagingApplication.class, args);
    }
}
