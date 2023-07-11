package com.projecto.baseapi.service;

import com.projecto.baseapi.entity.Contact;

import java.util.List;

public interface ContactService {
    Contact createContactService(Contact contact);
    Contact getContactService(long id);

    List<Contact> getContactUserService(long id);
}
