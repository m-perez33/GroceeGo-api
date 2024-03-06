package com.example.GroceryListMaker;

import com.example.GroceryListMaker.dao.JdbcGroceryListDao;
import com.example.GroceryListMaker.dao.JdbcProductDao;
import com.example.GroceryListMaker.model.GroceryList;
import com.example.GroceryListMaker.model.Product;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;

public class JdbcGroceryListDaoTests extends BaseDaoTests {
    private static final int LIST_ID = 1;

    private static final LocalDate LOCAL_DATE = LocalDate.of(2023, 12, 22);

    private JdbcGroceryListDao jdbcGroceryListDao;


    @Before
    public void setup() {

        // Arrange - new instance of JdbcSaleDao before each and every test
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcGroceryListDao = new JdbcGroceryListDao(jdbcTemplate);
    }

    @Test
    public void getGroceryListById_returns_correct_sale() {

        // Step One: Replace Assert.fail("Test not implemented.")

        // Arrange - create an instance of grocery list
        GroceryList groceryList = mapValuesToGroceryList(LIST_ID, LOCAL_DATE);

        // Act - retrieve grocery list
        GroceryList retrievedGroceryList = jdbcGroceryListDao.getGroceryListById(LIST_ID);

        // Assert - retrieved grocery list is not null and matches expected grocery list

        Assert.assertNotNull("getProductById(" + LIST_ID + ") returned null", retrievedGroceryList);
        assertGroceryListsMatch( groceryList, retrievedGroceryList);
    }

    private static GroceryList mapValuesToGroceryList(int listId, LocalDate date){
        GroceryList groceryList = new GroceryList();
        groceryList.setListId(listId);
        groceryList.setDate(date);

        return groceryList;
    }

    private void assertGroceryListsMatch( GroceryList expected, GroceryList actual){

        Assertions.assertEquals(expected.getListId(), actual.getListId());
        Assertions.assertEquals( expected.getDate(), actual.getDate());
    }

}
