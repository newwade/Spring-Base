package com.projecto.baseapi.repository;

import com.projecto.baseapi.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact,Long> {
    List<Contact> findAllByUserId(long id);
}
