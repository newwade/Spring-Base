package com.projecto.baseapi.controller;

import com.projecto.baseapi.entity.User;
import com.projecto.baseapi.repository.UserRepository;
import com.projecto.baseapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user){
        return new ResponseEntity<>(userService.registerUserService(user), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserController(@PathVariable Long id){
        return new ResponseEntity<>(userService.getUserService(id),HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsersController(){
        return new ResponseEntity<>(userService.getAllUsersService(),HttpStatus.OK);
    }

}
