package lk.ijse.sports_zone.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.sports_zone.db.DBConnection;
import lk.ijse.sports_zone.dto.Customer;
import lk.ijse.sports_zone.dto.tm.CustomerTM;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerModel {

    public static boolean save(Customer customer) throws SQLException {
        String sql ="INSERT INTO Customer(custId, custName, contactNo, address, email)"+
                "VALUES(?, ?, ?, ?, ?)";
        try(PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)){
            pstm.setString(1, customer.getCustId());
            pstm.setString(2, customer.getCustName());
            pstm.setString(3, customer.getContactNo());
            pstm.setString(4, customer.getAddress());
            pstm.setString(5, customer.getEmail());

            return pstm.executeUpdate()>0;
        }
    }

    public static ObservableList<CustomerTM> getAll() throws SQLException {
        String sql = "SELECT * FROM Customer";
        try(PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)){
            ResultSet resultSet = pstm.executeQuery();

            ObservableList<CustomerTM> allData = FXCollections.observableArrayList();
            while(resultSet.next()){
                allData.add(new CustomerTM(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5)
                ));
            }
            return allData;
        }

    }

    public static boolean update(Customer customer) throws SQLException {
        String sql = "UPDATE Customer SET custName =?, contactNo =?, address =?, email =?" +
                "WHERE custId =?";

        try(PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)){
            pstm.setString(1, customer.getCustName());
            pstm.setString(2, customer.getContactNo());
            pstm.setString(3, customer.getAddress());
            pstm.setString(4, customer.getEmail());
            pstm.setString(5, customer.getCustId());

            return pstm.executeUpdate()>0;
        }
    }

    public static Customer search(String id) throws SQLException {
        String sql = "SELECT * FROM Customer WHERE custId =?";
        try(PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)){
            pstm.setString(1, id);

            ResultSet resultSet = pstm.executeQuery();
            if(resultSet.next()){
                return new Customer(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5)
                );
            }
            return null;
        }
    }

    public static boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM Customer WHERE custId =?";

        try(PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)){
            pstm.setString(1, id);
            return pstm.executeUpdate()>0;
        }
    }
}
