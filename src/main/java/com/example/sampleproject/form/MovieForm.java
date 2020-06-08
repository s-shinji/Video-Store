package com.example.sampleproject.form;

import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

public class MovieForm {
    private MultipartFile movie;
    private MultipartFile thumbnail;

    @Size(min = 1, max = 50, message = "1~50字で指定してください")
    private String title;

    public MovieForm() {

    }

    public MultipartFile getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(MultipartFile thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public MultipartFile getMovie() {
        return movie;
    }
    public void setMovie(MultipartFile movie) {
        this.movie = movie;
    }
}