package com.vandal.dbconnect.stuff;

public class Product {
    private int partNumber;
    private String name;
    private String description;
    private boolean isForSale;
    private double price;

    public Product(int partNumber, String name, String description, boolean isForSale, double price) {
        this.partNumber = partNumber;
        this.name = name;
        this.description = description;
        this.isForSale = isForSale;
        this.price = price;
    }

    // Getters and setters for the attributes

    public int getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(int partNumber) {
        this.partNumber = partNumber;
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

    public boolean isForSale() {
        return isForSale;
    }

    public void setForSale(boolean forSale) {
        isForSale = forSale;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}


