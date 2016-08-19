package com.perso.lunabeetest.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by arnaud on 16/08/16.
 */
public class UnsplashCard {

    private String author;
    private String imageUrl;

    public UnsplashCard() {

    }

    public UnsplashCard(String text, String imageUrl) {
        this.author = text;
        this.imageUrl = imageUrl;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String text) {
        this.author = text;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
