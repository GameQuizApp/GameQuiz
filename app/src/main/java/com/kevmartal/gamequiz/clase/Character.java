package com.kevmartal.gamequiz.clase;

import java.util.List;

public class Character {
    private String name;
    private String imageUrl;

    // Constructor
    public Character(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
