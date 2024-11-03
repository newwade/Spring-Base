package com.projecto.baseapi.controller;

import com.projecto.baseapi.constant.LoginRequest;
import com.projecto.baseapi.constant.LoginResponse;
import com.projecto.baseapi.constant.RegisterRequest;
import com.projecto.baseapi.constant.RegisterResponse;
import com.projecto.baseapi.entity.User;
import com.projecto.baseapi.service.JWTService;
import com.projecto.baseapi.service.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin(origins = "http://localhost:3000",methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    private final JWTService jwtService;

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(UserService userService, JWTService jwtService){
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> registerUser(@RequestBody RegisterRequest user){
        return new ResponseEntity<>(userService.registerUserService(user), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@Valid @RequestBody LoginRequest loginUserDto) {
        User authenticatedUser = userService.authenticateUser(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);
        LoginResponse loginResponse = LoginResponse.builder().token(jwtToken)
                .expirationTime(jwtService.getExpirationTime()).build();
        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/register/admin")
    public ResponseEntity<User> registerAdmin(@RequestBody User user){
        return new ResponseEntity<>(userService.registerAdmin(user), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserController(@PathVariable Long id){
        logger.info("UserController:getUserController:API");
        return new ResponseEntity<>(userService.getUserService(id),HttpStatus.OK);
    }

    @GetMapping("/profile")
    public ResponseEntity<User> getUserProfile(){
        logger.info("UserController:getUserProfile:API");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        return new ResponseEntity<>(currentUser,HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsersController(){
        return new ResponseEntity<>(userService.getAllUsersService(),HttpStatus.OK);
    }

}
