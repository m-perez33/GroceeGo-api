package com.example.GroceryListMaker.controller;

import com.example.GroceryListMaker.dao.GroceryListDao;
import com.example.GroceryListMaker.exception.DaoException;
import com.example.GroceryListMaker.model.GroceryList;
import com.example.GroceryListMaker.model.ListEntry;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/GroceryLists")
public class GroceryListController {


    private GroceryListDao groceryListDao;

    public GroceryListController(GroceryListDao groceryListDao){
        this.groceryListDao = groceryListDao;
    }
    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<GroceryList> getAllGroceryLists(){
        try {
            return groceryListDao.getGroceryLists();
        }
        catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "DAO Error - " + e.getMessage());
        }    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public GroceryList getIndividualGrroceryList(@PathVariable int id) {
        try {
            GroceryList groceryList = groceryListDao.getGroceryListById(id);
            if (groceryList == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Grocery List not found");
            }
            return groceryList;
        }
        catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "DAO Error - " + e.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "", method = RequestMethod.POST)
    public GroceryList saveListEntry(@Valid @RequestBody GroceryList incomingList) {
        try {
            return groceryListDao.createGrocery(incomingList);
        }
        catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "DAO Error - " + e.getMessage());
        }
    }



    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void deleteExistingGroceryList(@PathVariable int id) {
        try {
            int cardsDeleted = groceryListDao.deleteGroceryList(id);
            if (cardsDeleted == 0) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
        }
        catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "DAO Error - " + e.getMessage());
        }
    }
}
