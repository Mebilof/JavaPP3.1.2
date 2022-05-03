package com.project.PP312.service;

import com.project.PP312.dao.RoleDao;
import com.project.PP312.model.Role;
import org.hibernate.HibernateException;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.project.PP312.dao.UserDao;
import com.project.PP312.model.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserDao userDao;

    private final RoleDao roleDao;

    private final PasswordEncoder passwordEncoder;

    @Lazy
    public UserServiceImpl(UserDao userDao, RoleDao roleDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> getListUsers() {
        return userDao.getListUsers();
    }

    @Override
    public User getUserById(long id) {
        return userDao.getUserById(id);
    }

    @Override
    public User getUserByName(String name) {
        return userDao.getUserByName(name);
    }

    @Transactional
    @Override
    public void addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.addUser(user);
    }

    @Transactional
    @Override
    public void updateUser(User user, String roleAdmin, String pass) {
        if (user.getUsername().length() < 1) {
            return;
        }

        Set<Role> roles = new HashSet<>();
        if (roleAdmin != null) {
            roles.add(roleDao.getRoleByName("ROLE_ADMIN"));
        }
        roles.add(roleDao.getRoleByName("ROLE_USER"));
        user.setRoles(roles);

        if (user.getPassword().equals("")){
            user.setPassword(userDao.getUserById(user.getId()).getPassword());
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userDao.updateUser(user);
    }

    @Transactional
    @Override
    public void deleteUser(long id) {
        userDao.deleteUser(id);
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        return userDao.getUserByName(name);
    }
}
