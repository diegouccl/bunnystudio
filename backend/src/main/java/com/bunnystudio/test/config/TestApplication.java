package com.bunnystudio.test.config;

import com.bunnystudio.test.enums.TaskState;
import com.bunnystudio.test.models.Task;
import com.bunnystudio.test.models.User;
import com.bunnystudio.test.repositories.TaskRepository;
import com.bunnystudio.test.repositories.UserRespository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@ComponentScan({ "com.bunnystudio.test*" })
@EntityScan(basePackages = { "com.bunnystudio.test.models" })
@EnableJpaRepositories("com.bunnystudio.test.repositories")
public class TestApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(TestApplication.class);
    }

}
