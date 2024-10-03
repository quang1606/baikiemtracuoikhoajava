package baikiemtra.entities.applicationmanagement;

import java.math.BigDecimal;

public class Admin {
    private BigDecimal money =BigDecimal.ZERO;

    public Admin(BigDecimal money) {
        this.money = money;
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
                "money=" + money +
                '}';
    }
}
