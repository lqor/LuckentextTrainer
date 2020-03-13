package com.igor.course.controllers;

import com.igor.course.entity.Role;
import com.igor.course.entity.User;
import com.igor.course.services.AuthorityServiceImpl;
import com.igor.course.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AccountController {
    private UserServiceImpl userService;
    private AuthorityServiceImpl authorityService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping("/account")
    public String signForm(Model model) {
        String username =  SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("username", username);

        return "account/account";
    }

    @RequestMapping("/login")
    public String loginpage() {
        return "account/login";
    }

    @RequestMapping("/addnewuser")
    public String addNewUser() {
        return "account/addnewuser";
    }

    @RequestMapping("/createNewUser")
    public String createNewUser(
            @RequestParam("username") String name,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            Model model
            ) {

        boolean succCreateUser = true;
        try {
            userService.add(new User(name, password, email, 0, false, false,true));
            authorityService.add(new Role(name, "ROLE_USER"));
        } catch (Exception e) {
            e.printStackTrace();
            succCreateUser = false;
        }

        model.addAttribute("succCreateUser", succCreateUser);

        return "account/addnewuser";
    }

    @Autowired
    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Autowired
    public void setAuthorityService(AuthorityServiceImpl authorityService) {
        this.authorityService = authorityService;
    }
}
