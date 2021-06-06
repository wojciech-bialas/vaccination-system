package us.edu.pl;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.Lifecycle;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import us.edu.pl.user.domain.Role;
import us.edu.pl.user.domain.RoleRepository;
import us.edu.pl.user.domain.User;
import us.edu.pl.user.domain.UserRepository;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
@AllArgsConstructor
public class UsersInitialization {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostConstruct
    public void init() {
        final Role userRole = new Role(1L, "USER", null);
        final Role adminRole = new Role(2L, "ADMIN", null);

        roleRepository.save(userRole);
        roleRepository.save(adminRole);

        final User sampleUser = new User(
                97241200216L,
                "user",
                bCryptPasswordEncoder.encode("user"),
                "user@gmail.com",
                "505952012",
                "DXc-2S21-DV",
                new HashSet<>(Collections.singletonList(userRole)),
                Collections.emptySet(),
                Collections.emptySet()
        );

        userRepository.save(sampleUser);
    }

}
