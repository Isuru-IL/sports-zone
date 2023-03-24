package lk.ijse.sports_zone.model;

import lk.ijse.sports_zone.db.DBConnection;
import lk.ijse.sports_zone.dto.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserModel {

    public static Boolean save(User user) throws SQLException {
        String sql = "INSERT INTO User(userName,empId, password, email, jobTitle)"+
                "VALUES(?, ?, ?, ?, ?)";
        try(PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)){
            pstm.setString(1, user.getUserName());
            pstm.setString(2, user.getEmpId());
            pstm.setString(3,user.getPassword());
            pstm.setString(4, user.getEmail());
            pstm.setString(5, user.getJobTitle());

            int affectedRows = pstm.executeUpdate();
            return affectedRows>0;
        }
    }

    public static User searchByUsername(String userName) throws SQLException {
        String sql = "SELECT email FROM User WHERE userName=? ";
        try(PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)){
            pstm.setString(1, userName);
            ResultSet resultSet = pstm.executeQuery();

            User user1 = new User();
            if(resultSet.next()) {
                String email = resultSet.getString(1);
                //System.out.println(email + "111");
                user1.setEmail(email);

            }
            return user1;
        }
    }


    public static boolean updatePassword(User user) throws SQLException {
        String sql = "UPDATE user SET password =? WHERE userName = ?";
        try(PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)){

            pstm.setString(1, user.getPassword());
            pstm.setString(2, user.getUserName());

            return pstm.executeUpdate() > 0 ;
        }
    }

    public static User checkLoginAccess(User userCheckLogin) throws SQLException {
        User userCheck = new User();

        String sql = "SELECT userName, password, jobTitle FROM User WHERE userName =?";

        try(PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)){

            pstm.setString(1, userCheckLogin.getUserName());

            ResultSet resultSet = pstm.executeQuery();
            if(resultSet.next()){
                userCheck.setUserName(resultSet.getString(1));
                userCheck.setPassword(resultSet.getString(2));
                userCheck.setJobTitle(resultSet.getString(3));
            }
           return userCheck;
        }
    }
}
