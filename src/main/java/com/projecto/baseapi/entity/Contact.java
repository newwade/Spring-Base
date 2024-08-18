package com.projecto.baseapi.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.projecto.baseapi.custom.GenderValidation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "contacts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    @GenderValidation()
    private String gender;
    private String mobilePhone;
    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    @JsonBackReference
    private User user;

}
