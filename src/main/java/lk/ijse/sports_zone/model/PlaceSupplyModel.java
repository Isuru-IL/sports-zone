package lk.ijse.sports_zone.model;

import lk.ijse.sports_zone.db.DBConnection;
import lk.ijse.sports_zone.dto.SupplyLoadDetailDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class PlaceSupplyModel {
    public static boolean placeSupply(String loadId, String supId, double netTotal, List<SupplyLoadDetailDTO> supplyLoadDetailDTOList) throws SQLException {
        LocalDate date = LocalDate.now();

        Connection con = null;

        try{
            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);

            boolean isSaved = SupplierloadDetailModel.save(loadId, supId, date, supplyLoadDetailDTOList);
            if(isSaved){
                boolean isUpdate = InventoryModel.updateSupplyQty(supplyLoadDetailDTOList);
                if(isUpdate){
                    con.commit();
                    return true;
                }
            }
            return false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            con.rollback();
            return false;
        } finally {
        con.setAutoCommit(true);
        }
    }
}
