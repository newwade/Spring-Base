package com.projecto.baseapi.service;

import com.projecto.baseapi.constant.LoginRequest;
import com.projecto.baseapi.entity.User;

import java.util.List;

public interface UserService {

    User registerAdmin(User user);
    User registerUserService(User user);

    User authenticateUser(LoginRequest user);
    User getUserService(long id);
    List<User> getAllUsersService();
}
