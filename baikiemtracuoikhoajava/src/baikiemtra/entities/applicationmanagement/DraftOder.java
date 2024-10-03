package baikiemtra.entities.applicationmanagement;

import java.math.BigDecimal;

public class DraftOder {
    private static  int idCount;
    private int id;
    private String nameSeller;
    private String menuName;
    private int idCustomer;
    private int quantity;
    private int idSeller;
    private int idMenu;

    public DraftOder( String nameSeller, String menuName, int idCustomer, int quantity,int idSeller, int idMenu) {
        this.id = ++idCount;
        this.nameSeller = nameSeller;
        this.menuName = menuName;
        this.idCustomer = idCustomer;
        this.quantity = quantity;
        this.idSeller =idSeller;
        this.idMenu = idMenu;

    }

    public int getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(int idMenu) {
        this.idMenu = idMenu;
    }

    public int getIdSeller() {
        return idSeller;
    }

    public void setIdSeller(int idSeller) {
        this.idSeller = idSeller;
    }

    public static int getIdCount() {
        return idCount;
    }

    public static void setIdCount(int idCount) {
        DraftOder.idCount = idCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameSeller() {
        return nameSeller;
    }

    public void setNameSeller(String nameSeller) {
        this.nameSeller = nameSeller;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "DraftOder{" +
                "id=" + id +
                ", nameSeller='" + nameSeller + '\'' +
                ", menuName='" + menuName + '\'' +
                ", idCustomer=" + idCustomer +
                ", quantity=" + quantity +

                '}';
    }
}
