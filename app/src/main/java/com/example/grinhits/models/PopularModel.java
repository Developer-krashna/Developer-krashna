package com.example.grinhits.models;

public class PopularModel {

    private int image;
    private String name;
    private String price;
    private String weight;

    public PopularModel(int image, String name, String price, String weight) {
        this.image = image;
        this.name = name;
        this.price = price;
        this.weight = weight;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
}
