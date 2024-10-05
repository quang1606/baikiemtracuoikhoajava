package baikiemtra.entities.applicationmanagement;

public class Food {
    private static int idCount;
    private int id;
    private String name;
    private TypeOfFood typeOfFood;
    private double price;
    private int idSaller;
    private int quantitySold = 0;
    private double rateStars = 0.0;
    public Food(String name, TypeOfFood typeOfFood, double price, int idSaller) {
        this.id = ++idCount;
        this.name = name;
        this.typeOfFood = typeOfFood;
        this.price = price;
        this.idSaller = idSaller;
    }

    public double getRateStars() {
        return rateStars;
    }

    public void setRateStars(double rateStars) {
        this.rateStars = rateStars;
    }

    public static int getIdCount() {
        return idCount;
    }

    public static void setIdCount(int idCount) {
        Food.idCount = idCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TypeOfFood getTypeOfFood() {
        return typeOfFood;
    }

    public void setTypeOfFood(TypeOfFood typeOfFood) {
        this.typeOfFood = typeOfFood;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getIdSaller() {
        return idSaller;
    }

    public void setIdSaller(int idSaller) {
        this.idSaller = idSaller;
    }

    public int getQuantitySold() {
        return quantitySold;
    }

    public void setQuantitySold(int quantitySold) {
        this.quantitySold = quantitySold;
    }

    @Override
    public String toString() {
        return "Menu Details:" +
                ", ID: " + id +
                ", Tên món: " + name +
                ", Loại món: " + typeOfFood +
                ", Giá: " + price + " VND" +
                ", ID của người bán: " + idSaller +
                ", Số lượng đã bán: " + quantitySold +
                ", Đánh giá sản phẩm: " + getRateStars();
    }

}
