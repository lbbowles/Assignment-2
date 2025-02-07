package com.csc340.demo;

public class Digimon {
    public String name;
    public String image;
    public String level;

    public Digimon(String name, String image, String level) {
        this.name = name;
        this.image = image;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String type) {
        this.level = type;
    }
}
