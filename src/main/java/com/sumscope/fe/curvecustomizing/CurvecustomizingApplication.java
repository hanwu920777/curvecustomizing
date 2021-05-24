package com.sumscope.fe.curvecustomizing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CurvecustomizingApplication {

    public static void main(String[] args) {
        SpringApplication.run(CurvecustomizingApplication.class, args);
    }


}
