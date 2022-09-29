package com.example.demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import static java.sql.DriverManager.getConnection;

public class Database1 extends Configs {
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName +
                "?serverTimezone=UTC";

        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Подключился");
        dbConnection = getConnection(connectionString, dbUser, dbPass);

        return dbConnection;
    }

    public void RegPolzovatel(User user) {

        String insert = "INSERT INTO " + Constat.USER_TABLE + "(" +
                Constat.USER_FIRSTNAME + "," + Constat.USER_LASTNAME + "," +
                Constat.USER_USERNAME + "," + Constat.USER_PASSWORD + "," + Constat.USER_GENDER + ")" +
                "VALUES(?,?,?,?,?)";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, user.getFirstName());
            prSt.setString(2, user.getLastName());
            prSt.setString(3, user.getUserName());
            prSt.setString(4, user.getPassword());
            prSt.setString(5, user.getGender());

            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();;
        }
    }
    public ResultSet getUser(User user) {
        ResultSet resSet = null;

        String select = "SELECT * FROM " + Constat.USER_TABLE + " WHERE " + Constat.USER_USERNAME + "=? AND " +
                Constat.USER_PASSWORD + "=?";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, user.getUserName());
            prSt.setString(2, user.getPassword());

            resSet = prSt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();;
        }
        return resSet;

    }
}
