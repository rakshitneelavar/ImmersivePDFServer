package com.ssdi.immersivepdf.dao;

import com.ssdi.immersivepdf.model.Login.Login;
import com.ssdi.immersivepdf.util.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginUserDao {

    public boolean login(Login login){
        try {
            Connection connection = DBConnector.getConnection();
            String sql = "SELECT * FROM USER WHERE email=? AND password=?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, login.getEmail());
            pstmt.setString(2, login.getPassword());
            ResultSet res = pstmt.executeQuery();
            if (res.first()) {
                return true;
            }
            else{
                return false;
            }
            /* connection.close(); */
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}