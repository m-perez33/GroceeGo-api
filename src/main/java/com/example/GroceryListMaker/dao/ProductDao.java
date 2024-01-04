package com.example.GroceryListMaker.dao;

import com.example.GroceryListMaker.model.Product;

import java.util.List;

public interface ProductDao {

    List<Product> getProducts();

    Product getProductById (int id);

    Product createProduct (Product product);

}
