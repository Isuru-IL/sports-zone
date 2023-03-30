package lk.ijse.sports_zone.model;

import javafx.collections.ObservableList;
import lk.ijse.sports_zone.db.DBConnection;
import lk.ijse.sports_zone.dto.Employee;
import lk.ijse.sports_zone.dto.tm.EmployeeTM;
import lk.ijse.sports_zone.util.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeModel {

    public static boolean save(Employee employee) throws SQLException {
//        String sql = "INSERT INTO Employee(empId, empName, address, dob, contactNo, salary, email, nic, jobTitle)"+
//                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
//        try(PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)){
//            pstm.setString(1, employee.getEmpId());
//            pstm.setString(2, employee.getEmpName());
//            pstm.setString(3, employee.getAddress());
//            pstm.setString(4, employee.getDob());
//            pstm.setString(5, employee.getContactNo());
//            pstm.setString(6, String.valueOf(employee.getSalary()));
//            pstm.setString(7, employee.getEmail());
//            pstm.setString(8, employee.getNic());
//            pstm.setString(9, employee.getJobTitle());
//
//            int affectedRows = pstm.executeUpdate();
//            return affectedRows > 0;
//        }
        String sql = "INSERT INTO Employee(empId, empName, address, dob, contactNo, salary, email, nic, jobTitle)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return CrudUtil.execute(
                sql,
                employee.getEmpId(),
                employee.getEmpName(),
                employee.getAddress(),
                employee.getDob(),
                employee.getContactNo(),
                employee.getSalary(),
                employee.getEmail(),
                employee.getNic(),
                employee.getJobTitle()
        );


    }

    public static List<Employee> getAll() throws SQLException {
//        String sql = "SELECT * FROM Employee";
//        try(PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)){
//            ResultSet resultSet = pstm.executeQuery();
//
//            List<Employee> allData = new ArrayList<>();
//
//            while(resultSet.next()){
//                allData.add(new Employee(
//                        resultSet.getString(1),
//                        resultSet.getString(2),
//                        resultSet.getString(3),
//                        resultSet.getString(4),
//                        resultSet.getString(5),
//                        resultSet.getDouble(6),
//                        resultSet.getString(7),
//                        resultSet.getString(8),
//                        resultSet.getString(9)
//                ));
//            }
//            return allData;
//        }
        String sql = "SELECT * FROM Employee";
        List<Employee> allData = new ArrayList<>();

        ResultSet resultSet = CrudUtil.execute(sql);

        while (resultSet.next()){
            allData.add(new Employee(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getDouble(6),
                    resultSet.getString(7),
                    resultSet.getString(8),
                    resultSet.getString(9)
            ));
        }
        return allData;
    }

    public static Employee search(String empId) throws SQLException {
//        String sql = "SELECT * FROM employee WHERE empId = ?";
//        try(PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)){
//            pstm.setString(1, empId);
//
//            ResultSet resultSet = pstm.executeQuery();
//            if(resultSet.next()){
//                return new Employee(
//                        resultSet.getString(1),
//                        resultSet.getString(2),
//                        resultSet.getString(3),
//                        resultSet.getString(4),
//                        resultSet.getString(5),
//                        resultSet.getDouble(6),
//                        resultSet.getString(7),
//                        resultSet.getString(8),
//                        resultSet.getString(9)
//                );
//            }
//        }
//        return null;

        String sql = "SELECT * FROM Employee WHERE empId =?";
        ResultSet resultSet = CrudUtil.execute(sql, empId);

        if(resultSet.next()){
            return new Employee(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getDouble(6),
                    resultSet.getString(7),
                    resultSet.getString(8),
                    resultSet.getString(9)
            );
        }
        return null;
    }

    public static boolean update(Employee employee) throws SQLException {
//        String sql = "UPDATE Employee SET empName =?, address =?, dob =?, contactNo =?,"+
//        "salary =?, email =?, nic =?, jobTitle =? WHERE empId =?";
//
//        try(PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)){
//
//            pstm.setString(1, employee.getEmpName());
//            pstm.setString(2, employee.getAddress());
//            pstm.setString(3, employee.getDob());
//            pstm.setString(4, employee.getContactNo());
//            pstm.setString(5, String.valueOf(employee.getSalary()));
//            pstm.setString(6, employee.getEmail());
//            pstm.setString(7, employee.getNic());
//            pstm.setString(8, employee.getJobTitle());
//            pstm.setString(9, employee.getEmpId());
//
//            int affectedRows = pstm.executeUpdate();
//
//            return affectedRows > 0;
//        }
        String sql = "UPDATE Employee SET empName =?, address =?, dob =?, contactNo =?, " +
                "salary =?, email =?, nic =?, jobTitle =? WHERE empId =?";
        return CrudUtil.execute(
                sql,
                employee.getEmpName(),
                employee.getAddress(),
                employee.getDob(),
                employee.getContactNo(),
                employee.getSalary(),
                employee.getEmail(),
                employee.getNic(),
                employee.getJobTitle(),
                employee.getEmpId()
        );

    }

    public static boolean delete(String empId) throws SQLException {
//        String sql = "DELETE FROM Employee WHERE empId = ?";
//
//        try(PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)){
//            pstm.setString(1, empId);
//
//            int affectedRows = pstm.executeUpdate();
//            return affectedRows > 0;
//        }

        String sql = "DELETE FROM Employee WHERE empId = ?";
        return CrudUtil.execute(sql, empId);
    }

    public static String getNextEmpId() throws SQLException {
//        String sql = "SELECT empId FROM employee ORDER BY empId DESC LIMIT 1";
//
//        try(PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)){
//            ResultSet resultSet = pstm.executeQuery();
//
//            if (resultSet.next()) {
//                return splitEmpId(resultSet.getString(1));
//            }
//            return splitEmpId(null);
//        }
        String sql = "SELECT empId FROM employee ORDER BY empId DESC LIMIT 1";
        ResultSet resultSet = CrudUtil.execute(sql);

        if(resultSet.next()){
            return splitEmpId(resultSet.getString(1));
        }
        return splitEmpId(null);
    }

    private static String splitEmpId(String currentId) {
        if(currentId != null) {
            String[] strings = currentId.split("E0");
            //System.out.println(strings[1]);
            int id = Integer.parseInt(strings[1]);

            //System.out.println("first ="+id);
            id++;
            //System.out.println("seconf "+id);
            return "E0" + id;
        }
        return "E001";
    }
}
