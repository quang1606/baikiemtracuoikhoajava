package baikiemtra.entities.applicationmanagement;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDateTime;

public class TransactionHistory {
    private static  int idCount;
    private int id;
    private BigDecimal money;
    private LocalDateTime localDateTime ;
    private int idCustomer;
    private  int idSeller;
    private int idShipper;
    private int idAdmin;

    public TransactionHistory( LocalDateTime localDateTime, BigDecimal money) {
        this.id = ++idCount;
        this.localDateTime = localDateTime;
        this.money = money;
    }


    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public static int getIdCount() {
        return idCount;
    }

    public static void setIdCount(int idCount) {
        TransactionHistory.idCount = idCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public int getIdSeller() {
        return idSeller;
    }

    public void setIdSeller(int idSeller) {
        this.idSeller = idSeller;
    }

    public int getIdShipper() {
        return idShipper;
    }

    public void setIdShipper(int idShipper) {
        this.idShipper = idShipper;
    }

    public int getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }
    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("+#,##0.00;-#,##0.00");
        return "Transaction ID: " + id + ", Date: " + localDateTime + ", Money: " + df.format(money);
    }
}
