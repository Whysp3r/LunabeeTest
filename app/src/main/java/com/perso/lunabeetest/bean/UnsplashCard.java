package com.perso.lunabeetest.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by arnaud on 16/08/16.
 */
public class UnsplashCard implements Parcelable {

    private String author;
    private String imageUrl;

    public UnsplashCard() {

    }

    public UnsplashCard(String text, String imageUrl) {
        this.author = text;
        this.imageUrl = imageUrl;
    }

    protected UnsplashCard(Parcel in) {
        author = in.readString();
        imageUrl = in.readString();
    }

    public static final Creator<UnsplashCard> CREATOR = new Creator<UnsplashCard>() {
        @Override
        public UnsplashCard createFromParcel(Parcel in) {
            return new UnsplashCard(in);
        }

        @Override
        public UnsplashCard[] newArray(int size) {
            return new UnsplashCard[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(author);
        parcel.writeString(imageUrl);
    }
}
