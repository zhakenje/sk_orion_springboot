package kz.orion.sk_orion_springboot;

import kz.orion.sk_orion_springboot.model.entity.Admin;
import kz.orion.sk_orion_springboot.model.entity.enums.Role;
import kz.orion.sk_orion_springboot.model.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    private final AdminRepository adminRepository;

    @Bean
    public CommandLineRunner getCommandLiner(){
        return args -> {
            Admin admin = new Admin();
            admin.setUsername("admin");
            admin.setPassword("admin");
            admin.setFirstName("John");
            admin.setLastName("Macintosh");
            admin.setRole(Role.ADMIN);
            admin.setDateOfBirth(LocalDate.of(1996, 8, 20));
            admin.setPhoneNumber("8-777-816-96-96");
            admin.setEmail("zhakenje@gmail.com");

            adminRepository.save(admin);

        };
    }
}
