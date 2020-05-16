package com.example.sampleproject.controller;

import com.example.sampleproject.ImageForm;
import com.example.sampleproject.entity.Movie;
import com.example.sampleproject.service.MovieService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SampleController {

    private final MovieService movieService;

    @Autowired
    public SampleController(MovieService movieService){
        this.movieService = movieService;
    }


    @ModelAttribute
    public ImageForm setForm() {
        return new ImageForm();
    }
    
    @RequestMapping("/upload")
    public String upload() {
        return "upload";
    }

    @GetMapping("/index")
    public String index(Model model, RedirectAttributes redirectAttributes){
        List<Movie> list = movieService.getAll();
        List<String> list2 = new ArrayList<>();
        for(int i = 0; i < list.size(); i++) {
            StringBuffer data = new StringBuffer();
            String base64 = Base64.getEncoder().encodeToString(list.get(i).getMovie());
            // data.append("data:image/jpeg;base64,");
            //mp4にしか対応していない
            data.append("data:video/mp4;base64,");
            data.append(base64);
            list2.add(data.toString());
        }
        model.addAttribute("movieList", list);
        model.addAttribute("movieList2", list2);
        return "index";
    }
    
    @PostMapping("/upload")
    public String upload(@Validated ImageForm imageForm, BindingResult result, Model model ) throws Exception {
        if(result.hasErrors()) {
            return "upload";
        }
        Movie movie = new Movie();
        for(int i = 0; i < imageForm.getImage().length; i++) {
            // StringBuffer data = new StringBuffer();
            // String base64 = Base64.getEncoder().encodeToString(imageForm.getImage()[i].getBytes());
            // data.append("data:image/jpeg;base64,");
            // data.append(base64);
            movie.setMovie(imageForm.getImage()[i].getBytes());
            movie.setCreated(LocalDateTime.now());
            movieService.save(movie);
        }
        return "redirect:/index";
    }
    
}