package lk.ijse.sports_zone.model;

import lk.ijse.sports_zone.dto.Delivery;
import lk.ijse.sports_zone.util.CrudUtil;

import java.sql.SQLException;

public class DeliveryModel {
    public static boolean save(Delivery delivery) throws SQLException {
        String sql = "INSERT INTO Delivery(deliveryId, empId, orderId, vehiId, location, deliveryDate, dueDate)" +
                "VALUE(?, ?, ?, ?, ?, ?, ?)";
        return CrudUtil.execute(
                sql,
                delivery.getDeliveryId(),
                delivery.getEmpId(),
                delivery.getOrderId(),
                delivery.getVehiId(),
                delivery.getLocation(),
                delivery.getDeliveryDate(),
                delivery.getDueDate()
        );
    }
}
