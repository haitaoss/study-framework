package cn.haitaoss.studyspringbooturule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("classpath:urule-console-context.xml")
@SpringBootApplication
public class StudySpringbootUruleApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudySpringbootUruleApplication.class, args);
    }


}
