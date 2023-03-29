package lk.ijse.sports_zone.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.sports_zone.db.DBConnection;
import lk.ijse.sports_zone.dto.Supplier;
import lk.ijse.sports_zone.dto.tm.SupplierTM;

import java.awt.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierModel {
    public static boolean save(Supplier supplier) throws SQLException {
        String sql = "INSERT INTO Supplier(supId, supName, address, email, contactNo)"+
                "VALUES (?, ?, ?, ?, ?)";
        try(PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)){
            pstm.setString(1, supplier.getSupId());
            pstm.setString(2, supplier.getSupName());
            pstm.setString(3, supplier.getAddress());
            pstm.setString(4, supplier.getEmail());
            pstm.setString(5, supplier.getContactNo());

            int affectedRows = pstm.executeUpdate();
            return affectedRows > 0;
        }
    }

    public static ObservableList<SupplierTM> getAll() throws SQLException {
        String sql = "SELECT * FROM Supplier";
        try(PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)){

            ResultSet resultSet = pstm.executeQuery();

            ObservableList<SupplierTM> allData = FXCollections.observableArrayList();

            while(resultSet.next()){
                allData.add(new SupplierTM(
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

    public static Supplier search(String supId) throws SQLException {
        String sql = "SELECT * FROM Supplier WHERE supId = ?";

        try(PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)){
            pstm.setString(1, supId);

            ResultSet resultSet = pstm.executeQuery();

            if(resultSet.next()){
               return new Supplier(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5)
                );
            }
        }
        return null;
    }

    public static boolean delete(String supId) throws SQLException {
        String sql = "DELETE FROM Supplier WHERE supId =?";

        try(PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)){
            pstm.setString(1, supId);

            return pstm.executeUpdate() > 0;
        }
    }

    public static boolean update(Supplier supplier) throws SQLException {
        String sql = "UPDATE Supplier SET supId =?, supName =?, address =?, email =?, contactNo =? WHERE supId =?";

        try(PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)){
            pstm.setString(1, supplier.getSupId());
            pstm.setString(2, supplier.getSupName());
            pstm.setString(3, supplier.getAddress());
            pstm.setString(4, supplier.getEmail());
            pstm.setString(5, supplier.getContactNo());
            pstm.setString(6, supplier.getSupId());

            return pstm.executeUpdate() > 0;
        }
    }
}
