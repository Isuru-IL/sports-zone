package lk.ijse.sports_zone.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.sports_zone.dto.Delivery;
import lk.ijse.sports_zone.dto.Supplier;
import lk.ijse.sports_zone.dto.tm.DeliveryTM;
import lk.ijse.sports_zone.dto.tm.VehicleTM;
import lk.ijse.sports_zone.util.CrudUtil;

import java.sql.ResultSet;
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

    public static ObservableList<DeliveryTM> getAll() throws SQLException {
        String sql = "SELECT * FROM Delivery";
        ResultSet resultSet = CrudUtil.execute(sql);

        ObservableList<DeliveryTM> allData = FXCollections.observableArrayList();

        while (resultSet.next()){
            allData.add(new DeliveryTM(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getString(8)
            ));
        }
        return allData;
    }

    public static boolean update(Delivery delivery) throws SQLException {

        String sql = "UPDATE Delivery SET empid =?, vehiId =?, location =?, deliverydate =?, deliveryStatus =? WHERE deliveryId =?";
        return CrudUtil.execute(
                sql,
                delivery.getEmpId(),
                delivery.getVehiId(),
                delivery.getLocation(),
                delivery.getDeliveryDate(),
                delivery.getDeliveryStaus(),
                delivery.getDeliveryId()
        );
    }

    public static boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM Delivery WHERE deliveryId =?";
        return CrudUtil.execute(sql, id);
    }

    public static Delivery search(String id) throws SQLException {
        String sql = "SELECT * FROM Delivery WHERE deliveryId = ?";
        ResultSet resultSet = CrudUtil.execute(sql, id);

        if(resultSet.next()){
            return new Delivery(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getString(8)
            );
        }
        return  null;
    }

    public static String getNextDeliveryId() throws SQLException {
        String sql = "SELECT deliveryId FROM Delivery ORDER BY deliveryId DESC LIMIT 1";
        ResultSet resultSet = CrudUtil.execute(sql);

        if (resultSet.next()) {
            return splitRepairId(resultSet.getString(1));
        }
        return splitRepairId(null);
    }
    private static String splitRepairId(String currentId) {
        if(currentId != null) {
            String[] strings = currentId.split("DE-");
            int id = Integer.parseInt(strings[1]);
            ++id;
            String digit=String.format("%03d", id);
            return "DE-" + digit;
        }
        return "DE-001";
    }
}
