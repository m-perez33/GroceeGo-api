package com.example.GroceryListMaker.model;

import java.time.LocalDate;

public class GroceryList {

    private int listId;

    private LocalDate date;


    public GroceryList(LocalDate date){
        this.date = date;
    }

    public GroceryList(){

    }

    public void setListId(int listId) {
        this.listId = listId;
    }

    public int getListId() {
        return listId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return   " " + listId + " " + date;
    }
}