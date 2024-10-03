package baikiemtra.view.login;

import baikiemtra.data.Database;
import baikiemtra.entities.applicationmanagement.Customer;
import baikiemtra.entities.applicationmanagement.Role;
import baikiemtra.entities.login.User;
import baikiemtra.service.applicationmanagement.CustomerService;
import baikiemtra.service.login.UserService;
import baikiemtra.untils.Utils;
import baikiemtra.view.applicationmanagement.MenuAdmin;
import baikiemtra.view.applicationmanagement.MenuCustomer;
import baikiemtra.view.applicationmanagement.MenuShipper;
import baikiemtra.view.applicationmanagement.MenuSaller;

import java.util.Scanner;

public class MenuLogin {

    MenuAdmin menuAdmin =new MenuAdmin();
    MenuCustomer menuCustomer = new MenuCustomer();
    MenuShipper menuShipper = new MenuShipper();
    MenuSaller menuSaller = new MenuSaller();
    UserService userService = new UserService();
    public void displayMenuPreLogin(Scanner scanner){
        System.out.println("----Moi ban chon!----");
        System.out.println("1 - Dang nhap");
        System.out.println("2 - Dang ky");

        selectMenuPreLogin(scanner);
    }
    public void displayMenuLogGin(Scanner scanner, String useName){
        System.out.println("1 - Đăng nhập lại");
        System.out.println("2 - Quên mật khẩu");
        selectMenuLogin(scanner,useName);
    }
    public void  selectMenuPreLogin (Scanner scanner){
        int choose = Utils.inputInteger(scanner);
        switch (choose){
            case 1:
                User user =userService.login(scanner);
                if(user!=null){
                    if(user.getRole().equals(Role.ADMIN)){
                        menuAdmin.displayAdmin(scanner,user);
                    }else if (user.getRole().equals(Role.SALLER)){
                        menuSaller.displayShop(scanner,user);
                    }else if (user.getRole().equals(Role.CUSTOMER)){
                        menuCustomer.displayCustomer(scanner,user);
                    }else {
                        menuShipper.displayShipper(scanner,user);
                    }
                }else {
                    String username = userService.getUsername();
                    if (Database.userMap.get(username) != null) {
                        System.out.println(username);
                        System.out.println("Sai mật khẩu");
                        displayMenuLogGin(scanner, username);
                    } else {
                        System.out.println("Tài khoản không tồn tại");
                        displayMenuPreLogin(scanner);
                    }
                }
                break;
            case 2:
                User  isRegisterSuccess = userService.register(scanner);
                if (isRegisterSuccess!=null){
                    if(isRegisterSuccess.getRole().equals(Role.ADMIN)){
                        menuAdmin.displayAdmin(scanner,isRegisterSuccess);
                    }else  if (isRegisterSuccess.getRole().equals(Role.SALLER)){
                        menuSaller.displayShop(scanner,isRegisterSuccess);
                    }else  if(isRegisterSuccess.getRole().equals(Role.CUSTOMER)){

                        menuCustomer.displayCustomer(scanner,isRegisterSuccess);
                    }else {
                        menuShipper.displayShipper(scanner,isRegisterSuccess);
                    }
                }
                displayMenuPreLogin(scanner);
                break;
            default:
                System.out.println("Chon chuc nang khong hop le!");
        }
    }
    private  void selectMenuLogin(Scanner scanner, String useName){
        int choose = Utils.inputInteger(scanner);
        switch (choose){
            case 1:
                displayMenuPreLogin(scanner);
                break;
            case 2:
                userService.setPassWord(scanner,useName);
                break;
            default:
                System.out.println("Lua chon khong hop le!");
                displayMenuPreLogin(scanner);
        }
    }

}
