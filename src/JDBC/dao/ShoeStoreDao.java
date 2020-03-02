package JDBC.dao;

import JDBC.dto.Store;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ShoeStoreDao {
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

    public void updateAndDelete(String name){

        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement =
                    getConnection().prepareStatement("DELETE from shoes where name =?");
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void update(String name, int count){
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement =
                    getConnection().prepareStatement("update shoes set count=? where name =?");
            preparedStatement.setInt(1, count);
            preparedStatement.setString(2, name);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
