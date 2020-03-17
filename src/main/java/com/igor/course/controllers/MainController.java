package com.igor.course.controllers;

import com.igor.course.entity.User;
import com.igor.course.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class MainController {
    private final UserService userService;

    @Autowired
    public MainController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/")
    public String index(Model model) {
        String username =  SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("username", username);

        return "index";
    }

    @RequestMapping("/top")
    public String getTop(Model model) {
        String username =  SecurityContextHolder.getContext().getAuthentication().getName();
        List<User> users = userService.getAll();

        model.addAttribute("username", username);
        model.addAttribute("top_users", users);

        return "/top/top";
    }
}
