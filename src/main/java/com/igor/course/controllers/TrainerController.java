package com.igor.course.controllers;

import com.igor.course.common.TextParser;
import com.igor.course.entity.Exercise;
import com.igor.course.entity.User;
import com.igor.course.services.UserService;
import com.igor.course.services.TextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/course")
public class TrainerController {
    private TextService textService;
    private UserService userService;

    @RequestMapping("/ex")
    public String ex(@RequestParam("number") String var, Model model) {
        Exercise exercise = textService.get(var);

        if(!var.equals(exercise.getId())) System.out.println("Fehler!!!!!!!!!!!!!!!!!!!!!!!!!");

        model.addAttribute("number", var);
        model.addAttribute("points", exercise.getPoints());
        model.addAttribute("text", TextParser.parse(exercise.getText()));
        model.addAttribute("answers", TextParser.getAnswers());
        model.addAttribute("allDone", false);
        model.addAttribute("falseForms", new ArrayList<>());

        return "exercises/exercise_template";
    }

    @RequestMapping("")
    public String goToCourse(Model model) {
        String username =  SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("username", username);
        model.addAttribute("texts", textService.getAll());

        ArrayList<String> doneCourses;
        if(userNotAnonoum()) {
            doneCourses = getCoursesId(userService.get(username).getExercises());
        } else {
            doneCourses = new ArrayList<>();
        }

        model.addAttribute("idOfDoneCourses", doneCourses);

        return "course/course";
    }

    @RequestMapping("/ex/processform")
    public String processForm(
            @RequestParam Map<String, String> reqParam,
            Model model
    ) {
        String id = reqParam.get("number");
        Exercise exercise = textService.get(String.valueOf(id));

        model.addAttribute("text", TextParser.parse(exercise.getText())); //the whole text
        reqParam.remove("_csrf"); //no need in map, its just spring-security parameter

        String username =  SecurityContextHolder.getContext().getAuthentication().getName(); // its our id for current user
        User user = userService.get(username);

        ArrayList<Integer> falseForms = testInputs(id, reqParam);

        if(falseForms.size() == 0) {
            int points = Integer.parseInt(reqParam.get("points"));

            if(userNotAnonoum() && !userAlreadyDoneExercise(username, id)) {
                userService.setPoints(username, user.getPoints() + points);
                userService.addExercise(username, exercise);
                textService.addUser(String.valueOf(id), user);
            } else {
                model.addAttribute("userAlreadyDoneThisExercises", true);
            }

            model.addAttribute("allDone", true);
        }

        model.addAttribute("number", exercise.getId());
        model.addAttribute("points", exercise.getPoints());
        model.addAttribute("falseForms", falseForms);


        return "exercises/exercise_template";
    }

    @Autowired
    public void setTextService(TextService textService) {
        this.textService = textService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    private boolean userNotAnonoum() {
        return SecurityContextHolder.getContext().getAuthentication() != null &&
                SecurityContextHolder.getContext().getAuthentication().isAuthenticated() &&
                //when Anonymous Authentication is enabled
                !(SecurityContextHolder.getContext().getAuthentication()
                        instanceof AnonymousAuthenticationToken);
    }

    /*
    * forms and answers must have the same ordering
    * Thit method return an array wit number, taht represents wich form is not valid
    * */
    private ArrayList<Integer> testInputs(String id, Map<String, String> reqParam) {
        ArrayList<Integer> res = new ArrayList<>();

        // parse the same text to get answers to forms
        ArrayList<String> answers = TextParser.getAnswers(textService.get(id).getText());

        Iterator<String> iter = reqParam.keySet().iterator();
        int i = 0;
        iter.next(); // skip "number="-param
        iter.next(); // skip "points="-param
        while (iter.hasNext()) {
            String value = reqParam.get(iter.next());
            if(!value.equals(answers.get(i))) {
                res.add(i);
            }

            i++;
        }

        return res;
    }

    //Return all exercises-id
    private ArrayList<String> getCoursesId(Set<Exercise> exercises) {
        ArrayList<String> res = new ArrayList<>();

        for (Exercise exercise : exercises) {
            res.add(exercise.getId());
        }

        return res;
    }

    // Test if user with userId already done exercise
    private boolean userAlreadyDoneExercise(String userId, String exerciseId) {
        Set<Exercise> exercises = userService.get(userId).getExercises();

        for (Exercise exercise : exercises) {
            if (exercise.getId().equals(exerciseId))
                return true;
        }

        return false;
    }
}
