package com.example.sampleproject;

// import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

public class ImageForm {

    // private int[] userId;
    // @NotNull
    private MultipartFile[] image;

    public ImageForm() {

    }

    // public int[] getUserId() {
    //     return userId;
    // }

    // public void setUserId(int[] userId) {
    //     this.userId = userId;
    // }

    public MultipartFile[] getImage() {
        return image;
    }

    public void setImage(MultipartFile[] image) {
        this.image = image;
    }
}