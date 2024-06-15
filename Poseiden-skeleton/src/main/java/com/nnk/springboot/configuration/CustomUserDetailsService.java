package com.nnk.springboot.configuration;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    /**

     This method overrides the loadUserByUsername method from the UserDetailsService interface.
     It loads a user by their username from the UserRepository and creates a UserDetails object using the User entity.
     If the user is not found, it throws a UsernameNotFoundException.
     @param username The username of the user to load
     @return UserDetails object representing the user
     @throws UsernameNotFoundException If the user is not found in the UserRepository */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getGrantedAuthorities(user.getRole()));
    }

    /**

     Returns a list of GrantedAuthority objects for the given role.
     @param role the role for which GrantedAuthority objects are to be created
     @return a list of GrantedAuthority objects containing a single SimpleGrantedAuthority object for the given role */
    private List<GrantedAuthority> getGrantedAuthorities(String role) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role));
        return authorities;
    }
}
