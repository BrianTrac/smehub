package vn.test.hub.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "vn.test.hub.*")
public class MainCommonApplication {
    public static void main(String[] args) {
        SpringApplication.run(MainCommonApplication.class, args);
    }
}
