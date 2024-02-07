package com.example.GroceryListMaker.model;

import jakarta.validation.constraints.NotNull;

public class Product {

    private int productId;

    private String productName;

    public Product(String productName) {
        this.productName = productName;

    }

    public Product(){

    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }
}
