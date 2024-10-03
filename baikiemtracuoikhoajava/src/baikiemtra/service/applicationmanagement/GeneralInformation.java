package baikiemtra.service.applicationmanagement;

import java.util.Scanner;

public interface GeneralInformation <T>{
    void insert(T object);
    void UpdateInformation(Scanner scanner, T object);
}
