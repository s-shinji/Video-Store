package com.example.sampleproject.form;

import javax.validation.constraints.Size;

// import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

public class MovieForm {
    //動画を複数投稿する場合
    // private MultipartFile[] image;

    private MultipartFile movie;
    
    @Size(min = 1, max = 50, message="1~50字で指定してください")
    private String title;
    public MovieForm() {

    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    //動画を複数投稿する場合
    // public MultipartFile[] getImage() {
    //     return image;
    // }
    // public void setImage(MultipartFile[] image) {
    //     this.image = image;
    // }

    public MultipartFile getMovie() {
        return movie;
    }
    public void setMovie(MultipartFile movie) {
        this.movie = movie;
    }
}