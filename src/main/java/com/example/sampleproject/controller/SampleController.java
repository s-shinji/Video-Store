package com.example.sampleproject.controller;

import com.example.sampleproject.ImageForm;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SampleController {

	@ModelAttribute
    public ImageForm setForm() {
        return new ImageForm();
	}
	
    @RequestMapping("/sample")
    public String sample() {
        return "upload";
	}
	
	@PostMapping("/upload")
    public String upload(ImageForm imageForm, Model model) throws Exception {
        // System.out.println(imageForm.getImage().length);
        List<String> lists = new ArrayList<>();
        for(int i = 0; i < imageForm.getImage().length; i++) {
            StringBuffer data = new StringBuffer();
            String base64 = Base64.getEncoder().encodeToString(imageForm.getImage()[i].getBytes());
            data.append("data:image/jpeg;base64,");
            data.append(base64);
            lists.add(data.toString());
        }
        model.addAttribute("lists", lists);
        return "sample";
    }
}