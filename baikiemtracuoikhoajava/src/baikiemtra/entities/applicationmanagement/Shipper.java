package baikiemtra.entities.applicationmanagement;

import baikiemtra.entities.login.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Shipper extends User {
    private String name;
    private double longitude;
    private double latitude;
    private BigDecimal money =BigDecimal.ZERO;
    private LocalDateTime lockTime;
    private int lockDuration ;
    private boolean available;
    int count = 0;

    public Shipper(String useName, String passWord, String email, Role role, String name, double longitude, double latitude) {
        super(useName, passWord, email, role);
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
        this.available = true;
        this.lockTime = null;
        this.lockDuration = 0;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public LocalDateTime getLockTime() {
        return lockTime;
    }

    public void setLockTime(LocalDateTime lockTime) {
        this.lockTime = lockTime;
    }

    public int getLockDuration() {
        return lockDuration;
    }

    public void setLockDuration(int lockDuration) {
        this.lockDuration = lockDuration;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
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
