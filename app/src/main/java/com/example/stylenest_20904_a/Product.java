package com.example.stylenest_20904_a;

public class Product {
    private String name;
    private String price;
    private int imageResource;
    private String category;

    public Product(String name, String price, int imageResource, String category) {
        this.name = name;
        this.price = price;
        this.imageResource = imageResource;
        this.category = category;
    }

    public String getName() { return name; }
    public String getPrice() { return price; }
    public int getImageResource() { return imageResource; }
    public String getCategory() { return category; }
}