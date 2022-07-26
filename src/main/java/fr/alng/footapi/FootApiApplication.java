package fr.alng.footapi;

import fr.alng.footapi.model.Role;
import fr.alng.footapi.model.User;
import fr.alng.footapi.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@SpringBootApplication
public class FootApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FootApiApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	//@Bean
	/*CommandLineRunner run(UserService userService){
		return args -> {
			userService.saveRole(new Role(null, "ROLE_USER"));
			userService.saveRole(new Role(null, "ROLE_MANAGER"));
			userService.saveRole(new Role(null, "ROLE_ADMIN"));
			userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));

			userService.saveUser(new User(null, "admin", "axel.lung@outlook.fr", new ArrayList<>(), new SimpleDateFormat("YYYY-MM-dd").parse("2000-02-21"), "1234"));
			userService.saveUser(new User(null, "pedro25", "pedro@pedro.fr", new ArrayList<>(), new SimpleDateFormat("YYYY-MM-dd").parse("2000-02-21"), "1234"));
			userService.saveUser(new User(null, "toto", "toto@toto.fr", new ArrayList<>(), new SimpleDateFormat("YYYY-MM-dd").parse("2000-02-21"), "1234"));
			userService.saveUser(new User(null, "superadmin", "superadmin@superadmin.fr", new ArrayList<>(), new SimpleDateFormat("YYYY-MM-dd").parse("2000-02-21"), "1234"));

			userService.addRoleToUser("superadmin", "ROLE_SUPER_ADMIN");
			userService.addRoleToUser("superadmin", "ROLE_ADMIN");
			userService.addRoleToUser("superadmin", "ROLE_USER");
			userService.addRoleToUser("admin", "ROLE_ADMIN");
			userService.addRoleToUser("pedro25", "ROLE_MANAGER");
			userService.addRoleToUser("toto", "ROLE_USER");
		};
	}*/

}
