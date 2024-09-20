package ci.digitalacademy.monEtab;

import ci.digitalacademy.monEtab.models.enumeration.Gender;
import ci.digitalacademy.monEtab.services.*;
import ci.digitalacademy.monEtab.services.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.Instant;
import java.time.LocalDate;

@RequiredArgsConstructor
@SpringBootApplication
public class MonEtabApplication implements CommandLineRunner {

	private final BCryptPasswordEncoder passwordEncoder;
	private final UserService userService;

	public static void main(String[] args) {

		SpringApplication.run(MonEtabApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {


		String passwordAdmin = passwordEncoder.encode("admin");

		UserDTO admin = new UserDTO();
		admin.setPseudo("admin");
		admin.setPassword(passwordAdmin);
		admin.setCreatedDate(Instant.now());

		userService.save(admin);

		String passwordUser = passwordEncoder.encode("user");

		UserDTO user = new UserDTO();
		user.setPseudo("user");

		user.setPassword(passwordUser);
		user.setCreatedDate(Instant.now());

		userService.save(user);




	}
}
