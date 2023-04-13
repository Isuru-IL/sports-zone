package lk.ijse.sports_zone.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.sports_zone.dto.Customer;
import lk.ijse.sports_zone.dto.Repair;
import lk.ijse.sports_zone.dto.tm.CustomerTM;
import lk.ijse.sports_zone.dto.tm.RepairTM;
import lk.ijse.sports_zone.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RepairModel {

    public static String getNextOrderId() throws SQLException {
        String sql = "SELECT repairId FROM Repair ORDER BY repairId DESC LIMIT 1";
        ResultSet resultSet = CrudUtil.execute(sql);

        if (resultSet.next()) {
            return splitRepairId(resultSet.getString(1));
        }
        return splitRepairId(null);
    }

    private static String splitRepairId(String currentId) {
        if(currentId != null) {
            String[] strings = currentId.split("R-");
            int id = Integer.parseInt(strings[1]);
            ++id;
            String digit=String.format("%03d", id);
            return "R-" + digit;
        }
        return "R-001";
    }

    public static boolean save(Repair repair) throws SQLException {
        String sql ="INSERT INTO Repair(repairId, custId, repairItem, date, price)"+
                "VALUES(?, ?, ?, ?, ?)";
        return CrudUtil.execute(
                sql,
                repair.getRepairId(),
                repair.getCustId(),
                repair.getRepairItem(),
                repair.getDate(),
                repair.getPrice()
        );
    }

    public static ObservableList<RepairTM> getAll() throws SQLException {
        String sql = "SELECT * FROM Repair";

        ObservableList<RepairTM> allData = FXCollections.observableArrayList();
        ResultSet resultSet = CrudUtil.execute(sql);
        while (resultSet.next()){
            allData.add(new RepairTM(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getDouble(5)
            ));
        }
        return allData;
    }

    public static boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM Repair WHERE repairId =?";
        return CrudUtil.execute(sql,id);
    }

    public static boolean update(Repair repair) throws SQLException {
        String sql = "UPDATE Repair SET custId =?, repairItem =?, date =?, price =?" +
                "WHERE repairId =?";
        return CrudUtil.execute(
                sql,
                repair.getCustId(),
                repair.getRepairItem(),
                repair.getDate(),
                repair.getPrice(),
                repair.getRepairId()
        );
    }

    public static Repair search(String id) throws SQLException {
        String sql = "SELECT * FROM Repair WHERE repairId =?";
        ResultSet resultSet = CrudUtil.execute(sql, id);

        if(resultSet.next()){
            return new Repair(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getDouble(5)
            );
        }
        return null;
    }
}
