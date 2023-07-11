package com.projecto.baseapi.service;

import com.projecto.baseapi.entity.User;

import java.util.List;

public interface UserService {
    User registerUserService(User user);
    User getUserService(long id);
    List<User> getAllUsersService();
}
