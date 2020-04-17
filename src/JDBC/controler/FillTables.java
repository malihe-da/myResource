package JDBC.controler;

import JDBC.dto.Store;

import java.sql.*;

import static JDBC.Main.stock;
import static JDBC.dao.UserDao.getConnection;

public class FillTables {

    public void fillTables(){


        Store alstar = new Store("AlStar", "sport", 1800000);
        stock.put(alstar, 5);
        Store puma = new Store("Puma", "sport", 600000);
        stock.put(puma, 7);
        Store adidas = new Store("Adidas", "sport", 700000);
        stock.put(adidas, 6);
        Store maral = new Store("Maral", "Women's shoes", 300000);
        stock.put(maral, 12);
        Store danieli = new Store("Danieli", "Women's shoes", 650000);
        stock.put(danieli, 8);


        Store nature = new Store("Nature", "Journal", 8000);
        stock.put(nature, 19);
        Store ng = new Store("National geographic", "Journal", 9000);
        stock.put(ng, 6);
        Store ams = new Store("AMS", "Annals", 23000);
        stock.put(ams, 5);
        Store ims = new Store("Iranian MS", "Annals", 2000);
        stock.put(ims, 8);

        Store sony = new Store("Sony", "TV", 4000000);
        stock.put(sony, 7);
        Store LG = new Store("LG", "TV", 3500000);
        stock.put(LG, 4);
        Store sum = new Store("Samsung", "phone", 7000000);
        stock.put(sum, 13);
        Store Apple = new Store("Apple", "phone", 1200000);
        stock.put(Apple, 4);
        Store ipad = new Store("ipad", "tablet", 1600000);
        stock.put(ipad, 6);


        int id=1;
       /* for (Store product:
             stock.keySet()) {

            insertIntoStore(id, product, stock.get(product));
            id++;
        }*/

    }

    public void insertIntoStore(int id, Store product, int count){

        try {
            Connection connection = getConnection();

            String query="insert into store values(?, ?, ?, ?, ?)";
            PreparedStatement statement=connection.prepareStatement(query);

            statement.setInt(1, id);
            statement.setString(2, product.getName());
            statement.setDouble(3, product.getPrice());
            statement.setInt(4, count);
            statement.setString(5, product.getType());

            statement.executeUpdate();

            statement.close();
            connection.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}
