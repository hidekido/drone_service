package fedor.lysenko.drone_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableWebMvc
@EnableSwagger2
@EnableScheduling
public class DroneServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DroneServiceApplication.class, args);
	}
}
