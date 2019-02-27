package notable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class NotableApplication {

	@Autowired
	private NoteRepository noteRepository;

	public static void main(String[] args) {
		SpringApplication.run(NotableApplication.class, args);
	}

	@Bean
	CommandLineRunner runner() {
		return args -> {
			/* Add seed data */
			noteRepository.save(new Note("100", "Lorem ipsum dolor"));
			noteRepository.save(new Note("200", "Sriracha scenester cardigan"));
			noteRepository.save(new Note("300", "Tofu commodo squid"));
		};
	}

}
