package lk.ijse.sports_zone.model;

import lk.ijse.sports_zone.db.DBConnection;
import lk.ijse.sports_zone.dto.CartDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class PlaceOrderModel {

    public static boolean placeOrder(String ordrId, String customerId, Date date, Time time, List<CartDTO> cartDTOList) throws SQLException {
        Connection con = null;
        try{
            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);

            boolean isSaved = CashierOrderModel.save(ordrId, customerId, date, time, cartDTOList);
            if(isSaved){
                boolean isUpdate = InventoryModel.updateQty(cartDTOList);
                if(isUpdate){
                    boolean isOrdered = OrderDetailModel.save(ordrId, cartDTOList);
                    if(isOrdered){
                        con.commit();
                        return true;
                    }
                }
            }
            return false;
        } catch (SQLException er) {
            System.out.println(er);
            con.rollback();
            return false;
        } finally {
            System.out.println("finally");
            con.setAutoCommit(true);
        }
    }
}
