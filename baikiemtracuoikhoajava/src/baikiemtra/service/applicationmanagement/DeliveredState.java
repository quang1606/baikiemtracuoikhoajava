package baikiemtra.service.applicationmanagement;

import baikiemtra.entities.applicationmanagement.Order;

public class DeliveredState implements OrderState {
    @Override
    public void next(Order context) {
        System.out.println("Đơn hàng đã được giao thành công, không thể chuyển tiếp.");
    }

    @Override
    public void prev(Order context) {
        context.setState(new AwaitingDeliveryState()); // Quay lại trạng thái "Chờ giao hàng"
    }

    @Override
    public void printStatus() {
        System.out.println("Trạng thái: Đã giao hàng thành công.");
    }

    @Override
    public String getStateName() {
        return "Đã giao hàng thành công";
    }
}
