package com.chemax.project.controller;

import com.chemax.project.entities.User;
import com.chemax.project.request.SectionRequest;
import com.chemax.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String showRegistrationPage(Model model) {
        User userForm = new User();
        model.addAttribute("userForm", userForm);
        return "registrationPage";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("userForm")User userForm, Model model) {
        userService.saveUser(userForm);
        return "redirect:/login";
    }
}
