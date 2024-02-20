package com.example.GroceryListMaker;

import com.example.GroceryListMaker.dao.JdbcProductDao;
import com.example.GroceryListMaker.model.Product;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;

public class JdbcProductDaoTests extends BaseDaoTests {

    private static final int PRODUCT_ID = 1;

    private static final String PRODUCT_NAME = "Product Name";

    private JdbcProductDao jdbcProductDao;

    @Before
    public void setup() {

        // Arrange - new instance of JdbcSaleDao before each and every test
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcProductDao = new JdbcProductDao(jdbcTemplate);
    }

    @Test
    public void getSaleById_returns_correct_sale() {

        // Step One: Replace Assert.fail("Test not implemented.")
        // Assert.fail("Test not implemented.");

        // Arrange - create an instance of Madge's first Sale object
        Product product = mapValuesToProduct(PRODUCT_ID, PRODUCT_NAME);

        // Act - retrieve Madge's first sale
        Product retrievedProduct = jdbcProductDao.getProductById(PRODUCT_ID);
        //Sale sale = jdbcSaleDao.getSaleById(MADGE_FIRST_SALE_ID);

        // Assert - retrieved sale is not null and matches expected sale
        //assertProductsMatch("getProductById(" + );
        Assert.assertNotNull("getProductById(" + PRODUCT_ID + ") returned null", retrievedProduct);
        assertProductsMatch("getProductById(" + PRODUCT_ID + ") returned wrong or partial data", product, retrievedProduct);
    }

    private static Product mapValuesToProduct(int productId, String name){
        Product product = new Product();
        product.setProductId(productId);
        product.setProductName(name);

        return product;
    }

    private void assertProductsMatch(String message, Product expected, Product actual){

        Assertions.assertEquals(message, expected.getProductName(), actual.getProductName());
       // Assertions.assertEquals(message, expected.getProductId(), actual.getProductId());
    }

}
