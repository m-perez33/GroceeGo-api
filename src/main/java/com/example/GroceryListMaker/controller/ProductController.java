package com.example.GroceryListMaker.controller;

import com.example.GroceryListMaker.dao.ProductDao;
import com.example.GroceryListMaker.exception.DaoException;
import com.example.GroceryListMaker.model.GroceryList;
import com.example.GroceryListMaker.model.Product;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/Product")
public class ProductController {

    private ProductDao productDao;

    public ProductController(ProductDao productDao){
        this.productDao = productDao;
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<Product> getAllProducts(){
        try {
            return productDao.getProducts();
        }
        catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "DAO Error - " + e.getMessage());
        }    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Product getIndividualProduct(@PathVariable int id) {
        try {
            Product product = productDao.getProductById(id);
            if (product == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Grocery List not found");
            }
            return product;
        }
        catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "DAO Error - " + e.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "", method = RequestMethod.POST)
    public Product saveProduct(@Valid @RequestBody Product incomingProduct) {
        try {
            return productDao.createProduct(incomingProduct);
        }
        catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "DAO Error - " + e.getMessage());
        }
    }







}
