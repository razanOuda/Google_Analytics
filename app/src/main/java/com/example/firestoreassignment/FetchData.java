package com.example.firestoreassignment;

import android.media.Image;

import java.io.Serializable;

public class FetchData implements Serializable {

    String title , adress;
    Image image;

    public FetchData(String title, String adress, Image image) {
        this.title = title;
        this.adress = adress;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
