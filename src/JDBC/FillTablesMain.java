package JDBC;

import java.sql.*;

public class FillTablesMain {
    public static void main(String[] args) {

        insertIntoStore(1, "Maktab Store");

    }
    public static void insertIntoStore(int id, String name){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/dg_maktab_store",
                    "root", null);

            String query="insert into store values(?, ?)";
            PreparedStatement statement=connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.setString(2, name);

            statement.executeUpdate();

            statement.close();
            connection.close();
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static void insertIntoShoeStore(int id, String name, double price){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/dg_maktab_store",
                    "root", null);

            String query="insert into shoes values(?, ?, ? , 1)";
            PreparedStatement statement=connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.setString(2, name);
            statement.setDouble(3, price);

            statement.executeUpdate();

            statement.close();
            connection.close();
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static void insertIntoElectronicsStore(int id, String name, double price){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/dg_maktab_store",
                    "root", null);

            String query="insert into electronical_device values(?, ?, ? , 1)";
            PreparedStatement statement=connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.setString(2, name);
            statement.setDouble(3, price);

            statement.executeUpdate();

            statement.close();
            connection.close();
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static void insertIntoReadableStore(int id, String name, double price){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/dg_maktab_store",
                    "root", null);

            String query="insert into readable_producs values(?, ?, ? , 1)";
            PreparedStatement statement=connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.setString(2, name);
            statement.setDouble(3, price);

            statement.executeUpdate();

            statement.close();
            connection.close();
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
