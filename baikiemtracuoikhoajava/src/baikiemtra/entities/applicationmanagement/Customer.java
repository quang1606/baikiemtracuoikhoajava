package baikiemtra.entities.applicationmanagement;

import baikiemtra.entities.login.User;

import java.math.BigDecimal;

public class Customer extends User {
    private String name;
    private double longitude;
    private double latitude;
    private BigDecimal money =BigDecimal.ZERO;
    private String phoneNumber;

    public Customer( String useName, String passWord, String email, Role role, String name, double longitude, double latitude, String phoneNumber) {
        super(useName, passWord, email, role);
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Customer{" +getId()+
                "name='" + name + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", money=" + money +
                '}';
    }
}
