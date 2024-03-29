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

    private static final String PRODUCT_NAME = "Product 1";

    private JdbcProductDao jdbcProductDao;


    @Before
    public void setup() {

        // Arrange - new instance of JdbcSaleDao before each and every test
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcProductDao = new JdbcProductDao(jdbcTemplate);
    }

    @Test
    public void getProductById_returns_correct_sale() {


        // Arrange - create an instance of product
        Product product = mapValuesToProduct(PRODUCT_ID, PRODUCT_NAME);

        // Act - retrieve product
        Product retrievedProduct = jdbcProductDao.getProductById(PRODUCT_ID);

        // Assert - retrieved product is not null and matches expected product
        System.out.println(product.getProductName());
        System.out.println(retrievedProduct.getProductName());
        Assert.assertNotNull("getProductById(" + PRODUCT_ID + ") returned null", retrievedProduct);
        assertProductsMatch(product, retrievedProduct);
    }

    private static Product mapValuesToProduct(int productId, String name) {
        Product product = new Product();
        product.setProductId(productId);
        product.setProductName(name);

        return product;
    }

    private void assertProductsMatch(Product expected, Product actual) {
        System.out.println(expected.getProductName());
        System.out.println(expected.getProductName());
        Assertions.assertEquals(expected.getProductId(), actual.getProductId());
        Assertions.assertEquals(expected.getProductName(), actual.getProductName());
    }

}
