package com.example.signish.Data;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC {

    private static final String SQL_CREATE = "CREATE TABLE FICHAJE"
            + "("
            + FichajeEsquema.FichajeEntrada._ID
            + " INTEGER PRIMARY KEY,"
            +  FichajeEsquema.FichajeEntrada.currentTime + " TEXT NOT NULL,"
            + "UNIQUE (" + FichajeEsquema.FichajeEntrada.currentTime + "))";

    public static void main(String[] args) {

//        try (Connection conn = DriverManager.getConnection(
//                "jdbc:postgresql://192.168.0.22/", "angela", "ruizrobles");
//             Statement statement = conn.createStatement()) {
//
//            // if DDL failed, it will raise an SQLException
//            statement.execute(SQL_CREATE);
//
//        } catch (SQLException e) {
//            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
  }

}
