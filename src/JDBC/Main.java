package JDBC;

import JDBC.controler.FillTables;
import JDBC.controler.UserRegister;
import JDBC.dao.StoreDao;
import JDBC.dao.UserDao;
import JDBC.dto.Store;
import JDBC.dto.User;

import java.util.*;

import static JDBC.controler.UserRegister.*;

public class Main {

    public static Map<Store, Integer> stock = new HashMap<>();
    public static List<User> userList=new ArrayList<>();


    public static void main(String[] args) {

        Scanner scn = new Scanner(System.in);
        FillTables tables = new FillTables();
        tables.fillTables();
        UserDao.readData();
        User user = register();

        printMenu();

        userDoShopping(user);

        if (user.getShoppingBasket().size() > 0) {
            user.printBasket();
            while (true) {
                System.out.println("Do you confirm your purchase (Y or N)?");
                String ans = scn.next();
                if (ans.equals("Y")) {
                    user.totalPrice();

                    for (Store purchase :
                            user.getShoppingBasket().keySet()) {

                        for (Store goods :
                                stock.keySet()) {
                            if (Objects.equals(purchase, goods)){
                                UserRegister.update(goods, user.getShoppingBasket().get(purchase));
                            }
                        }
                    }
                    user.cleanBasket();
                    System.out.println("Hope you are happy with your purchase.");
                    break;
                } else if (ans.equals("N")) {
                    userDoShopping(user);
                    break;
                }
                System.out.println("Invalid input!");
            }
        }

        sortUsers(userList);
        System.out.println("Sort the customers by their age: \n" + userList.toString());

    }
}
