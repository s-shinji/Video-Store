package com.example.sampleproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//springとreactでurlがかぶらないようにするため追加
@RequestMapping("/KdiJ362")
public class SinglePageController {
	@GetMapping("/**/{path:[^.]*}")
    public String any() {
        return "forward:/index.html";
    }
}