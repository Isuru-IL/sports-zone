package lk.ijse.sports_zone.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.sports_zone.dto.Supplier;
import lk.ijse.sports_zone.dto.Vehicle;
import lk.ijse.sports_zone.dto.tm.VehicleTM;
import lk.ijse.sports_zone.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleModel {

    public static boolean save(Vehicle vehicle) throws SQLException {
        String sql = "INSERT INTO Vehicle(vehiId, vehiNo, vehiType)" +
                "VALUES (?, ?, ?)";
        return CrudUtil.execute(
                sql,
                vehicle.getVehiId(),
                vehicle.getVehiNo(),
                vehicle.getVehiType()
        );
    }

    public static boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM Vehicle WHERE vehiId =?";
        return CrudUtil.execute(sql, id);
    }

    public static boolean update(Vehicle vehicle) throws SQLException {
        String sql = "UPDATE vehicle SET vehiNo =?, vehiType =? WHERE vehiId =?";
        return CrudUtil.execute(
                sql,
                vehicle.getVehiNo(),
                vehicle.getVehiType(),
                vehicle.getVehiId()
        );
    }

    public static ObservableList<VehicleTM> getAll() throws SQLException {
        String sql = "SELECT * FROM Vehicle";
        ResultSet resultSet = CrudUtil.execute(sql);

        ObservableList<VehicleTM> allData = FXCollections.observableArrayList();

        while (resultSet.next()){
            allData.add(new VehicleTM(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3)
            ));
        }
        return allData;
    }

    public static Vehicle search(String id) throws SQLException {
        String sql = "SELECT * FROM Vehicle WHERE vehiId = ?";
        ResultSet resultSet = CrudUtil.execute(sql, id);

        if(resultSet.next()){
            return new Vehicle(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3)
            );
        }
        return  null;
    }

    public static List<String> loadVehiIds() throws SQLException {
        String sql = "SELECT vehiId FROM Vehicle";
        List<String> allVehiIds = new ArrayList<>();

        ResultSet resultSet = CrudUtil.execute(sql);
        while (resultSet.next()){
            allVehiIds.add(resultSet.getString(1));
        }
        return allVehiIds;
    }
}
