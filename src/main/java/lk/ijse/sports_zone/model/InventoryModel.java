package lk.ijse.sports_zone.model;

import lk.ijse.sports_zone.db.DBConnection;
import lk.ijse.sports_zone.dto.CartDTO;
import lk.ijse.sports_zone.dto.Inventory;
import lk.ijse.sports_zone.dto.SupplyLoadDetailDTO;
import lk.ijse.sports_zone.dto.tm.InventoryTM;
import lk.ijse.sports_zone.util.CrudUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InventoryModel {
    public static boolean save(Inventory inventory) throws SQLException {
        String sql = "INSERT INTO Item(itemCode, itemName, category, brand, unitPrice, qtyOnHand)"+
                "VALUES (?, ?, ?, ?, ?, ?)";
        return CrudUtil.execute(
                sql,
                inventory.getItemCode(),
                inventory.getItemName(),
                inventory.getCategory(),
                inventory.getBrand(),
                inventory.getUnitPrice(),
                inventory.getQtyOnHand()
        );
    }

    public static List<Inventory> getAll() throws SQLException {
        String sql = "SELECT * FROM Item";
        ResultSet resultSet = CrudUtil.execute(sql);

        List<Inventory> allData = new ArrayList<>();
        while (resultSet.next()){
            allData.add(new Inventory(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getDouble(5),
                    resultSet.getInt(6)
            ));
        }
        return allData;
    }

    public static Inventory search(String id) throws SQLException {
        String sql = "SELECT * FROM Item WHERE itemCode =?";
        ResultSet resultSet = CrudUtil.execute(sql,id);

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

    public static boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM Item WHERE itemCode =?";
        return CrudUtil.execute(sql, id);
    }

    public static boolean update(Inventory inventory) throws SQLException {
        String sql = "UPDATE Item SET itemName =?, category =?, brand =?, unitPrice =?, qtyOnHand =? WHERE itemCode =?";

        return CrudUtil.execute(
                sql,
                inventory.getItemName(),
                inventory.getCategory(),
                inventory.getBrand(),
                inventory.getUnitPrice(),
                inventory.getQtyOnHand(),
                inventory.getItemCode()
        );
    }

    public static List<String> loadItemCodes() throws SQLException {
        String sql = "SELECT itemCode FROM Item";
        List<String> allCodes = new ArrayList<>();
        ResultSet resultSet = CrudUtil.execute(sql);

        while (resultSet.next()){
            allCodes.add(resultSet.getString(1));
        }
        return allCodes;
    }

    public static Inventory searchByItemCode(String code) throws SQLException {
        String sql = "SELECT * FROM Item WHERE itemCode= ?";
        ResultSet resultSet = CrudUtil.execute(sql,code);
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

    public static boolean updateQty(List<CartDTO> cartDTOList) throws SQLException {
        for(CartDTO dto : cartDTOList){
            if(!updateQty(dto)){
                return false;
            }
        }
        return true;
    }

    private static boolean updateQty(CartDTO dto) throws SQLException {
        String sql = "UPDATE Item SET qtyOnHand = (qtyOnHand - ?) WHERE itemCode = ?";
        return CrudUtil.execute(sql, dto.getQty(), dto.getItemCode());
    }

    public static boolean updateSupplyQty(List<SupplyLoadDetailDTO> supplyLoadDetailDTOList) throws SQLException {
        for (SupplyLoadDetailDTO dto : supplyLoadDetailDTOList){
            if(!updateSupplyQty(dto)){
                return false;
            }
        }
        return true;
    }

    private static boolean updateSupplyQty(SupplyLoadDetailDTO dto) throws SQLException {
        String sql = "UPDATE Item SET qtyOnHand = (qtyOnHand + ?) WHERE itemCode =?";
        return CrudUtil.execute(sql, dto.getQty(), dto.getItemCode());
    }
}
