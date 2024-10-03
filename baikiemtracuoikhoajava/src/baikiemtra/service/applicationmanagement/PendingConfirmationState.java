package baikiemtra.service.applicationmanagement;

import baikiemtra.entities.applicationmanagement.Order;

public class PendingConfirmationState implements OrderState {
    @Override
    public void next(Order context) {
        context.setState(new AwaitingPickupState()); // Chuyển sang trạng thái "Chờ lấy hàng"
    }

    @Override
    public void prev(Order context) {
        System.out.println("Không thể quay lại, đơn hàng đang chờ xác nhận.");
    }

    @Override
    public void printStatus() {
        System.out.println("Trạng thái: Chờ xác nhận.");
    }

    @Override
    public String getStateName() {
        return "Chờ xác nhận";
    }
}
