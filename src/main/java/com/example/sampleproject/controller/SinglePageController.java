package com.example.sampleproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SinglePageController {
	@GetMapping("/**/{path:[^.]*}")
    public String any() {
        return "top";
    }
}