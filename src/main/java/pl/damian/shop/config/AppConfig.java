package pl.damian.shop.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.damian.shop.domain.dao.Role;
import pl.damian.shop.repository.RoleRepository;

@Configuration
public class AppConfig {

    @Bean
    public CommandLineRunner commandLineRunner(RoleRepository roleRepository) {
        return args -> {
            addRole("ROLE_ADMIN", roleRepository);
            addRole("ROLE_USER", roleRepository);

//            if (roleRepository.findByName("ROLE_USER").isEmpty()) {
//                roleRepository.save(new Role(null, "ROLE_USER"));
//            }
//            if (roleRepository.findByName("ROLE_ADMIN").isEmpty()) {
//                roleRepository.save((new Role(null, "ROLE_ADMIN")));
//            }
        };
    }

    private void addRole(String role, RoleRepository roleRepository) {
        if (roleRepository.findByName(role).isEmpty()) {
            roleRepository.save(new Role(null, role));
        }
    }


    //szyfrowanie haseł
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
