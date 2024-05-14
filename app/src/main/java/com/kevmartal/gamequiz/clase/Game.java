package com.kevmartal.gamequiz.clase;

import java.util.List;

public class Game {
    private String name;
    private String imageUrl;
    private List<String> categories;

    // Constructor
    public Game(String name, String imageUrl, List<String> categories) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.categories = categories;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public List<String> getCategories() {return categories;}
}
