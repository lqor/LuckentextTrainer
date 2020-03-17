package com.igor.course.controllers;

import com.igor.course.entity.Exercise;
import com.igor.course.services.TextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class AddTextController {
    private TextService textService;

    @RequestMapping("/add")
    public String addTextPage(Model model) {
        String username =  SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("username", username);

        return "add/addText";
    }


    @RequestMapping("/addNewText")
    public String addTextProcess(@RequestParam Map<String, String> reqParam, Model model) {
        String username =  SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("username", username);

        String text = reqParam.get("text");
        int points = Integer.parseInt(reqParam.get("points"));
        String difficulty = reqParam.get("difficulty");

        try {
            Exercise exercise = new Exercise(text, points, difficulty);
            exercise.setId(incString(textService.getLastId()));

            textService.add(exercise);
            return "add/addTextSucc";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/";
        }
    }

    @Autowired
    public void setTextService(TextService textService) {
        this.textService = textService;
    }

    private String incString(String i) {
        return String.valueOf(Integer.parseInt(i) + 1);
    }
}
