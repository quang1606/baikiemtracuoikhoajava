package baikiemtra.view.applicationmanagement;

import baikiemtra.data.Database;
import baikiemtra.entities.applicationmanagement.Shipper;
import baikiemtra.entities.login.User;
import baikiemtra.service.applicationmanagement.ShipperService;
import baikiemtra.untils.Utils;
import baikiemtra.view.login.MenuLogin;

import java.util.Scanner;

public class MenuShipper {

    ShipperService shipperService = ShipperService.getInstance();
    public void displayShipper(Scanner scanner, User user){
        do {
            System.out.println("Moi ban chon!");
            System.out.println("1 - Chinh sua thong tin");
            System.out.println("2 - Danh sach don hang cho giao gan nhat ");
            System.out.println("3 - Xac nhan don hang can giao");
            System.out.println("4 - Xac nhan don hang giao thanh cong");
            System.out.println("5 - Lich su giao hang thanh cong");
            System.out.println("6 - Xem don hang da huy");
            System.out.println("7 - Rut tien");
            System.out.println("8 -Lich su giao dich: ");
            System.out.println("9 -Dang xuat");
            selectShipper(scanner,user);
        }while (true);

    }

    private void selectShipper(Scanner scanner , User user) {
        int choice = Utils.inputInteger(scanner);
        Shipper shipper = Database.shipperMap.get(user.getId());
        switch (choice) {
            case 1:
                shipperService.updateInformation(scanner,shipper);
                break;
            case 2:
                shipperService.nearbyOrderList(shipper);
                break;
            case 3:
                shipperService.orderConfirmation(scanner,shipper);
                break;
            case 4:
                shipperService.deliveredState(scanner,shipper);
                break;
            case 5:
                shipperService.displayDeliveredState(shipper);
                break;
            case 6:
                shipperService.cancelledState(shipper);
                break;

            case 7:
                shipperService.withdrawMoney(scanner,shipper);
                break;
            case 8:
                shipperService.selectTransactionHistory(shipper);
                break;
            case 9:
                user=null;
                System.out.println("Ban da dang xuat thanh cong");
                new MenuLogin().displayMenuPreLogin(scanner);
                break;
            default:
                System.out.println("Lua chon khong hop le!");

        }
    }
}
