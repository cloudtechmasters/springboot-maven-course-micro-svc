package com.cloudtechmasters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
@SpringBootApplication
@ComponentScan(basePackages = "com.cloudtechmasters")
public class SpringbootMavenCourseMicroSvcApplication {
private final static Logger log = LoggerFactory.getLogger(SpringbootMavenCourseMicroSvcApplication.class);
	@Autowired
    private ApplicationContext applicationContext;
	public static void main(String[] args) {
		SpringApplication.run(SpringbootMavenCourseMicroSvcApplication.class, args);
	}
@Scheduled(fixedRate = 5000)
    public void log() {
        log.info("This is \na multiline\n\n\nlog");
    }
    private void m() {
    }
    @Scheduled(fixedRate = 10000)
    public void logException() {
        try {
            applicationContext.getBean("test");
        } catch (Exception e) {
            throw new RuntimeException("Error happened", e);
        }
    }
}
