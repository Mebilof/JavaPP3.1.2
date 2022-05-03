package com.project.PP312.service;

import com.project.PP312.model.Role;
import com.project.PP312.model.User;

import java.util.List;

public interface UserService {
    List<User> getListUsers();
    User getUserById(long id);
    User getUserByName(String email);
    void addUser(User user);
    void updateUser(User user, String roleAdmin, String pass);
    void deleteUser(long id);
}
