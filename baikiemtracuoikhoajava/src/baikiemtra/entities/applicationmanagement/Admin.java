package baikiemtra.entities.applicationmanagement;

import baikiemtra.entities.login.User;

import java.math.BigDecimal;

public class Admin extends User {

    private BigDecimal money =BigDecimal.ZERO;
    private String nameAdmin;



    public Admin(String useName, String passWord, String email, Role role, String nameAdmin) {
        super(useName, passWord, email, role);
        this.nameAdmin = nameAdmin;
    }

    public String getNameAdmin() {
        return nameAdmin;
    }

    public void setNameAdmin(String nameAdmin) {
        this.nameAdmin = nameAdmin;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "Name Admin=" +nameAdmin +
                "money=" + money +
                '}';
    }
}
