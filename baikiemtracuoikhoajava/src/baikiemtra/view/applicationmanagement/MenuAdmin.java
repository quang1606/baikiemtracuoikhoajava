package baikiemtra.view.applicationmanagement;

import baikiemtra.entities.login.User;
import baikiemtra.service.applicationmanagement.AdminService;
import baikiemtra.untils.Utils;
import baikiemtra.view.login.MenuLogin;

import java.util.Scanner;

public class MenuAdmin {
AdminService adminService = AdminService.getInstance();
    public void displayAdmin(Scanner scanner, User user){
        do {
            System.out.println("Moi ban chon!");
            System.out.println("1 - Khoa tai khoan ban hang");
            System.out.println("2 - Mo tai khoan ban hang");
            System.out.println("3 - Khoa tai khoan lai xe");
            System.out.println("4 - Mo tai khoan lai xe");
            System.out.println("5 - Rut tien khoi he thong");
            System.out.println("6 -Dang xuat");
            selectAdmin(scanner,user);
        }while (true);

    }

    public void selectAdmin(Scanner scanner, User user){
        int choice = Utils.inputInteger(scanner);
        switch (choice){
            case 1:
                adminService.accountLock(scanner);
                break;
            case 2:
                adminService.unlockAccount(scanner);
                break;
            case 3:

                break;
            case 4:

                break;
            case 5:
                adminService.withdrawMoney(scanner);
            case 6:
                user=null;
                System.out.println("Ban da dang xuat thanh cong");
                new MenuLogin().displayMenuPreLogin(scanner);
                break;

            default:
                System.out.println("Lua chon khong hop le:");
        }
    }

}
