package com.project.PP312.controller;

import com.project.PP312.model.Role;
import com.project.PP312.model.User;
import com.project.PP312.service.RoleService;
import com.project.PP312.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@Controller
public class AdminController {

    private final UserService userService;

    private final RoleService roleService;

    AdminController(UserService userService, RoleService roleService) {
        this.roleService = roleService;
        this.userService = userService;
    }
        @GetMapping("/admin")
    public String printUsers(ModelMap model) {
        model.addAttribute("users", userService.getListUsers());
        return "users";
    }

    @GetMapping("/admin/new")
    public String addUser(ModelMap model) {
        model.addAttribute("user", new User());
        return "newUser";
    }

    @PostMapping("/admin/new")
    public String createUser(@ModelAttribute("user") User user, @RequestParam("newRoles[]") String[] roles) {
        Set<Role> roleSet = new HashSet<>();
        for (String role: roles){
            roleSet.add(roleService.getRoleByName(role));
        }
        user.setRoles(roleSet);
        userService.addUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/{id}/edit")
    public String goToPageEditUser(@PathVariable("id") int id, ModelMap model) {
        model.addAttribute("user", userService.getUserById(id));
        return "editUser";
    }

    @PostMapping ("/admin/{id}/edit")
    public String updateUser(@RequestParam(name = "role_admin", required = false) String roleAdmin,
                             @RequestParam(name = "password", required = false) String pass,
                             @ModelAttribute("user") User user) {
        System.out.println(user.toString());
        userService.updateUser(user, roleAdmin, pass);
        return "redirect:/admin";
    }

    @GetMapping("/admin/{id}/delete")
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
