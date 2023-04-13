package lk.ijse.sports_zone.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import lk.ijse.sports_zone.dto.CartDTO;
import lk.ijse.sports_zone.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrderDetailModel {
    public static boolean save(String ordrId, List<CartDTO> cartDTOList) throws SQLException {
        for(CartDTO dto : cartDTOList){
            if(!save(ordrId, dto)){
                return false;
            }
        }
        return true;
    }
    public static boolean save(String ordrId, CartDTO dto) throws SQLException {
        String sql = "INSERT INTO orderDetail(orderId, itemCode, qty)" +
                "VALUES(?, ?, ?)";
        return CrudUtil.execute(sql, ordrId, dto.getItemCode(), dto.getQty());
    }

    public static ObservableList<PieChart.Data> getDataToPieChart() throws SQLException {
        String sql="SELECT item.itemName,COUNT(orderDetail.itemCode) "+
                "FROM orderDetail "+
                "INNER JOIN item "+
                "ON item.itemCode = orderDetail.itemCode " +
                "INNER JOIN orders " +
                "ON orderDetail.orderId=orders.orderId " +
                "WHERE MONTH(orders.date) = MONTH(CURRENT_DATE()) " +
                "GROUP BY item.itemName " +
                "LIMIT 5 ";
        ObservableList<PieChart.Data> datalist = FXCollections.observableArrayList();
        ResultSet resultSet = CrudUtil.execute(sql);

        while(resultSet.next()){
            datalist.add(
                    new PieChart.Data(
                            resultSet.getString(1),
                            resultSet.getInt(2)
                    )
            );
        }
        return datalist;
    }
}
