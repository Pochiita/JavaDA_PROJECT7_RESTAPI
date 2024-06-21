package com.nnk.springboot;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.services.UserService;
import org.junit.Assert;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserTests {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    static int userId;

    @Test
    @Order(1)

    public  void saveAndUpdateRuleName(){
        User user = new User();
        user.setUsername("UserForTest");
        user.setFullname("UserForTest");
        user.setPassword(userService.passwordEncoder.encode("SecurePassw0rd!"));
        user.setRole("USER");
        User saveUser = userService.saveUser(user);
        Assert.assertEquals(user,saveUser);
        user.setUsername("Updated for test");
        user.setFullname("Updated for test");
        User afterUpdate = userService.saveUser(saveUser);
        userId = afterUpdate.getId();
        Assert.assertEquals("Updated for test", afterUpdate.getUsername());
        Assert.assertEquals("Updated for test", afterUpdate.getFullname());

    }
    @Test
    @Order(2)

    public void deleteRuleName(){
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()) {
            userService.deleteUser(userId);
            Assert.assertFalse(userRepository.findById(userId).isPresent());
        }
    }

}
