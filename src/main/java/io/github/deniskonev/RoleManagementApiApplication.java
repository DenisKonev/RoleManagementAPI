package io.github.deniskonev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:db/db.properties")
public class RoleManagementApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(RoleManagementApiApplication.class, args);
    }

}
