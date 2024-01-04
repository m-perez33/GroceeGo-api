package com.example.GroceryListMaker.dao;

import com.example.GroceryListMaker.exception.DaoException;
import com.example.GroceryListMaker.model.Product;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcProductDao implements ProductDao {
    private JdbcTemplate jdbcTemplate;

    public JdbcProductDao (JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();

        String sql = "SELECT * FROM product;";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                Product p = new Product();
                p.setProductName(results.getString("name"));
                p.setProductId(results.getInt("product_id"));
                products.add(p);

            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return products;

    }

    @Override
    public Product getProductById(int id) {
        Product product = null;
        String sql = "SELECT product_id, name FROM product WHERE product_id = ?";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
            if (results.next()) {
                Product p = new Product();
                p.setProductName(results.getString("name"));
                p.setProductId(results.getInt("product_id"));
                product = p;

            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        return product;
    }

    @Override
    public Product createProduct(Product product) {
        //Product newProduct = null;

        String sql = "INSERT into product (name) VALUES (?) RETURNING product_id";
        try {
            int pID = jdbcTemplate.queryForObject(sql, int.class, product.getProductName());
            System.out.println(pID);
            return getProductById(pID);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        // return newProduct;
    }
}
