package baikiemtra.entities.applicationmanagement;

import baikiemtra.entities.login.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Saller extends User {

    private String name;
    private double longitude;
    private double latitude;
    private boolean available;
    private BigDecimal money =BigDecimal.ZERO;
    private LocalDateTime lockTime;
    private int lockDuration ;

    public Saller(String useName, String passWord, String email, Role role, String name, double longitude, double latitude, boolean available) {
        super(useName, passWord, email, role);
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
        this.available = true;
        this.lockTime = null;
        this.lockDuration = 0;
    }

    public int getLockDuration() {
        return lockDuration;
    }

    public void setLockDuration(int lockDuration) {
        this.lockDuration = lockDuration;
    }

    public LocalDateTime getLockTime() {
        return lockTime;
    }

    public void setLockTime(LocalDateTime lockTime) {
        this.lockTime = lockTime;
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

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }


    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public BigDecimal getMoney() {
        return money;
    }

    @Override
    public String toString() {
        return "Saller{" +
                "ID= "+getId()+
                "name='" + name + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", available=" + available +
                ", money=" + money +
                '}';
    }
}
