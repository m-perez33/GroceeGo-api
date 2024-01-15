package com.example.GroceryListMaker.controller;

import com.example.GroceryListMaker.dao.ListEntryDao;
import com.example.GroceryListMaker.exception.DaoException;
import com.example.GroceryListMaker.model.ListEntry;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/listEntry")
public class ListEntryController {

    private ListEntryDao listEntryDao;

    public ListEntryController(ListEntryDao listEntryDao) {
        this.listEntryDao = listEntryDao;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ListEntry getListEntry(@PathVariable int id){
        ListEntry listEntry = listEntryDao.getListEntryById(id);

        return listEntry;
    }

    @RequestMapping(path = "/list/{id}", method = RequestMethod.GET)
    public List<ListEntry> getByList(@PathVariable int id){
        return listEntryDao.getListEntriesByListId( id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "", method = RequestMethod.POST)
    public ListEntry saveListEntry(@Valid @RequestBody ListEntry incomingEntry) {
        try {
            return listEntryDao.createListEntry(incomingEntry);
        }
        catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "DAO Error - " + e.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public ListEntry updateExistingCard(@Valid @RequestBody ListEntry changedListEntry, @PathVariable int id) {
        // The id on the URL takes precedence over the one in the payload, if any
        changedListEntry.setListEntryId(id);

        try {
            return listEntryDao.updateListEntry(changedListEntry);
        }
        catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "DAO Error - " + e.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void deleteExistingCard(@PathVariable int id) {
        try {
            int cardsDeleted = listEntryDao.deleteListEntry(id);
            if (cardsDeleted == 0) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
        }
        catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "DAO Error - " + e.getMessage());
        }
    }


}

