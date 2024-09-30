package com.projecto.baseapi.service;

import com.projecto.baseapi.constant.LoginRequest;
import com.projecto.baseapi.constant.Role;
import com.projecto.baseapi.entity.User;
import com.projecto.baseapi.exception.RecordNotFoundException;
import com.projecto.baseapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl  implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    private final AuthenticationManager authenticationManager;

    @Autowired
    public UserServiceImpl(PasswordEncoder passwordEncoder,UserRepository userRepository, AuthenticationManager authenticationManager){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public User registerUserService(User user) {
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(Role.USER);
        user.setRoles(roles);
        return userRepository.save(user);
    }



    @Override
    public User registerAdmin(User user){
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(Role.USER);
        roles.add(Role.ADMIN);
        user.setRoles(roles);
        return userRepository.save(user);
    }

    @Override
    public User authenticateUser(LoginRequest user){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        user.getPassword()
                )
        );
        return userRepository.findByEmailAddress(user.getUsername())
                .orElseThrow();
    }

    @Override
    public User getUserService(long id) {
        return userRepository.findById(id).orElseThrow(()-> new RecordNotFoundException("user not found for id : "+id));
    }

    @Override
    public List<User> getAllUsersService() {
        return userRepository.findAll();
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<User> user = userRepository.findByEmailAddress(username);
//        if(!user.isEmpty()){
//            return user.get();
//        }
//        throw new UsernameNotFoundException("Invalid Username or Password");
//    }
}
