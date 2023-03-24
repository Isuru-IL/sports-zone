package lk.ijse.sports_zone.model;

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

    public static List<SupplierTM> getAll() throws SQLException {
        String sql = "SELECT * FROM Supplier";
        try(PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)){

            ResultSet resultSet = pstm.executeQuery();

            List<SupplierTM> allData = new ArrayList<>();

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
}
