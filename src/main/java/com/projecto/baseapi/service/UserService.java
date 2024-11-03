package com.projecto.baseapi.service;

import com.projecto.baseapi.constant.LoginRequest;
import com.projecto.baseapi.constant.RegisterRequest;
import com.projecto.baseapi.constant.RegisterResponse;
import com.projecto.baseapi.entity.User;

import java.util.List;

public interface UserService {

    User registerAdmin(User user);
    RegisterResponse registerUserService(RegisterRequest user);

    User authenticateUser(LoginRequest user);
    User getUserService(long id);
    List<User> getAllUsersService();
}
