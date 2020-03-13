package com.igor.course.controllers;

import com.igor.course.entity.User;
import com.igor.course.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class MainController {
    private final UserServiceImpl userService;

    @Autowired
    public MainController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @RequestMapping("/")
    public String index(Model model) {
        String username =  SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("username", username);

        return "index";
    }

    @RequestMapping("/course")
    public String goToCourse(Model model) {
        String username =  SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("username", username);

        return "course/course";
    }

    @RequestMapping("/info")
    public String info(Model model) {
        String username =  SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("username", username);

        return "info/info";
    }

    @RequestMapping("/top")
    public String getTop(Model model) {
        String username =  SecurityContextHolder.getContext().getAuthentication().getName();
        List<User> users = userService.getAll();

        model.addAttribute("username", username);
        model.addAttribute("top_users", users);

        return "/top/top";
    }

    @RequestMapping("/add")
    public String addText(Model model) {
        String username =  SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("username", username);

        return "add/add";
    }
}
