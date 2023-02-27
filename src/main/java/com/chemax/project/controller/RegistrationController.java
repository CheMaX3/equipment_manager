package com.chemax.project.controller;

import com.chemax.project.entities.User;
import com.chemax.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String showAddUserForm(User user) {
        return "registrationPage";
    }

    @PostMapping("/registration")
    public String addUser(@Valid User user, BindingResult result, Model model) {

        String err = userService.passwordConfirmation(user) + userService.userExists(user);

        if (!err.isEmpty()) {
            ObjectError error = new ObjectError("globalError", err);
            result.addError(error);
        }

        if (result.hasErrors()) {
            return "registrationPage";
        }

        userService.saveUser(user);
        model.addAttribute("users", userService.allUsers());
        return "redirect:/login";
    }
}
