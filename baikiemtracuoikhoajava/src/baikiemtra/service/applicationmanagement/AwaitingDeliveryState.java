package baikiemtra.service.applicationmanagement;

import baikiemtra.entities.applicationmanagement.Order;

public class AwaitingDeliveryState implements OrderState {
    @Override
    public void next(Order context) {
        context.setState(new DeliveredState()); // Chuyển sang trạng thái "Đã giao hàng"
    }

    @Override
    public void prev(Order context) {
        context.setState(new AwaitingPickupState()); // Quay lại trạng thái "Chờ lấy hàng"
    }

    @Override
    public void printStatus() {
        System.out.println("Trạng thái: Chờ giao hàng.");
    }

    @Override
    public String getStateName() {
        return "Chờ giao hàng";
    }
}
