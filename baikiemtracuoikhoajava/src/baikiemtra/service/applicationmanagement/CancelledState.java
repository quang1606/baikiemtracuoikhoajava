package baikiemtra.service.applicationmanagement;

import baikiemtra.entities.applicationmanagement.Order;

public class CancelledState implements OrderState{
    @Override
    public void next(Order context) {
        System.out.println("Đơn hàng đã bị hủy, không thể chuyển sang trạng thái tiếp theo.");
    }

    @Override
    public void prev(Order context) {
        System.out.println("Đơn hàng đã bị hủy, không thể quay lại trạng thái trước.");
    }

    @Override
    public void printStatus() {
        System.out.println("Trạng thái: Đơn hàng đã bị hủy.");
    }

    @Override
    public String getStateName() {
        return "Đã hủy";
    }
}
