package lk.ijse.sports_zone.model;

import lk.ijse.sports_zone.dto.CartDTO;
import lk.ijse.sports_zone.util.CrudUtil;

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
}
