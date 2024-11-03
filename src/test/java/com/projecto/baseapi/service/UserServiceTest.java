package com.projecto.baseapi.service;

import com.projecto.baseapi.constant.RegisterRequest;
import com.projecto.baseapi.constant.RegisterResponse;
import com.projecto.baseapi.constant.Role;
import com.projecto.baseapi.entity.User;
import com.projecto.baseapi.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;

    @BeforeEach
    void init(){
        user = User.builder().id(1).firstName("mark").lastName("hamill").emailAddress("hamill@gmail.com")
                .password("plainpass").roles(new HashSet<>(Collections.singleton(Role.USER))).build();
    }

    @Test
     void whenSaveReturnUser(){
        Mockito.when(passwordEncoder.encode("plainpass")).thenReturn("encodedpass");
        Mockito.when(userRepository.save(Mockito.any())).thenReturn(user);
        RegisterResponse actual_user = userService.registerUserService(RegisterRequest.builder().emailAddress(user.getEmailAddress())
                .firstName(user.getFirstName()).lastName(user.getLastName())
                        .password("plainpass")
                .roles(user.getRoles()).contacts(user.getContacts()).build());
        assertEquals(user.getEmailAddress(),actual_user.getEmailAddress());
    }

    @Test
     void getUserForId(){
        long employee_id = 1L;
        Mockito.when(userRepository.findById(employee_id)).thenReturn(Optional.ofNullable(user));
        User actual_user = userService.getUserService(employee_id);
        assertEquals(employee_id,actual_user.getId());

    }


}
