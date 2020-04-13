package org.SafnaRafic.emergencydisasterhelpline.models.data;

import org.SafnaRafic.emergencydisasterhelpline.models.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class DbInit implements CommandLineRunner {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public DbInit(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }



    @Override
    public void run(String... args) throws Exception {
        //delete all
        this.userRepository.deleteAll();
        //create new user
        User user= new User("user",passwordEncoder.encode("user"), "USER","");
        User admin= new User("admin",passwordEncoder.encode("admin"), "ADMIN","ACCESS_TEST1,ACCESS_TEST2");
        User coordinator= new User("coordinator", passwordEncoder.encode("coordinator"), "COORDINATOR","ACCESS_TEST1");

        List<User> users= Arrays.asList(user,admin,coordinator);
        //save to db
        this.userRepository.saveAll(users);
    }
}
