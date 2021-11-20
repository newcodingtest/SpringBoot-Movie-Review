package com.yoon.guestbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing// JPA를 이용하면서 AuditingEntityListener를 활성화시킨다.
public class GuestBookApplication {

    public static void main(String[] args) {
        SpringApplication.run(GuestBookApplication.class, args);
    }

}
