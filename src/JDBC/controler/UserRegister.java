package JDBC.controler;

import JDBC.dao.*;
import JDBC.dto.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import static JDBC.Main.userList;
import static JDBC.Main.stock;

public class UserRegister {

    public static User register() {
        System.out.println("Welcome To Maktab_dg_Store, Please register: ");
        while (true) {
            Scanner scn = new Scanner(System.in);
            System.out.println("Please enter your user name:");
            String userName = scn.nextLine();
            System.out.println("Please enter password: ");
            String passWord = scn.nextLine();

            if (userList.isEmpty()) {
                User user = new User(userName, passWord);
                userForm(user);
                return user;
            }

            for (User user :
                    userList) {
                if (user.getUserName().equals(userName)) {
                    if (user.getPassword().equals(passWord)) {
                        return user;
                    } else {
                        System.out.println("You entered uncorrected password. Please try again! ");
                        continue;
                    }
                }
            }
            User user = new User(userName, passWord);
            userForm(user);
            return user;
        }
    }


    public static void userForm(User user) {
        Scanner scn = new Scanner(System.in);
        System.out.println("Before shopping, please fill the following form. ");
        System.out.println(user.getUserName() + " please enter your name: ");
        user.setName(scn.nextLine());
        System.out.println("Please enter your family: ");
        user.setFamily(scn.next());
        System.out.println("Please enter your age: ");
        user.setAge(scn.nextInt());
        System.out.println("Please enter your mobil number:");
        user.setPhone(scn.next());
        System.out.println("Please enter your email address:");
        user.seteMail(scn.next());
        System.out.println("Please enter your address:");
        user.setAddress(scn.next());

        userList.add(user);

        UserDao userDao = new UserDao();
        userDao.insertUser(userList.size(),user);
    }

    public static void sortUsers(List<User> userList) {
        Comparator<User> myLambdaComparator = (o1, o2) -> o1.getAge() == o2.getAge() ? 0 : o1.getAge() > o2.getAge() ? 1 : -1;

        userList.sort(myLambdaComparator);
    }

    public static void userDoShopping(User user) {
        Scanner scn = new Scanner(System.in);

        while (true) {

            System.out.println("What do you choose for shopping?" +
                    "\n If your shopping is finished, please enter 0 " +
                    "\n If you want to delete your  previous purchase, please enter \'d\'" +
                    "\n If you want to see market stock, please enter \'m\'" +
                    "\n Otherwise, Please enter the name of goods that you want:");
            String choice = scn.next();

            if (choice.equals("0")) {
                break;
            }
            if (choice.equals("m")) {
                printMenu();
            } else if (choice.equals("d")) {
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

    public static void printMenu() {
        System.out.println("Goods     Type       Price         count");
        System.out.println("_____________________________________________");
        for (Store goods :
                stock.keySet()) {
            System.out.println(goods.getName() + "---------" + goods.getType() + "---------" +
                    goods.getPrice() + "---------" + stock.get(goods));
        }

    }

    public static void update(Store purchase, int count) {
        String name = purchase.getName();
        if (stock.get(purchase) == count) {
            stock.remove(purchase, count);

            new StoreDao().updateAndDelete(name);

        } else {
            int number = stock.get(purchase) - count;
            stock.put(purchase, number);

            new StoreDao().update(name, number);
        }
    }
}