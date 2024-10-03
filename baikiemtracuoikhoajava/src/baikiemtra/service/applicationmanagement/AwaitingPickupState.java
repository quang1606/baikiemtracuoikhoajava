package baikiemtra.service.applicationmanagement;

import baikiemtra.entities.applicationmanagement.Order;

public class AwaitingPickupState implements OrderState {
    @Override
    public void next(Order context) {
        context.setState(new AwaitingDeliveryState()); // Chuyển sang trạng thái "Chờ giao hàng"
    }

    @Override
    public void prev(Order context) {
        context.setState(new PendingConfirmationState()); // Quay lại trạng thái "Chờ xác nhận"
    }

    @Override
    public void printStatus() {
        System.out.println("Trạng thái: Chờ lấy hàng.");
    }

    @Override
    public String getStateName() {
        return "Chờ lấy hàng";
    }
}

