package com.projecto.baseapi.constant;

import com.projecto.baseapi.entity.Contact;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterResponse {
    private String emailAddress;
    private String firstName;
    private String lastName;
    private Set<Role> roles;
    private List<Contact> contacts;
}
