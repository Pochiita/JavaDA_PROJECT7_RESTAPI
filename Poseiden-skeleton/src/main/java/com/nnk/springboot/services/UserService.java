package com.nnk.springboot.services;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public final PasswordEncoder passwordEncoder = new PasswordEncoder() {
        @Override
        public String encode(CharSequence rawPassword) {
            return BCrypt.hashpw(rawPassword.toString(), BCrypt.gensalt());
        }

        @Override
        public boolean matches(CharSequence rawPassword, String encodedPassword) {
            return BCrypt.checkpw(rawPassword.toString(), encodedPassword);
        }
    };

    public User saveUser (User user){
        User currentUser= user;
        currentUser.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(currentUser);
    }

    public User updateUser (User user,Integer id){
        User userInDB = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        userInDB.setFullname(user.getFullname());
        userInDB.setUsername(user.getUsername());
        userInDB.setPassword(passwordEncoder.encode(user.getPassword()));
        userInDB.setRole(user.getRole());
        return userRepository.save(userInDB);
    }

    public void deleteUser (Integer id){
        User userInDB = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        userRepository.delete(userInDB);
    }


}
