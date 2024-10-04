package baikiemtra.service.applicationmanagement;

import baikiemtra.data.Database;
import baikiemtra.entities.applicationmanagement.Admin;
import baikiemtra.entities.applicationmanagement.Saller;
import baikiemtra.untils.Utils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Scanner;

public class AdminService {
    private static AdminService adminService;
    private AdminService(){

    }

    public static  synchronized AdminService getInstance(){
        if (adminService==null){
            adminService = new AdminService();
        }
        return adminService;
    }
    //Ham khoa tai khoa
    public void accountLock(Scanner scanner){
        if (Database.sallerMap.isEmpty()){
            System.out.println("Khong co tai khoan nao");
            return;
        }
        for (Map.Entry<Integer, Saller> entry : Database.sallerMap.entrySet()){
            System.out.println(entry.getValue());
        }
        System.out.println("Nhap vao id tai khoan muon khoa");
        int id= Utils.inputInteger(scanner);
        Saller saller = Database.sallerMap.get(id);
        if (saller!=null){
            System.out.println("Nhap vao so ngay khoa tai khoan!");
            int day = Utils.inputInteger(scanner);
            lockAccount(saller,day);
            Database.accountLockSeller.put(saller.getId(),saller);
        }else {
            System.out.println("Tai khoan ko ton tai!");
        }

    }

    //Mo khoa tai khoan
    public void unlockAccount(Scanner scanner){
        if (Database.accountLockSeller.isEmpty()){
            System.out.println("Khong co tai khoan nao");
            return;
        }
        for (Map.Entry<Integer, Saller> entry : Database.accountLockSeller.entrySet()){
            System.out.println(entry.getValue());
        }
        System.out.println("Nhap vao id tai khoan muon mo");
        int id= Utils.inputInteger(scanner);
        Saller saller = Database.sallerMap.get(id);
        if (saller!=null){
            unlockAccount(saller);
            Database.accountLockSeller.remove(saller.getId());
        }else {
            System.out.println("Tai khoan ko ton tai!");
        }

    }
    //Ham khoa tai khoan
    private void lockAccount(Saller saller,int day){
        saller.setAvailable(false);
        saller.setLockTime(LocalDateTime.now());
        saller.setLockDuration(day);
        System.out.println("Tài khoản co ID = "+ saller.getId() + " đã bị khóa trong " + day + " ngày.");
    }

    //Ham mo khoa tai khoan
    private   void unlockAccount(Saller saller){
        saller.setLockTime(null);
        saller.setAvailable(true);
        saller.setLockDuration(0);
    }

    public boolean isLocked(Saller saller){
        if (!saller.isAvailable()){
            if (saller.getLockTime()!=null && saller.getLockTime().plusDays(saller.getLockDuration()).isBefore(LocalDateTime.now())){
                unlockAccount(saller);
            }
        }
        return !saller.isAvailable();
    }

    //Ham rut tien
    public void withdrawMoney(Scanner scanner){
        Admin admin =Database.adminList.get(1);
        if (admin.getMoney().compareTo(BigDecimal.ZERO)==0){
            System.out.println("Tai khoan ban 0 co tien ");
            return;
        }

        System.out.println("Số tiền bạn đang có: " + admin.getMoney() + " K");
        double money;

        do {
            System.out.println("Nhập số tiền bạn muốn rút: ");
            money = Utils.inputDouble(scanner);
            BigDecimal withdrawMoney = BigDecimal.valueOf(money);
            if (withdrawMoney.compareTo( admin.getMoney()) <= 0) {
                System.out.println("Rút tiền thành công: " + withdrawMoney + " K");
                admin.setMoney(admin.getMoney().subtract(withdrawMoney));
                return;
            } else {
                System.out.println("Không đủ tiền, vui lòng nhập lại.");
            }
        } while (true); // Tiếp tục yêu cầu cho đến khi có đầu vào hợp lệ

    }

}
