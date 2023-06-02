package lk.ijse.sports_zone.dao;

import lk.ijse.sports_zone.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO{

    @Override
    public ArrayList<Customer> getAll() throws SQLException {
        return null;
    }

    @Override
    public boolean save(Customer dto) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Customer dto) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException {
        return false;
    }

    @Override
    public String generateNewID() throws SQLException {
        return null;
    }

    @Override
    public Customer search(String s) throws SQLException {
        return null;
    }
}
