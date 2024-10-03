package baikiemtra.entities.applicationmanagement;

import baikiemtra.service.applicationmanagement.OrderState;
import baikiemtra.service.applicationmanagement.PendingConfirmationState;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Order {
    private static int idCount;
    private int id;
    private int idSeller;
    private int idCustomer;
    private int idShipper;
    private String nameCustomer;
    private String nameSeller;
    private String foodName;
    private int quantity;
    private int floorFee;
    private double sumShip;
    private BigDecimal foodBill;
    private  BigDecimal totalAmount;
    private String phoneNumber;
    private OrderState state;
    private LocalDateTime lastUpdateTime;
    private double distance = 0;
    private int idFood;
    private int idVoucher;

    public Order(int idSeller, int idCustomer, String nameCustomer, String nameSeller, String menuName, int quantity, int floorFee, double sumShip, BigDecimal foodBill, BigDecimal totalAmount, String phoneNumber, int idFood) {
        this.id = ++idCount;
        this.idSeller = idSeller;
        this.idCustomer = idCustomer;
        this.nameCustomer = nameCustomer;
        this.nameSeller = nameSeller;
        this.foodName = menuName;
        this.quantity = quantity;
        this.floorFee = floorFee;
        this.sumShip = sumShip;
        this.foodBill = foodBill;
        this.totalAmount = totalAmount;
        this.phoneNumber =phoneNumber;
        this.state = new PendingConfirmationState(); // Trạng thái ban đầu là "Chờ xác nhận"
        this.lastUpdateTime = LocalDateTime.now(); // Lưu thời gian hiện tại
        this.idFood=idFood;
    }



    public int getIdVoucher() {
        return idVoucher;
    }

    public void setIdVoucher(int idVoucher) {
        this.idVoucher = idVoucher;
    }

    // Định dạng thời gian thành chuỗi đọc dễ hơn
    private String getFormattedTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return lastUpdateTime.format(formatter);
    }
    public OrderState getState() {
        return state;
    }

    public void setState(OrderState state) {
        this.state = state;
        this.lastUpdateTime = LocalDateTime.now(); // Cập nhật thời gian mỗi khi thay đổi trạng thái
    }
    public void nextState() {
        state.next(this); // Chuyển trạng thái tiếp theo
    }

    public void prevState() {
        state.prev(this); // Quay lại trạng thái trước
    }
    public void printStatus() {
        state.printStatus(); // In trạng thái hiện tại
        System.out.println("Thời gian cập nhật: " + getFormattedTime()); // Hiển thị thời gian cập nhật
    }

    public LocalDateTime getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(LocalDateTime lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public static int getIdCount() {
        return idCount;
    }

    public int getIdShipper() {
        return idShipper;
    }

    public void setIdShipper(int idShipper) {
        this.idShipper = idShipper;
    }

    public static void setIdCount(int idCount) {
        Order.idCount = idCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdSeller() {
        return idSeller;
    }

    public void setIdSeller(int idSeller) {
        this.idSeller = idSeller;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getNameCustomer() {
        return nameCustomer;
    }

    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
    }

    public String getNameSeller() {
        return nameSeller;
    }

    public void setNameSeller(String nameSeller) {
        this.nameSeller = nameSeller;
    }


    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getFloorFee() {
        return floorFee;
    }

    public void setFloorFee(int floorFee) {
        this.floorFee = floorFee;
    }

    public double getSumShip() {
        return sumShip;
    }

    public void setSumShip(double sumShip) {
        this.sumShip = sumShip;
    }

    public void setSumShip(int sumShip) {
        this.sumShip = sumShip;
    }

    public BigDecimal getFoodBill() {
        return foodBill;
    }

    public void setFoodBill(BigDecimal foodBill) {
        this.foodBill = foodBill;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return "Thông tin Đơn hàng:" +
                ", Mã đơn hàng: " + id +
                ", Tên khách hàng: " + nameCustomer +
                ", Tên người bán: " + nameSeller +
                ", Món ăn: " + foodName +
                ", Số lượng: " + quantity +
                ", Phí sàn: " + floorFee + " VND" +
                ", Phí vận chuyển: " + sumShip + " VND" +
                ", Tiền món ăn: " + foodBill + " VND" +
                ", Tổng tiền thanh toán: " + totalAmount + " VND" +
                ", Số điện thoại khách hàng: " + phoneNumber +
                ", Trạng thái đơn hàng: " + state.getStateName() +
                ", Thời gian cập nhật: " + lastUpdateTime +idShipper;
    }


    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int getIdFood() {
        return idFood;
    }

    public void setIdFood(int idFood) {
        this.idFood = idFood;
    }
}
