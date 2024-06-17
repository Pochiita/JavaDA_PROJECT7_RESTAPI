package com.nnk.springboot.services;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class BaseUserService {

    @Autowired
    BidListRepository bidListRepository;

    @Autowired
    UserRepository userRepository;
    private final PasswordEncoder passwordEncoder = new PasswordEncoder() {
        @Override
        public String encode(CharSequence rawPassword) {
            return BCrypt.hashpw(rawPassword.toString(), BCrypt.gensalt());
        }

        @Override
        public boolean matches(CharSequence rawPassword, String encodedPassword) {
            return BCrypt.checkpw(rawPassword.toString(), encodedPassword);
        }
    };
    @PostConstruct
    void createBaseUser (){
        User isUserExisting = userRepository.findByUsername("dev");
        if (isUserExisting == null) {
            User user = new User();
            user.setFullname("Dev user");
            user.setUsername("dev");
            user.setPassword(passwordEncoder.encode("y1T£7?`8=4~{"));
            user.setRole("ADMIN");
            userRepository.save(user);
        }
    }
}
