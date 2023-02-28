package com.masai.demo;

import com.masai.demo.configuration.AppConstants;
import com.masai.demo.dto.UserDto;
import com.masai.demo.model.Role;
import com.masai.demo.model.User;
import com.masai.demo.repository.RoleDao;
import com.masai.demo.repository.UserDao;
import com.masai.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
@EnableWebMvc
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class PrdApplication implements CommandLineRunner {

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(PrdApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Role normalRole = new Role();
		normalRole.setRoleId(AppConstants.NORMAL_USER);
		normalRole.setName("ROLE_NORMAL");

		Role adminRole = new Role();
		adminRole.setRoleId(AppConstants.ADMIN_USER);
		adminRole.setName("ROLE_ADMIN");

		List<Role> roles = List.of(normalRole,adminRole);

		List<Role> roleList = this.roleDao.saveAll(roles);

		roleList.forEach(role -> System.out.println(role.getName()));

		//Creating Admin user if Application is run for first time

		Optional<User> optionalUser = this.userDao.findByEmail("abhishek@gmail.com");

		if (optionalUser.isEmpty()) {

			UserDto user = new UserDto();
			user.setUserFirstName("Abhishek");
			user.setUserLastName("Bhandari");
			user.setEmail("abhishek@gmail.com");
			user.setPassword("Hello@123");
			user.setPhone("9760805712");

			this.userService.createUser(user,AppConstants.ADMIN_USER);
		}
		else{
			System.out.println(optionalUser.get());
		}


	}



}
