package baikiemtra.service.applicationmanagement;

import java.util.Scanner;

public interface GeneralInformation <T>{
    void insert(T object);
    void UpdateInformation(Scanner scanner, T object);
    void displayDeliveredState(T object);
    void CancelledState(T object);
    void withdrawMoney(Scanner scanner, T object);
}
