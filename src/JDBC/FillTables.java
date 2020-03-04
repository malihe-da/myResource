package JDBC;

import JDBC.dto.ElectronicsDeviseStore;
import JDBC.dto.ReadableProducesStore;
import JDBC.dto.ShoeSore;

import java.sql.*;

import static JDBC.Main.stock;

public class FillTables {

    public void fillTables(){

        insertIntoStore(1, "Maktab Store");

        insertIntoShoeStore(1, "AlStar", 800000, 5, "sport");
        insertIntoShoeStore(2, "Puma", 600000, 3, "sport");
        insertIntoShoeStore(3, "Adidas", 700000, 4, "sport");
        insertIntoShoeStore(4, "Maral", 300000, 6, "Women's shoes");
        insertIntoShoeStore(5, "Danieli", 650000, 8, "Women's shoes");

        insertIntoElectronicsStore(1, "Sony", 4000000, 6, "TV");
        insertIntoElectronicsStore(2, "LG", 3500000, 4, "TV");
        insertIntoElectronicsStore(3, "Samsung", 7000000, 6, "phone");
        insertIntoElectronicsStore(4, "Apple", 1200000, 3, "phone");
        insertIntoElectronicsStore(5, "iPad", 1600000, 4, "tablet");

        insertIntoReadableStore(5, "Nature", 80000, 9, "Journal");
        insertIntoReadableStore(6, "National geographic", 90000, 3, "Journal");
        insertIntoReadableStore(7, "AMS", 100000, 5, "Annals");
        insertIntoReadableStore(8, "Iranian MS", 200000, 8, "Annals");


    }

    public void insertIntoStore(int id, String name){
        try {
            Class.forName("com.mysql.jdbc.Driver");
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
    public void insertIntoShoeStore(int id, String name, double price, int count, String type){
        stock.put(new ShoeSore(name, type, price), count);
      try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/dg_maktab_store",
                    "root", null);

            String query="insert into shoes values(?, ?, ? , 1, ?, ?)";
            PreparedStatement statement=connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.setString(2, name);
            statement.setDouble(3, price);
            statement.setInt(4, count);
            statement.setString(5, type);

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
    public void insertIntoElectronicsStore(int id, String name, double price, int count, String type){
        stock.put(new ElectronicsDeviseStore(name, type, price), count);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/dg_maktab_store",
                    "root", null);

            String query="insert into electronical_device values(?, ?, ? , 1, ?, ?)";
            PreparedStatement statement=connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.setString(2, name);
            statement.setDouble(3, price);
            statement.setInt(4, count);
            statement.setString(5, type);

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
    public void insertIntoReadableStore(int id, String name, double price, int count, String type){
        stock.put(new ReadableProducesStore(name, type, price), count);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/dg_maktab_store",
                    "root", null);

            String query="insert into readable_producs values(?, ?, ? , 1, ?, ?)";
            PreparedStatement statement=connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.setString(2, name);
            statement.setDouble(3, price);
            statement.setInt(4, count);
            statement.setString(5, type);

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
