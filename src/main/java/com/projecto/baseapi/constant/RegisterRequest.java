package com.projecto.baseapi.constant;

import com.projecto.baseapi.entity.Contact;
import lombok.*;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {
    private String emailAddress;
    private String password;
    private String firstName;
    private String lastName;
    private Set<Role> roles;
    private List<Contact> contacts;
}
