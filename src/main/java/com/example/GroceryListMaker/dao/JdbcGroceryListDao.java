package com.example.GroceryListMaker.dao;

import com.example.GroceryListMaker.exception.DaoException;
import com.example.GroceryListMaker.model.GroceryList;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcGroceryListDao implements GroceryListDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcGroceryListDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;;
    }

    @Override
    public List<GroceryList> getGroceryLists() {
        //Select all grocery lists. this will have be set to only select by user
        List<GroceryList> groceryLists = new ArrayList<>();
        String sql = "SELECT * FROM grocery_list";

        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()){
                GroceryList groceryList = mapRowToList(results);
                groceryLists.add(groceryList);
            }
        }catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        return groceryLists;
    }

    @Override
    public GroceryList getGroceryListById(int id) {

        GroceryList groceryList = null;
        String sql = "SELECT * FROM grocery_list WHERE grocery_list_id = ?";

        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
            if (results.next()){
                groceryList = mapRowToList(results);
            }
        }catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        return groceryList;
    }

    @Override
    public GroceryList createGrocery(GroceryList groceryList) {

        GroceryList newGroceryList = null;
        String sql = "INSERT INTO grocery_list (created_date) VALUES (?) RETURNING grocery_list_id";

        try {
            int glID = jdbcTemplate.queryForObject(sql, int.class, groceryList.getDate());
            newGroceryList = getGroceryListById(glID);
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return newGroceryList;
    }

    @Override
    public int deleteGroceryList(int id) {
        int numberOfRows = 0;
        String sql = "DELETE FROM grocery_list WHERE grocery_list_id = ?";
        try {
            numberOfRows = jdbcTemplate.update(sql, id);
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return numberOfRows;
    }

    private GroceryList mapRowToList(SqlRowSet rs){
        //map properties from rowset to object
        GroceryList gl =new GroceryList();
        gl.setListId(rs.getInt("grocery_list_id"));
        gl.setDate(rs.getDate("created_date").toLocalDate());
        return gl;
    }
}
