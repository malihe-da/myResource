package JDBC.dao;


import JDBC.dto.User;

import java.sql.*;

import static JDBC.Main.userList;


public class UserDao {


    public static Connection getConnection() {
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

    public void insertUser(int id, User user) {
        try {
            Connection connection = getConnection();

            String query = "insert into customer values(?, ?, ?, ?,?, ?, ?,? ,?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,id);
            statement.setString(2, user.getUserName());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getName());
            statement.setString(5, user.getFamily());
            statement.setInt(6, user.getAge());
            statement.setString(7, user.getPhone());
            statement.setString(8, user.geteMail());
            statement.setString(9, user.getAddress());

            statement.executeUpdate();

            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void readData(){
        try {
            Connection connection = getConnection();

            String query = "select * from customer order by id desc limit 1";
            PreparedStatement statement = connection.prepareStatement(query);

            ResultSet rs = statement.executeQuery(query);
            if(rs.next()) {
                int id = rs.getInt("id");
                String userName = rs.getString("user_name");
                String password = rs.getString("password");
                User user = new User(userName, password);
                user.setName(rs.getString("name"));
                user.setFamily(rs.getString("family"));
                user.setAge(rs.getInt("age"));
                user.setPhone(rs.getString("phone"));
                user.seteMail(rs.getString("gmail"));
                user.setAddress(rs.getString("adress"));
                userList.add(user);

            }

            rs.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
