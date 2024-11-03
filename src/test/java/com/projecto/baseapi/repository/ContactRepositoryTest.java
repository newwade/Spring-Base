package com.projecto.baseapi.repository;

import com.projecto.baseapi.constant.Role;
import com.projecto.baseapi.entity.User;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ObjectUtils;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;

@DataJpaTest
@Transactional
class ContactRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    private User user;
    @BeforeEach
    void init(){
        user = User.builder().firstName("mark").lastName("hamill").emailAddress("hamill@gmail.com")
                .password("hamillmark").roles(new HashSet<>(Collections.singleton(Role.USER))).build();
    }
    @AfterEach
    void teardown() {
        userRepository.delete(user);
    }
    @Test
    void shouldGetUserById(){
        User expected = userRepository.save(user);
        Optional<User> actual = userRepository.findById(expected.getId());
        assertEquals(expected.getId(), actual.get().getId());
    }

    @Test
    void shouldGetUserByEmailAddress(){
        User expected = userRepository.save(user);
        Optional<User> actual = userRepository.findByEmailAddress(expected.getEmailAddress());
        assertEquals(expected.getId(), actual.get().getId());
    }


}
