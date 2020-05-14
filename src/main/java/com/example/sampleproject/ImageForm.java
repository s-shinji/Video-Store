package com.example.sampleproject;

// import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

public class ImageForm {
    // @NotNull
    private MultipartFile[] image;
    
    public ImageForm(){

    }

    public MultipartFile[] getImage() {
        return image;
    }

    public void setImage(MultipartFile[] image) {
        this.image = image;
    }
}