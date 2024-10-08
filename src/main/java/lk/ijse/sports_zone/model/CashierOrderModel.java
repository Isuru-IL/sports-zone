package lk.ijse.sports_zone.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import lk.ijse.sports_zone.dto.CartDTO;
import lk.ijse.sports_zone.dto.tm.CashierOrderTM;
import lk.ijse.sports_zone.dto.tm.VehicleTM;
import lk.ijse.sports_zone.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CashierOrderModel {

    public static String getNextOrderId() throws SQLException {
        String sql = "SELECT orderId FROM Orders ORDER BY orderId DESC LIMIT 1";
        ResultSet resultSet = CrudUtil.execute(sql);

        if (resultSet.next()) {
            return splitOrderId(resultSet.getString(1));
        }
        return splitOrderId(null);

    }

    private static String splitOrderId(String currentId) {
        if(currentId != null) {
            String[] strings = currentId.split("OD-");
            int id = Integer.parseInt(strings[1]);
            ++id;
            String digit=String.format("%03d", id);
            return "OD-" + digit;
        }
        return "OD-001";
    }

    public static boolean save(String ordrId, String customerId, java.util.Date date, Time time, List<CartDTO> cartDTOList) throws SQLException {
        for(CartDTO dto : cartDTOList){
            if(!save(ordrId, customerId, date, time, dto)){
                return false;
            }
            break;
        }
        return true;
    }

    private static boolean save(String ordrId, String customerId, Date date, Time time, CartDTO dto) throws SQLException {
                String sql = "INSERT INTO Orders(orderId, custId, payment, time, date, deliveryStatus)" +
                "VALUES(?, ?, ?, ?, ?, ?)";
        return CrudUtil.execute(
                sql,
                ordrId,
                customerId,
                dto.getNetTotal(),
                time,
                date,
                dto.getDeliveryStatus()
        );
    }

    public static ObservableList<CashierOrderTM> getAll() throws SQLException {
        String sql = "SELECT * FROM Orders";
        ResultSet resultSet = CrudUtil.execute(sql);

        ObservableList<CashierOrderTM> allData = FXCollections.observableArrayList();

        while (resultSet.next()){
            allData.add(new CashierOrderTM(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6)
            ));
        }
        return allData;
    }

    public static int getTodayOrders() throws SQLException {
        String sql="SELECT COUNT(orderId) FROM orders WHERE date = CURDATE();";
        ResultSet resultSet= CrudUtil.execute(sql);
        int count=0;
        while (resultSet.next()){
            count=resultSet.getInt(1);
        }
        return count;

    }

    public static double getTodayIncome() throws SQLException {
        String sql = "select sum(payment) from orders where date= curdate()";

        ResultSet resultSet = CrudUtil.execute(sql);

        if(resultSet.next()) {
            return resultSet.getInt(1);
        }
        return 0;
    }

    public static double getMonthlyIncome() throws SQLException {
        String sql = "select sum(payment) from orders where MONTH(date)= MONTH(curdate())";

        ResultSet resultSet = CrudUtil.execute(sql);

        if(resultSet.next()) {
            return resultSet.getInt(1);
        }
        return 0;
    }

    public static List<XYChart.Data<String, Double>> getDataToAreaChart(String year) throws SQLException {
        String sql= "SELECT MONTHNAME(date) AS month,SUM(payment) AS total_income FROM orders WHERE YEAR(date)=? GROUP BY month ORDER BY month desc";

        List<XYChart.Data<String, Double>> data = new ArrayList<>();
        ResultSet resultSet = CrudUtil.execute(sql,year);

        while(resultSet.next()){
            String month = resultSet.getString("month");
            double income = resultSet.getDouble("total_income");
            data.add(new XYChart.Data<>(month, income));
        }
        return data;
    }
}
