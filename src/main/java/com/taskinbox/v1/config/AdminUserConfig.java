package com.taskinbox.v1.config;

import com.taskinbox.v1.domain.model.enumerations.Role;
import com.taskinbox.v1.domain.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.taskinbox.v1.domain.model.User;


@Configuration
@RequiredArgsConstructor
public class AdminUserConfig implements CommandLineRunner {


    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void run(String... args) throws Exception {

        var userAdmin = userRepository.findByMail("test@mail.com");


        userAdmin.ifPresentOrElse((user) -> {
            System.out.println("already an admin");
        },
                () -> {
                    var user = new User();
                    user.setMail("admin");
                    user.setPassword(bCryptPasswordEncoder.encode("123"));
                    user.setRole(Role.ADMIN);
                    userRepository.save(user);
                });

    }
}
