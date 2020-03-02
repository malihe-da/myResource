package JDBC.dto;

import java.util.Objects;

public class Store {
    String name;
    String type;
    double price;

    public Store(String name, String type, double price) {
        this.name = name;
        this.type=type;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Store store = (Store) o;
        return Double.compare(store.price, price) == 0 &&
                Objects.equals(name, store.name) &&
                Objects.equals(type, store.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type, price);
    }
}
