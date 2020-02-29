package entities;

import org.mindrot.jbcrypt.BCrypt;

import java.util.Objects;

public class Customer {
    private int id=0;
    private String username;
    private Password password;
    private boolean hasOrder;

    public boolean HasOrder() {
        return hasOrder;
    }

    public void setHasOrder(boolean hasOrder) {
        this.hasOrder = hasOrder;
    }

    public Customer(String username, String password) {
        this.username = username;
        this.password=new Password(password);
        hasOrder=false;
        id++;
    }

    public Customer(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = new Password(password);
        hasOrder=false;
    }

    public Customer(int id, String username) {
        this.id = id;
        this.username = username;
        hasOrder=false;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Password getPassword() {
        return password;
    }

    public void setPassword(Password password) {
        this.password = password;
    }
    public void setPassword(String password){
        this.password=new Password(password);
    }
    public void setPasswordsHash(String hash){
        this.password=new Password("");
        this.password.setHash(hash);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id == customer.id &&
                hasOrder == customer.hasOrder &&
                Objects.equals(username, customer.username) &&
                Objects.equals(password, customer.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, hasOrder);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password=" + password +
                ", hasOrder=" + hasOrder +
                '}';
    }
}
