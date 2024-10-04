package baikiemtra.view.applicationmanagement;

import baikiemtra.data.Database;
import baikiemtra.entities.applicationmanagement.Customer;
import baikiemtra.entities.applicationmanagement.Saller;
import baikiemtra.entities.login.User;
import baikiemtra.service.applicationmanagement.SallerService;
import baikiemtra.untils.Utils;
import baikiemtra.view.login.MenuLogin;

import java.util.Scanner;

public class MenuSaller {
    SallerService sallerService = SallerService.getInstance();
    public void displayShop(Scanner scanner, User user){
        do {
            System.out.println("Moi ban chon!");
            System.out.println("1 - Chinh sua thong tin quan");
            System.out.println("2 - Them mon an");
            System.out.println("3 - Sua mon an");
            System.out.println("4 - Xoa mon an");
            System.out.println("5 - Hien thi danh sach mon an");
            System.out.println("6 - Them voucher");
            System.out.println("7 - Hien thi danh sach voucher");
            System.out.println("8 - Xac nhan don hang");
            System.out.println("9 - Trang thai don hang");
            System.out.println("10 - Xem danh gia don hang");
            System.out.println("11 - Rut tien");
            System.out.println("12 -Dang xuat");
            selectShop(scanner,user);
        }while (true);
    }
    public void displayOrderStatus(Scanner scanner, Saller saller,User user){
        do {
            System.out.println("1 - Cho lay hang");
            System.out.println("2 - Cho giao hang");
            System.out.println("3 - Da giao thanh cong");
            System.out.println("4 - Don hang da huy");
            System.out.println("5 - Ve trang chu");
            selectOrderStatus(scanner,saller,user);
        }while (true);

    }

    private void selectOrderStatus(Scanner scanner, Saller saller,User user) {
        int choose = Utils.inputInteger(scanner);
        switch (choose) {
            case 1:
                sallerService.displayAwaitingPickupState(saller);
                break;
            case 2:
                sallerService.displayAwaitingDeliveryState(saller);
                break;
            case 3:
                sallerService.displayDeliveredState(saller);
                break;
            case 4:
                sallerService.CancelledState(saller);
                break;
            case 5:
                displayShop(scanner,user);
                break;
            default:
                System.out.println("Lua chon khong hop le!");
        }
    }

    private void selectShop(Scanner scanner, User user){
        int choose = Utils.inputInteger(scanner);
        Saller saller = Database.sallerMap.get(user.getId());
        switch (choose){
            case 1:
                sallerService.UpdateInformation(scanner,saller);
                break;
            case 2:
                sallerService.addFood(scanner,saller);
                break;
            case 3:
                sallerService.updateFood(scanner,saller);
                break;
            case 4:
                sallerService.deleteFood(scanner,saller);
                break;
            case 5:
                sallerService.selectFood(saller);
                break;
            case 6:
                sallerService.insertVoucher(scanner,saller);
                break;
            case 7:
                sallerService.selectVoucher(saller);
                break;
            case 8:
                sallerService.orderConfirmation(scanner,saller);
                break;
            case 9:
                displayOrderStatus(scanner,saller,user);
                break;
            case 10:
                sallerService.SeeOrderReviews(saller);
                break;

            case 11:
                sallerService.withdrawMoney(scanner,saller);
                break;
            case 12:
                user=null;
                System.out.println("Ban da dang xuat thanh cong");
                new  MenuLogin().displayMenuPreLogin(scanner);
                break;
            default:
                System.out.println("Lua chon khong hop le!");
        }


    }
}
