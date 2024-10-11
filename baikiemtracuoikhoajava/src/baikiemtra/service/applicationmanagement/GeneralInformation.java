package baikiemtra.service.applicationmanagement;

import java.util.Scanner;

public interface GeneralInformation <T>{
    void insert(T object);
    void updateInformation(Scanner scanner, T object);
    void displayDeliveredState(T object);
    void cancelledState(T object);
    void withdrawMoney(Scanner scanner, T object);
    void selectTransactionHistory(T object);
}
