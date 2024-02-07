package com.example.GroceryListMaker;


import com.example.GroceryListMaker.model.GroceryList;
import com.example.GroceryListMaker.model.ListEntry;
import com.example.GroceryListMaker.model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.time.LocalDate;

public class ModelTests {


   @Test
   public void test_product_model (){
       //Arrange - call instance of class
       Product product = new Product();

       //Act - test expected input
       product.setProductId(1);
       product.setProductName("cereal");

       //Assert - check if result is expected
       Assertions.assertEquals(1, product.getProductId());
       Assertions.assertEquals("cereal", product.getProductName());

   }
    @Test
    public void test_list_model (){
        //Arrange - call instance of class
        GroceryList list = new GroceryList();
        LocalDate date = LocalDate.of(2020, 1, 8);

        //Act - test expected input
        list.setDate(date);
        list.setListId(1);

        //Assert - check if result is expected
        Assertions.assertEquals(LocalDate.of(2020,01, 8), list.getDate());
        Assertions.assertEquals(1, list.getListId());

    }

    @Test
    public void test_listEntry_model(){
        //Arrange - call instance of class
        ListEntry entry = new ListEntry();
        LocalDate date = LocalDate.of(2020, 1, 8);

        //Act - test expected input
        entry.setCategory(1);
        entry.setListEntryId(2);
        entry.setCost(3);
        entry.setQuantity(5);
        entry.setListId(1);
        entry.setProductId(3);
        entry.setProductName("bread");



        //Assert - check if result is expected
        Assertions.assertEquals(1, entry.getCategory());
        Assertions.assertEquals(2, entry.getListEntryId());
        Assertions.assertEquals(3, entry.getCost());
        Assertions.assertEquals(5, entry.getQuantity());
        Assertions.assertEquals(1, entry.getListId());
        Assertions.assertEquals(3, entry.getProductId());
        Assertions.assertEquals("bread", entry.getProductName());
    }

}
