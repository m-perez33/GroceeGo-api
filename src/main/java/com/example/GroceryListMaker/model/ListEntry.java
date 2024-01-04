package com.example.GroceryListMaker.model;

public class ListEntry {

    private int listEntryId;

    private int listId;

    private int productId;

    private double quantity;

    private double cost;

    private int category;

    private String productName;

    public ListEntry() {

    }

    public ListEntry(int listId, int productId, double quantity, double cost) {
        this.listId = listId;
        this.productId = productId;
        this.quantity = quantity;
        this.cost = cost;
    }

    public void setCategory(int category) {
        this.category = category;
    }
    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public void setListEntryId(int listEntryId) {
        this.listEntryId = listEntryId;
    }

    public void setListId(int listId) {
        this.listId = listId;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }

    public int getCategory() {
        return category;
    }
    public int getListEntryId() {
        return listEntryId;
    }

    public int getListId() {
        return listId;
    }

    public int getProductId() {
        return productId;
    }

    public double getQuantity() {
        return quantity;
    }

    public double getCost() {
        return cost;
    }

    public double getTotal(){
        return cost * quantity;
    }

    @Override
    public String toString() {
        //if statemtnt to print differntly depending on product category

        if(category == 1){
            return "ID: " + listEntryId + ". " + System.lineSeparator() +"Product: " + productName + System.lineSeparator() +
                    "Quantity: " + quantity + " lbs" + System.lineSeparator() +
                    "Cost per pound: " + cost + System.lineSeparator() +
                    "Total cost: $" + getTotal() + System.lineSeparator()  ;
        }else{
            return  "ID: " +listEntryId + ". " + System.lineSeparator() + "Product: " + productName + System.lineSeparator() +
                    "Quantity: " + quantity + System.lineSeparator() +
                    "Cost per item: " + cost + System.lineSeparator() +
                    "Total cost: $" + getTotal() + System.lineSeparator()  ;

        }


    }
}



