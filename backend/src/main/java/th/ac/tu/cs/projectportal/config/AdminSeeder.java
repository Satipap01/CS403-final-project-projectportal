package th.ac.tu.cs.projectportal.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import th.ac.tu.cs.projectportal.repository.UserRepository;
import th.ac.tu.cs.projectportal.entity.User;
import th.ac.tu.cs.projectportal.entity.Role;
import th.ac.tu.cs.projectportal.entity.Gender;

@Configuration
public class AdminSeeder {

    @Bean
    CommandLineRunner init(UserRepository userRepository) {
        return args -> {
            if (userRepository.findByUsername("admin").isEmpty()) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword(new BCryptPasswordEncoder().encode("admin123"));
                admin.setRole(Role.Admin);
                admin.setApproved(true);
                admin.setGender(Gender.Male);
                admin.setNameTh("แอดมิน"); // ต้องใส่
                admin.setNameEn("Admin"); // ต้องใส่
                admin.setEmail("admin@example.com"); // ถ้า NOT NULL
                admin.setTel("0000000000");
                userRepository.save(admin);
                System.out.println("✅ Default Admin created: username=admin, password=admin123");
            }
        };
    }
}
