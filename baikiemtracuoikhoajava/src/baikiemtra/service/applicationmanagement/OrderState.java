package baikiemtra.service.applicationmanagement;

import baikiemtra.entities.applicationmanagement.Order;

public interface OrderState {
    void next(Order context); // Chuyển sang trạng thái tiếp theo
    void prev(Order context); // Quay lại trạng thái trước
    void printStatus(); // In trạng thái hiện tại
    String getStateName(); // Lấy tên trạng thái
}
