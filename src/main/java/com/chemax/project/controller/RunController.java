package com.chemax.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RunController {

    @GetMapping("/hello")
    public String showIndex() {
        return "index";
    }
}
