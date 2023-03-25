package lk.ijse.sports_zone.model;

import lk.ijse.sports_zone.db.DBConnection;
import lk.ijse.sports_zone.dto.Inventory;
import lk.ijse.sports_zone.dto.tm.InventoryTM;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InventoryModel {
    public static boolean save(Inventory inventory) throws SQLException {
        String sql = "INSERT INTO Item(itemCode, itemName, category, brand, unitPrice, qtyOnHand)"+
                "VALUES (?, ?, ?, ?, ?, ?)";

        try(PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)){
            pstm.setString(1, inventory.getItemCode());
            pstm.setString(2, inventory.getItemName());
            pstm.setString(3, inventory.getCategory());
            pstm.setString(4, inventory.getBrand());
            pstm.setDouble(5, inventory.getUnitPrice());
            pstm.setInt(6, inventory.getQtyOnHand());

            return pstm.executeUpdate() > 0;
        }
    }

    public static List<InventoryTM> getAll() throws SQLException {
        String sql = "SELECT * FROM Item";
        try(PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)){
            ResultSet resultSet = pstm.executeQuery();

            List<InventoryTM> allData = new ArrayList<>();
            while(resultSet.next()){
                allData.add(new InventoryTM(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getDouble(5),
                        resultSet.getInt(6)
                ));
            }
            return  allData;
        }
    }

    public static Inventory search(String id) throws SQLException {
        String sql = "SELECT * FROM Item WHERE itemCode =?";

        try(PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)){
            pstm.setString(1, id);

            ResultSet resultSet = pstm.executeQuery();
            if(resultSet.next()){
                return new Inventory(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getDouble(5),
                        resultSet.getInt(6)
                );
            }
            return null;
        }
    }

    public static boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM Item WHERE itemCode =?";

        try(PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)){
            pstm.setString(1, id);

            return pstm.executeUpdate() > 0;
        }
    }

    public static boolean update(Inventory inventory) throws SQLException {
        String sql = "UPDATE Item SET itemCode =?, itemName =?, category =?, brand =?, unitPrice =?, qtyOnHand =?";
        try(PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)){
            pstm.setString(1, inventory.getItemCode());
            pstm.setString(2, inventory.getItemName());
            pstm.setString(3, inventory.getCategory());
            pstm.setString(4, inventory.getBrand());
            pstm.setDouble(5, inventory.getUnitPrice());
            pstm.setInt(6, inventory.getQtyOnHand());

            return  pstm.executeUpdate()>0;
        }
    }
}
