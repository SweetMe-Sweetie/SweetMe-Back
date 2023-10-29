package efub.SweetMeback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SweetMeBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(SweetMeBackApplication.class, args);
	}

}
