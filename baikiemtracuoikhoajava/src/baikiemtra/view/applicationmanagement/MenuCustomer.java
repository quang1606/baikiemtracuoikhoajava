package baikiemtra.view.applicationmanagement;

import baikiemtra.data.Database;
import baikiemtra.entities.applicationmanagement.Customer;
import baikiemtra.entities.login.User;
import baikiemtra.service.applicationmanagement.CustomerService;
import baikiemtra.untils.Utils;
import baikiemtra.view.login.MenuLogin;

import java.util.Scanner;

public class MenuCustomer {
    CustomerService customerService =CustomerService.getInstance();
    public void displayCustomer(Scanner scanner, User user){

        do {
            System.out.println("Moi ban chon!");
            System.out.println("1 - Chinh sua thong tin");
            System.out.println("2 - Tim kiem mon an");
            System.out.println("3 - Xem danh gia mon an");
            System.out.println("4 - Mua hang");
            System.out.println("5 - Don hang");
            System.out.println("6 - Huy don hang:");
            System.out.println("7 - Nap tien");
            System.out.println("8 - Rut tien");
            System.out.println("9 - Dang xuat");
            selectCustomer(scanner,user);
        }while (true);

    }

    private void displaySearch(Scanner scanner, Customer customer){
        System.out.println("Ban cho  kieu tim kiem:");
        System.out.println("1 - Tim kiem theo ten: ");
        System.out.println("2 - Tim kiem theo the loai: ");
        selectSearch(scanner,customer);

    }

    private void displayOrder(Scanner scanner, Customer customer, User user){
        do {
            System.out.println("1 - Trang thai don hang");
            System.out.println("2 - Lich su don hang da giao");
            System.out.println("3 - Lich su don hang da huy");
            System.out.println("4 - Danh gia don");
            System.out.println("5 - Don nhap");
            System.out.println("6 - Mua hang trong don nhap:");
            System.out.println("7 - Quay ve trang chu:");
            selectOrder(scanner,customer,user);
        }while (true);
    }

    private void selectCustomer(Scanner scanner,User user){
        int choice = Utils.inputInteger(scanner);
        Customer customer =  Database.customerMap.get(user.getId());
        switch (choice){
            case 1:
                customerService.UpdateInformation(scanner, customer);
                break;
            case 2:
                displaySearch(scanner,customer);
                break;
            case 3:
                customerService.seeComments(scanner);
                break;
            case 4:
                customerService.purchase(scanner,customer);
                break;
            case 5:
                displayOrder(scanner,customer,user);
                break;

            case 6:
                customerService.cancelOrder(scanner,customer);
                break;
            case 7:
                customerService.recharge(scanner,customer);
                break;
            case 8:
                customerService.withdrawMoney(scanner,customer);
                break;
            case 9:
                user=null;
                System.out.println("Ban da dang xuat thanh cong");
                new  MenuLogin().displayMenuPreLogin(scanner);
                break;
            default:
                System.out.println("Lua chon khong hop le!");
        }
    }
    private void selectSearch(Scanner scanner, Customer customer ){
        int choice = Utils.inputInteger(scanner);
        switch (choice){
            case 1:
                customerService.searchMenu(scanner,customer);
                break;
            case 2:
                customerService.searchByCategory(scanner,customer);
                break;
            default:
                System.out.println("Lua chon khong hop le:");
        }
    }
    private void selectOrder(Scanner scanner, Customer customer,User user){
        int choice = Utils.inputInteger(scanner);
        switch (choice){
            case 1:
                customerService.orderState(customer);
                break;
            case 2:
                customerService.displayDeliveredState(customer);
                break;
            case 3:
                customerService.CancelledState(customer);
                break;
            case 4:
                customerService.feedback(customer,scanner);
                break;
            case 5:
                customerService.displayDraftOrder(customer);
                break;
            case 6:

                customerService.buyDraftOrder(scanner,customer);
                break;
            case 7:
                displayCustomer(scanner,user);
                break;

            default:
                System.out.println("Lua chon khong hop le:");
        }
    }

}
