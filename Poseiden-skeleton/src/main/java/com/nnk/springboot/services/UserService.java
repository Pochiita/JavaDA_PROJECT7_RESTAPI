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
        /**
         Encodes the raw password using the BCrypt hash algorithm.
         @param rawPassword the raw password to encode
         @return the encoded password */
        @Override
        public String encode(CharSequence rawPassword) {
            return BCrypt.hashpw(rawPassword.toString(), BCrypt.gensalt());
        }

        /**

         Matches the raw password with the encoded password using the BCrypt check algorithm.
         @param rawPassword the raw password to check
         @param encodedPassword the encoded password to match against
         @return true if the passwords match, false otherwise */
        @Override
        public boolean matches(CharSequence rawPassword, String encodedPassword) {
            return BCrypt.checkpw(rawPassword.toString(), encodedPassword);
        }
    };

    /**

     Saves a new user to the database by encoding the password before saving.
     @param user the user to save
     @return the saved user */

    public User saveUser (User user){
        User currentUser= user;
        currentUser.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(currentUser);
    }

    /**

     Updates an existing user in the database by encoding the new password before saving.
     @param user the updated user
     @param id the id of the user to update
     @return the updated user */
    public User updateUser (User user,Integer id){
        User userInDB = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        userInDB.setFullname(user.getFullname());
        userInDB.setUsername(user.getUsername());
        userInDB.setPassword(passwordEncoder.encode(user.getPassword()));
        userInDB.setRole(user.getRole());
        return userRepository.save(userInDB);
    }

    /**

     Deletes a user from the database based on the provided id.
     @param id the id of the user to delete */
    public void deleteUser (Integer id){
        User userInDB = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        userRepository.delete(userInDB);
    }


}
