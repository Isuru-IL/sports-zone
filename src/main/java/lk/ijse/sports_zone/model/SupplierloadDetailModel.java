package lk.ijse.sports_zone.model;

import lk.ijse.sports_zone.dto.SupplyLoadDetailDTO;
import lk.ijse.sports_zone.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class SupplierloadDetailModel {
    public static String genaratenextloadId() throws SQLException {
        String sql = "SELECT loadId FROM supplyLoadDetail ORDER BY loadId DESC LIMIT 1";
        ResultSet resultSet = CrudUtil.execute(sql);

        if (resultSet.next()) {
            return splitOrderId(resultSet.getString(1));
        }
        return splitOrderId(null);
    }

    private static String splitOrderId(String currentId) {
        if(currentId != null) {
            String[] strings = currentId.split("LD-");
            int id = Integer.parseInt(strings[1]);
            ++id;
            String digit=String.format("%03d", id);
            return "LD-" + digit;
        }
        return "LD-001";
    }

    public static boolean save(String loadId, String supId, LocalDate date, List<SupplyLoadDetailDTO> supplyLoadDetailDTOList) throws SQLException {
        for (SupplyLoadDetailDTO dto : supplyLoadDetailDTOList){
            if(!save(loadId, supId, date, dto)){
                return false;
            }
        }
        return true;
    }

    private static boolean save(String loadId, String supId, LocalDate date, SupplyLoadDetailDTO dto) throws SQLException {
        String sql = "INSERT INTO SupplyLoadDetail(loadId, itemCode, supId, date, price, qty)" +
                "VALUES(?, ?, ?, ?, ?, ?)";
        return CrudUtil.execute(
                sql,
                loadId,
                dto.getItemCode(),
                supId,
                date,
                dto.getTotalPrice(),
                dto.getQty()
        );
    }
}
