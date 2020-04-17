package JDBC.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static JDBC.dao.UserDao.getConnection;

public class StoreDao {


    public void updateAndDelete(String name){

        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement =
                    getConnection().prepareStatement("DELETE from store where name =?");
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
                    getConnection().prepareStatement("update store set count=? where name =?");
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
