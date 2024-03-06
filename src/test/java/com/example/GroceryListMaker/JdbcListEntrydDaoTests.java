package com.example.GroceryListMaker;

import com.example.GroceryListMaker.dao.JdbcListEntryDao;
import com.example.GroceryListMaker.dao.JdbcProductDao;
import com.example.GroceryListMaker.model.ListEntry;
import com.example.GroceryListMaker.model.Product;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class JdbcListEntrydDaoTests extends BaseDaoTests {

    private static final int LIST_ENTRY_ID = 1;

    private static final int LIST_ID = 1;

    private static final int PRODUCT_ID = 1;

    private static final double QUANTITY = 3;

    private static final double COST = 2.5;

    private static final int CATEGORY = 1;

    private JdbcListEntryDao jdbcListEntryDao;

    @Before
    public void setup() {

        // Arrange - new instance of JdbcSaleDao before each and every test
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcListEntryDao = new JdbcListEntryDao(jdbcTemplate);
    }

    @Test
    public void getListEntry_returns_correct_List() {


        // Arrange - create an instance of list entry
        ListEntry listEntry = mapValuesToListEntry(LIST_ID, LIST_ENTRY_ID, QUANTITY, COST, CATEGORY, PRODUCT_ID);

        // Act - retrieve list entry
        ListEntry retrievedList = jdbcListEntryDao.getListEntryById(LIST_ID);

        // Assert - retrieved listEntry is not null and matches expected listEntry
        Assert.assertNotNull("getProductById(" + PRODUCT_ID + ") returned null", retrievedList);
        assertListEntriesMatch(listEntry, retrievedList);
    }

    private static ListEntry mapValuesToListEntry(int id, int entryId, double quantity, double cost, int category, int productId) {
        ListEntry listEntry = new ListEntry();
        listEntry.setQuantity(quantity);
        listEntry.setCost(cost);
        listEntry.setCategory(category);
        listEntry.setListId(id);
        listEntry.setListEntryId(entryId);
        listEntry.setQuantity(quantity);
        listEntry.setCost(cost);
        listEntry.setProductId(productId);

        return listEntry;
    }

    private void assertListEntriesMatch(ListEntry expected, ListEntry actual) {

        Assertions.assertEquals(expected.getListEntryId(), actual.getListEntryId());
        Assertions.assertEquals(expected.getQuantity(), actual.getQuantity());
        Assertions.assertEquals(expected.getCost(), actual.getCost());
        Assertions.assertEquals(expected.getListId(), actual.getListId());
        Assertions.assertEquals(expected.getCategory(), actual.getCategory());
        Assertions.assertEquals(expected.getProductId(), actual.getProductId());
    }
}
