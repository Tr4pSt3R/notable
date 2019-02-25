package notable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class NotableApplication {

	@Autowired
	NoteRepository noteRepository;;

	public static void main(String[] args) {
		SpringApplication.run(NotableApplication.class, args);
	}

	@Bean
	CommandLineRunner runner() {
		return args -> {
			/* Add seed data */
			noteRepository.save(new Note("1", "Lorem ipsum dolor"));
			noteRepository.save(new Note("2", "Sriracha scenester cardigan"));
			noteRepository.save(new Note("3", "Tofu commodo squid"));
		};
	}

}
