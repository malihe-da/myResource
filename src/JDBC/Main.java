package JDBC;

import JDBC.dao.StoreDao;
import JDBC.dto.Store;
import JDBC.dto.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static Map<Store, Integer> stock = new HashMap<>();


    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        FillTables tables = new FillTables();
        tables.fillTables();

        User user = register();
        userForm(user);

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
                                new StoreDao().update(goods, user.getShoppingBasket().get(purchase));
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


    }

    private static void userDoShopping(User user) {
        Scanner scn = new Scanner(System.in);

        while (true) {

            System.out.println("What do you choose for shopping?" +
                    "\n If your shopping is finished, please enter 0 " +
                    "\n If you want to delete your  previous purchase, please enter \'d\'" +
                    "\n Otherwise, Please enter the name of goods that you want:");
            String choice = scn.next();

            if (choice.equals("0")) {
                break;
            }
            else if (choice.equals("d")) {
                user.printBasket();
                System.out.println("which one you want to delete?");
                String deleteOne = scn.next();
                for (Store goods :
                        stock.keySet()) {
                    if (goods.getName().equals(deleteOne)) {
                        user.deleteFromBasket(goods);
                        break;
                    }
                }
            }

            boolean flag = true;
            Store purchase = null;
            int count = 0;

            for (Store goods :
                    stock.keySet()) {
                if (goods.getName().equals(choice)) {
                    purchase = goods;
                    flag = false;
                    break;
                }
            }
            if (flag) {
                System.out.println("This goods is not exist in maktab_dg_store, Please try again.");
                continue;
            }
            do {
                System.out.println("How many do you want?");
                count = scn.nextInt();
                if (count > stock.get(purchase)) {
                    System.out.println("There is only " + stock.get(purchase) + " of " + purchase.getName());
                } else {
                    break;
                }
            } while (true);
            user.addToBasket(purchase, count);
        }
    }

    private static void printMenu() {
        System.out.println("Goods     Type       Price         count");
        System.out.println("_____________________________________________");
        for (Store goods :
                stock.keySet()) {
            System.out.println(goods.getName() + "---------" + goods.getType() + "---------" +
                    goods.getPrice() + "---------" + stock.get(goods));
        }
    }

    private static User register() {
        System.out.println("Welcome To Maktab_dg_Store, Please register: ");
        Scanner scn = new Scanner(System.in);
        System.out.println("Please enter your user name:");
        String userName = scn.nextLine();
        System.out.println("Please enter password: ");
        String passWord = scn.nextLine();

        return new User(userName, passWord);
    }

    private static void userForm(User user) {
        Scanner scn = new Scanner(System.in);
        System.out.println("Before shopping, please fill the following form. ");
        System.out.println(user.getUserName() + " please enter your name: ");
        user.setName(scn.nextLine());
        System.out.println("Please enter your family: ");
        user.setFamily(scn.next());
        System.out.println("Please enter your mobil number:");
        user.setPhone(scn.next());
        System.out.println("Please enter your email address:");
        user.seteMail(scn.next());
        System.out.println("Please enter your address:");
        user.setAddress(scn.next());

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/dg_maktab_store",
                    "root", null);

            String query="insert into customer values(1, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement=connection.prepareStatement(query);
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getName());
            statement.setString(3, user.getFamily());
            statement.setString(4, user.getPhone());
            statement.setString(5, user.geteMail());
            statement.setString(6, user.getAddress());

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
