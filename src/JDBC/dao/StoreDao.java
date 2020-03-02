package JDBC.dao;

import JDBC.dto.ElectronicsDeviseStore;
import JDBC.dto.ReadableProducesStore;
import JDBC.dto.ShoeSore;
import JDBC.dto.Store;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static JDBC.Main.stock;

public class StoreDao {
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

    public void update(Store purchase, int count){
        String name= purchase.getName();
        if(stock.get(purchase) == count){
            stock.remove(purchase, count);

            if(purchase instanceof ShoeSore){
                new ShoeStoreDao().updateAndDelete(name);
            }
            if(purchase instanceof ReadableProducesStore){
                new ReadableProduceDao().updateAndDelete(name);
            }
            if(purchase instanceof ElectronicsDeviseStore){
                new ElectronicsDeviseDao().updateAndDelete(name);
            }
        }
        else {
            int number = stock.get(purchase) - count;
            stock.put(purchase, number);

            if(purchase instanceof ShoeSore){
                new ShoeStoreDao().update(name, number);
            }
            if(purchase instanceof ReadableProducesStore){
                new ReadableProduceDao().update(name, number);
            }
            if(purchase instanceof ElectronicsDeviseStore){
                new ElectronicsDeviseDao().update(name, number);
            }
        }
    }

}
