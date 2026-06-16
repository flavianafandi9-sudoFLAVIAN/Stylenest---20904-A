package com.example.stylenest_20904_a;

public class Product {
    private String name;
    private String price;
    private int imageResource;
    private String category;
    private String selectedSize = "M"; // Default
    private int quantity = 1; // Default

    public Product(String name, String price, int imageResource, String category) {
        this.name = name;
        this.price = price;
        this.imageResource = imageResource;
        this.category = category;
    }

    public Product(String name, String price, int imageResource, String category, String selectedSize, int quantity) {
        this.name = name;
        this.price = price;
        this.imageResource = imageResource;
        this.category = category;
        this.selectedSize = selectedSize;
        this.quantity = quantity;
    }

    public String getName() { return name; }
    public String getPrice() { return price; }
    public int getImageResource() { return imageResource; }
    public String getCategory() { return category; }
    public String getSelectedSize() { return selectedSize; }
    public void setSelectedSize(String selectedSize) { this.selectedSize = selectedSize; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}