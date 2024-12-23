package com.example.cafe3.model;

public class Product {
    private String icon; // URL-адрес изображения
    private String name;
    private String description;
    private double price;

    public Product() {
        // Default constructor required for calls to DataSnapshot.getValue(Product.class)
    }

    public Product(String icon, String name, String description, double price) {
        this.icon = icon;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
