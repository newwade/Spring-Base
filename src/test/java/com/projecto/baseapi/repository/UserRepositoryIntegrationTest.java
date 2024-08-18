package com.projecto.baseapi.repository;


import com.projecto.baseapi.constant.Role;
import com.projecto.baseapi.entity.User;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

//@SpringBootTest
//@ActiveProfiles("dev")
@DataJpaTest
public class UserRepositoryIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void whenCalledSave_thenCorrectNumberOfUsers(){
        Set<Role> set = new HashSet<>();
        set.add(Role.USER);
        User user = User.builder().firstName("bruce").lastName("wayne").emailAddress("brucewayne@wayne.com")
                .roles(set).password("wayne2021").build();
        userRepository.save(user);
        List<User> users = userRepository.findAll();
        Assertions.assertEquals(1,users.size());
    }

}
