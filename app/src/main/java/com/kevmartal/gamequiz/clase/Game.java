package com.kevmartal.gamequiz.clase;

public class Game {
    private String name;
    private String imageUrl;

    // Constructor
    public Game(String name, String imageUrl) {
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
