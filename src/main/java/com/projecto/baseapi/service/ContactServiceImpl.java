package com.projecto.baseapi.service;

import com.projecto.baseapi.entity.Contact;
import com.projecto.baseapi.exception.RecordNotFoundException;
import com.projecto.baseapi.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {
    private final ContactRepository contactRepository;

    @Autowired
    public ContactServiceImpl(ContactRepository contactRepository){
        this.contactRepository = contactRepository;
    }

    @Override
    public Contact createContactService(Contact contact) {
        return contactRepository.save(contact);
    }

    @Override
    public Contact getContactService(long id) {
        return contactRepository.findById(id).orElseThrow(()->new RecordNotFoundException("contact not found for id : "+id));
    }

    @Override
    public List<Contact> getContactUserService(long id) {
        return contactRepository.findAllByUserId(id);
    }
}
