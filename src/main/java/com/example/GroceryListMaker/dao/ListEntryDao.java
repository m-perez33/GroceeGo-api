package com.example.GroceryListMaker.dao;

import com.example.GroceryListMaker.model.ListEntry;

import java.util.List;

public interface ListEntryDao {
    List<ListEntry> getListEntries();

    List<ListEntry> getListEntriesByListId(int id);

    ListEntry getListEntryById(int id);

    ListEntry createListEntry(ListEntry listEntry);

    ListEntry updateListEntry(ListEntry listEntry);

    int deleteListEntry(int id);

}
