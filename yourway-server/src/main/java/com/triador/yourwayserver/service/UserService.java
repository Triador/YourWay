package com.triador.yourwayserver.service;

import com.triador.yourwayserver.dao.repo.UserRepository;
import com.triador.yourwayserver.dao.model.User;
import com.triador.yourwayserver.enumeration.ErrorMessage;
import com.triador.yourwayserver.exception.CommonException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder bcryptEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bcryptEncoder) {
        this.userRepository = userRepository;
        this.bcryptEncoder = bcryptEncoder;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByName(username).orElseThrow(() -> new CommonException(ErrorMessage.ERROR_NOT_FOUND.getMessage()));
        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), getAuthority(user));
    }

    private List<SimpleGrantedAuthority> getAuthority(User user) {
        return Arrays.asList(new SimpleGrantedAuthority(user.getRole()));
    }

    public User save(User user) {
        User newUser = new User();
        newUser.setName(user.getName());
        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        newUser.setRole("ROLE_USER");
        return userRepository.save(newUser);
    }

    public User findById(int id) {
        return userRepository.findById(id).orElseThrow(() -> new CommonException(ErrorMessage.ERROR_NOT_FOUND.getMessage()));
    }

    public User findByName(String name) {
        return userRepository.findByName(name).orElseThrow(() -> new CommonException(ErrorMessage.ERROR_NOT_FOUND.getMessage()));
    }
}
