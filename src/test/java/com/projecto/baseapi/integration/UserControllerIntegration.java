package com.projecto.baseapi.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projecto.baseapi.constant.RegisterRequest;
import com.projecto.baseapi.constant.RegisterResponse;
import com.projecto.baseapi.constant.Role;
import com.projecto.baseapi.entity.User;
import jakarta.annotation.PostConstruct;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;
import java.util.HashSet;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = {
        "security.jwt.secret-key=test-secret-key",
        "security.jwt.expiration-time=3600000"
})
class UserControllerIntegration {
    @Autowired
    private MockMvc mockMvc;
    private RegisterRequest user;
    private ObjectMapper mapper = new ObjectMapper().findAndRegisterModules().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);

    @PostConstruct
    void init(){
        user = RegisterRequest.builder().firstName("mark").lastName("hamill").emailAddress("hamill@gmail.com")
                .password("plainpass").roles(new HashSet<>(Collections.singleton(Role.USER))).build();
    }

    @Test
    @DisplayName("Happy Path Test : save user and return user")
    void givenCorrectUserDTO_whenSaveUser_thenReturnUserDTO() throws Exception {
        RegisterResponse actual_user =  mapper.readValue(mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/user/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(user)))
                 .andExpect(status().isCreated())
                 .andReturn()
                 .getResponse()
                 .getContentAsString(), RegisterResponse.class);
        Assertions.assertEquals(user.getEmailAddress(),actual_user.getEmailAddress());
    }
}
