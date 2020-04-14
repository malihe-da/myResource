package JDBC.dto;

import java.util.*;

public class User {
    private String userName;
    private String password;
    private String name;
    private String family;
    private int age;
    private String phone;
    private String eMail;
    private String address;
    Map<Store, Integer> shoppingBasket = new HashMap<>(5);

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public void addToBasket(Store goods, int count) {
        for (Store kala :
                shoppingBasket.keySet()) {
            if (kala.equals(goods)) {
                int size = shoppingBasket.get(kala);
                shoppingBasket.put(goods, size + count);
                return;
            }
        }
        if (shoppingBasket.size() == 5) {
            System.out.println("Your Basket is Full. If you want this goods, you may delete your previous purchase.");
            return;
        }

        shoppingBasket.put(goods, count);
    }

    public void deleteFromBasket(Store goods) {
        for (Store kala :
                shoppingBasket.keySet()) {
            if (kala.equals(goods)) {
                int size = shoppingBasket.get(kala);
                if(size>1) {
                    shoppingBasket.put(goods, size - 1);
                }
                else {
                    shoppingBasket.remove(goods);
                }
            }

        }
    }

    public void printBasket() {
        if(shoppingBasket.isEmpty()){
            System.out.println("Your basket is empty!");
            return;
        }
        System.out.println(getUserName() + " shopping basket:");
        System.out.println("\n purchase ----------  count");
        System.out.println("______________________________________");

        for (Store kala :
                shoppingBasket.keySet()) {
            System.out.println(kala.getName() + " ----------  " + shoppingBasket.get(kala));
        }

    }

    public void totalPrice(){
        int total=0;
        for (Store kala :
                shoppingBasket.keySet()) {
            total += (kala.getPrice())* shoppingBasket.get(kala);
        }
        System.out.println("Your Total Price is = " + total);
    }

    public void cleanBasket(){
        shoppingBasket.clear();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Map<Store, Integer> getShoppingBasket() {
        return shoppingBasket;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userName, user.userName) &&
                Objects.equals(password, user.password) &&
                Objects.equals(name, user.name) &&
                Objects.equals(family, user.family) &&
                Objects.equals(phone, user.phone) &&
                Objects.equals(eMail, user.eMail) &&
                Objects.equals(address, user.address) &&
                Objects.equals(shoppingBasket, user.shoppingBasket);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, password, name, family, phone, eMail, address, shoppingBasket);
    }

    @Override
    public String toString() {
        return "User= " +
                 name +  family +
                ", age=" + age ;
    }
}
