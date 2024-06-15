package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    /**

     This method is used to display the list of users.
     @param model the Model object to add attributes to
     @return the name of the HTML template to be displayed (user/list) **/
    @RequestMapping("/user/list")
    public String home(Model model)
    {
        model.addAttribute("users", userRepository.findAll());
        return "user/list";
    }

    /**

     This method is used to display the form to add a new user.
     @param user the User object to be added
     @return the name of the HTML template to be displayed (user/add) **/
    @GetMapping("/user/add")
    public String addUser(User user) {
        return "user/add";
    }

    /**

     This method is used to validate the user input and save the user data.
     @param user the User object to be validated and saved
     @param result the BindingResult object to check for validation errors
     @param model the Model object to add attributes
     @return the redirection URL if successful, or the name of the HTML template to be displayed (user/add) if there are errors **/
    @PostMapping("/user/validate")
    public String validate(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors() || result.hasFieldErrors()){
            model.addAttribute("user",user);
            return "/user/add";
        }
        userService.saveUser(user);

        return "redirect:/user/list";
    }

    /**

     This method is used to display the form to update an existing user.
     @param id the Integer value of the user identifier
     @param model the Model object to be modified
     @return the name of the HTML template to be displayed (user/update) **/
    @GetMapping("/user/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        user.setPassword("");
        model.addAttribute("user", user);
        return "user/update";
    }

    /**

     This method is used to update an existing user with new data.
     @param id the Integer value of the user identifier
     @param user the User object to be updated
     @param result the BindingResult object to check for validation errors
     @param model the Model object to be modified
     @return the redirection URL if successful, or the name of the HTML template to be displayed (user/add) if there are errors **/
    @PostMapping("/user/update/{id}")
    public String updateUser(@PathVariable("id") Integer id, @Valid User user,
                             BindingResult result, Model model) {
        if (result.hasErrors() || result.hasFieldErrors()){
            model.addAttribute("user",user);
            return "/user/add";
        }
        userService.updateUser(user,id);
        return "redirect:/user/list";
    }

    /**

     This method is used to delete an existing user.
     @param id the Integer value of the user identifier
     @param model the Model object to be modified
     @return the redirection URL to the user list **/
    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model) {
        userService.deleteUser(id);
        return "redirect:/user/list";
    }
}
