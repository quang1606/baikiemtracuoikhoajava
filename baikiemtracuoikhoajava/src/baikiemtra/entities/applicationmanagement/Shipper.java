package baikiemtra.entities.applicationmanagement;

import baikiemtra.entities.login.User;

import java.math.BigDecimal;

public class Shipper extends User {
    private String name;
    private double longitude;
    private double latitude;
    private BigDecimal money =BigDecimal.ZERO;



    public Shipper(String useName, String passWord, String email, Role role, String name, double longitude, double latitude) {
        super(useName, passWord, email, role);
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;

    }

    public void setMoney(BigDecimal money) {
        this.money = money;
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
}
