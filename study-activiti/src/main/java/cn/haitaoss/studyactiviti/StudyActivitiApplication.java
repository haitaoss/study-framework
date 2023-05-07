package cn.haitaoss.studyactiviti;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @SpringBootApplication(exclude = {SecurityAutoConfiguration.class, ManagementWebSecurityAutoConfiguration.class})
@SpringBootApplication
// @MapperScan("cn.haitaoss.studyactiviti.mapper")
public class StudyActivitiApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(StudyActivitiApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("springboot run...");
    }
}
