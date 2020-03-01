package JDBC.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ElectronicsDeviseDao {
    private Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dg_maktab_store",
                    "root", null);
            return connection;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
