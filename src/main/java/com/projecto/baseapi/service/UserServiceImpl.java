package com.projecto.baseapi.service;

import com.projecto.baseapi.constant.Role;
import com.projecto.baseapi.entity.User;
import com.projecto.baseapi.exception.RecordNotFoundException;
import com.projecto.baseapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl  implements UserService, UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(PasswordEncoder passwordEncoder,UserRepository userRepository){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User registerUserService(User user) {
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        Set<Role> roles = user.getRoles();
        roles.add(Role.USER);
        user.setRoles(roles);
        return userRepository.save(user);
    }

    @Override
    public User getUserService(long id) {
        return userRepository.findById(id).orElseThrow(()-> new RecordNotFoundException("user not found for id : "+id));
    }

    @Override
    public List<User> getAllUsersService() {
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmailAddress(username);
        if(!user.isEmpty()){
            return user.get();
        }
        else{
            throw new UsernameNotFoundException("Invalid Username or Password");
        }    }
}
