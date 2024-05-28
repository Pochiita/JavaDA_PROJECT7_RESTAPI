package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;
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
            @PostConstruct
            public BidList test(){
                BidList bid = new BidList();
                bid.setType("test");
                bid.setBidQuantity((double)13);
                bid.setAccount("Test");
                System.out.println(bid.getId());
                System.out.println(bidListRepository.save(bid));
                return bidListRepository.save(bid);
            }
   /* @Autowired
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
        User user = new User();
        user.setFullname("Hello");
        user.setUsername("test");
        user.setPassword(passwordEncoder.encode("test"));
        user.setRole("ADMIN");
        userRepository.save(user);
    }*/
}
