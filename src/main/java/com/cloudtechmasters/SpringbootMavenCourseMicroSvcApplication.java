package com.cloudtechmasters;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.cloudtechmasters")
public class SpringbootMavenCourseMicroSvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootMavenCourseMicroSvcApplication.class, args);
	}

}
