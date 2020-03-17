package com.igor.course.controllers;

import com.igor.course.entity.Exercise;
import com.igor.course.entity.Role;
import com.igor.course.entity.User;
import com.igor.course.services.AuthorityService;
import com.igor.course.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Controller
public class AccountController {
    private UserService userService;
    private AuthorityService authorityService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping("/account")
    public String signForm(Model model) {
        String username =  SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userService.get(username);
        model.addAttribute("username", username);

        if(SecurityContextHolder.getContext().getAuthentication().isAuthenticated() && userService.get(username) != null) {
            model.addAttribute("doneexercises", parseExercises(currentUser.getExercises()));
            model.addAttribute("points", currentUser.getPoints());
        }


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
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setAuthorityService(AuthorityService authorityService) {
        this.authorityService = authorityService;
    }

    private String parseExercises(Set<Exercise> exercises) {
        Iterator<Exercise> iter = exercises.iterator();
        StringBuilder str = new StringBuilder();

        int i = 0;
        while (iter.hasNext()) {
            str.append(iter.next().getId());
            str.append(", ");
        }

        delete2LastChars(str);


        return str.toString();
    }

    private StringBuilder delete2LastChars(StringBuilder stringBuilder) {

        if(stringBuilder.length() > 2) {
            stringBuilder.deleteCharAt(stringBuilder.length()-1);
            stringBuilder.deleteCharAt(stringBuilder.length()-1);
        }

        return stringBuilder;
    }
}
