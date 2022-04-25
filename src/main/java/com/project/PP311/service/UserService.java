package com.project.PP311.service;

import com.project.PP311.model.User;

import java.util.List;

public interface UserService {
    List<User> getListUsers();
    User getUserById(long id);
    User getUserByName(String email);
    void addUser(User user);
    void updateUser(User newUser);
    void deleteUser(long id);
}
