package kz.orion.sk_orion_springboot.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public Connection databaseLink;

    public Connection getConnection() {
        String databaseName = "sport_complex";
        String databaseUser = "bestuser";
        String databasePassword = "bestuser";
        String url = "jdbc:mysql://localhost:3306/" + databaseName;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }catch (Exception e){
            e.printStackTrace();
        }

        return databaseLink;
    }
}
