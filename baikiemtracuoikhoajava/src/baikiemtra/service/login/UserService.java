package baikiemtra.service.login;

import baikiemtra.data.Database;
import baikiemtra.entities.applicationmanagement.Customer;
import baikiemtra.entities.applicationmanagement.Role;
import baikiemtra.entities.applicationmanagement.Saller;
import baikiemtra.entities.applicationmanagement.Shipper;
import baikiemtra.entities.login.User;
import baikiemtra.service.applicationmanagement.CustomerService;
import baikiemtra.service.applicationmanagement.SallerService;
import baikiemtra.service.applicationmanagement.ShipperService;
import baikiemtra.untils.Utils;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserService {
    private String username;
    CustomerService customerService = CustomerService.getInstance();
    ShipperService shipperService =ShipperService.getInstance();
    SallerService sallerService =SallerService.getInstance();

    //Ham dang ky
    public User register(Scanner scanner) {
        Role role = SelectSubscriberRole(scanner);
        String username = promptForUsername(scanner);
        String password = promptForPassword(scanner);
        String email = promptForEmail(scanner);
        System.out.println("Đăng ký thành công. Moi ban nhap vao thong tin tai khoan");
        User user = new User(username,password,email,role);
        Database.userMap.put(username,user);
        if (role.equals(Role.CUSTOMER)){
            Customer customer = customerService.inputCustomer(scanner,user);
            customerService.insert(customer);
            Database.customerMap.put(user.getId(),customer);
        }else if (role.equals(Role.SALLER)){
            Saller saller = sallerService.intputSaller(scanner,user);
            sallerService.insert(saller);
            Database.sallerMap.put(user.getId(),saller);
        }else if (role.equals(Role.SHIPPER)){
            Shipper shipper = shipperService.intputShipper(scanner,user);
            shipperService.insert(shipper);
            Database.shipperMap.put(user.getId(),shipper);
        }
        return user;
    }

    //Ham dang nhap
    public User login(Scanner scanner){
        System.out.println("Mời b nhập username");
        username = scanner.nextLine();
        System.out.println("Mời b nhập password");
        String password = scanner.nextLine();
        User user = Database.userMap.get(username);
        if(user!=null && password.equals(user.getPassWord()) ){
            return user;
        }
        return null;
    }

    private String promptForUsername(Scanner scanner) {
        String username;
        while (true) {
            System.out.println("Mời bạn nhập username:");
            username = scanner.nextLine();
            User user = Database.userMap.get(username);
            if (user==null) {
                return username;
            } else {
                System.out.println("Username đã tồn tại!");
            }
        }
    }

    private String promptForPassword(Scanner scanner) {
        String password;
        while (true) {
            System.out.println("Mời bạn nhập password:");
            password = scanner.nextLine();
            if (isValidPassword(password)) {
                return password;
            } else {
                System.out.println("Mật khẩu yếu, vui lòng nhập lại!");
            }
        }
    }

    private String promptForEmail(Scanner scanner) {
        String email;
        while (true) {
            System.out.println("Mời bạn nhập email:");
            email = scanner.nextLine();

            if (isValidEmail(email) && checkEmail(email)) {
                return email;
            } else if (isValidEmail(email) && !checkEmail(email)){
                System.out.println("email ko hop le ");

            }
        }

    }
    private boolean checkEmail(String email){
        for (User user : Database.userMap.values()){
            if (email.equals(user.getEmail()))
                return false;
        }
        return true;
    }

    private Role SelectSubscriberRole(Scanner scanner) {
        System.out.println("Moi ban chon dang ky!");
        System.out.println("1 - Khach hang");
        System.out.println("2 - Shop ban do");
        System.out.println("3 - Nguoi giao hang");
        int choose = Utils.inputInteger(scanner);
        Role role = null;
        switch (choose) {
            case 1:
                role = Role.CUSTOMER;
                break;
            case 2:
                role = Role.SALLER;
                break;
            case 3:
                role = Role.SHIPPER;
                break;
            default:
                System.out.println("Lua chon khong hop le!");
        }
        return role;
    }

    public String getUsername() {
        return username; // Trả về giá trị username cuối cùng đã nhập
    }
    private boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(Utils.EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean isValidPassword(String password) {
        Pattern pattern = Pattern.compile(Utils.PASSWORD_REGEX);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
    public void setPassWord(Scanner scanner, String username){
        User user = Database.userMap.get(username);
        System.out.println("Nhập email để xác nhận tài khoản: ");
        String email = scanner.nextLine();
        if(user.getEmail().equals(email)){
            user.setPassWord(promptForPassword(scanner));
            System.out.println("Ban da doi mat khau thanh cong");
        }

    }


}
