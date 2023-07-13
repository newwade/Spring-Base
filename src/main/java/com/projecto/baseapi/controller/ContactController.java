package com.projecto.baseapi.controller;

import com.projecto.baseapi.entity.Contact;
import com.projecto.baseapi.entity.User;
import com.projecto.baseapi.service.ContactService;
import com.projecto.baseapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/contact")
public class ContactController {
    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService){
        this.contactService = contactService;
    }

    @PostMapping("/save")
    public ResponseEntity<Contact> createContact(@RequestBody Contact contact){
        return new ResponseEntity<>(contactService.createContactService(contact), HttpStatus.CREATED);
    }

    @GetMapping("/usr/{id}")
    public ResponseEntity<List<Contact>> getContactUser(@PathVariable Long id){
        return new ResponseEntity<>(contactService.getContactUserService(id),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getContact(@PathVariable Long id){
        return new ResponseEntity<>(contactService.getContactService(id),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteContact(@PathVariable Long id){
        contactService.deleteContactService(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
