package baikiemtra.view.applicationmanagement;

import baikiemtra.entities.login.User;

import java.util.Scanner;

public class MenuAdmin {

    public void displayAdmin(Scanner scanner, User user){
        System.out.println("Moi ban chon!");
        System.out.println("1 - Khoa tai khoan ban hang");
        System.out.println("2 - Khoa tai khoan tai xe");
        System.out.println("3 - Khoa tai khoan nguoi dung");
        System.out.println("3 - Rut tien khoi he thong");
        System.out.println("4 -Dang xuat");
    }
}
