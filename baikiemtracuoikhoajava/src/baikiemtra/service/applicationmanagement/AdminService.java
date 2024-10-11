package baikiemtra.service.applicationmanagement;

import baikiemtra.data.Database;
import baikiemtra.entities.applicationmanagement.Admin;
import baikiemtra.entities.applicationmanagement.Saller;
import baikiemtra.entities.applicationmanagement.Shipper;
import baikiemtra.entities.applicationmanagement.TransactionHistory;
import baikiemtra.untils.Utils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Scanner;

public class AdminService {
    private static AdminService adminService;

    private AdminService() {

    }

    public static synchronized AdminService getInstance() {
        if (adminService == null) {
            adminService = new AdminService();
        }
        return adminService;
    }

    //Ham khoa tai khoan seller
    public void accountLock(Scanner scanner) {
        if (Database.sallerMap.isEmpty()) {
            System.out.println("Khong co tai khoan nao");
            return;
        }
        for (Map.Entry<Integer, Saller> entry : Database.sallerMap.entrySet()) {
            if (entry.getValue().isAvailable()) {
                System.out.println(entry.getValue());
            }
        }
        System.out.println("Nhap vao id tai khoan muon khoa");
        int id = Utils.inputInteger(scanner);
        Saller saller = Database.sallerMap.get(id);
        if (saller != null) {
            System.out.println("Nhap vao so ngay khoa tai khoan!");
            int day = Utils.inputInteger(scanner);
            lockAccountSeller(saller, day);
            Database.accountLockSeller.put(saller.getId(), saller);
        } else {
            System.out.println("Tai khoan ko ton tai!");
        }

    }

    //Mo khoa tai khoan seller
    public void unlockAccount(Scanner scanner) {
        if (Database.accountLockSeller.isEmpty()) {
            System.out.println("Khong co tai khoan nao");
            return;
        }
        for (Map.Entry<Integer, Saller> entry : Database.accountLockSeller.entrySet()) {
            System.out.println(entry.getValue());
        }
        System.out.println("Nhap vao id tai khoan muon mo");
        int id = Utils.inputInteger(scanner);
        Saller saller = Database.sallerMap.get(id);
        if (saller != null) {
            unlockAccountSeller(saller);
            Database.accountLockSeller.remove(saller.getId());
            System.out.println("Tai khoan da duoc mo!");
        } else {
            System.out.println("Tai khoan ko ton tai!");
        }

    }

    //Ham khoa tai khoan
    private void lockAccountSeller(Saller saller, int day) {
        saller.setAvailable(false);
        saller.setLockTime(LocalDateTime.now());
        saller.setLockDuration(day);
        System.out.println("Tài khoản co ID = " + saller.getId() + " đã bị khóa trong " + day + " ngày.");
    }

    //Ham mo khoa tai khoan
    private void unlockAccountSeller(Saller saller) {
        saller.setLockTime(null);
        saller.setAvailable(true);
        saller.setLockDuration(0);
    }

    //Kiem tra tai khoan den thoi gian mo khoa chua
    public boolean isLocked(Saller saller) {
        if (!saller.isAvailable()) {
            if (saller.getLockTime() != null && saller.getLockTime().plusDays(saller.getLockDuration()).isBefore(LocalDateTime.now())) {
                unlockAccountSeller(saller);
            }
        }
        return !saller.isAvailable();
    }

    //khoa tai khoan ship
    public void accountLockShipper(Scanner scanner) {
        if (Database.sallerMap.isEmpty()) {
            System.out.println("Khong co tai khoan nao");
            return;
        }
        for (Map.Entry<Integer, Shipper> entry : Database.shipperMap.entrySet()) {
            if (entry.getValue().isAvailable()) {
                System.out.println(entry.getValue());
            }
        }
        System.out.println("Nhap vao id tai khoan muon khoa");
        int id = Utils.inputInteger(scanner);
        Shipper shipper = Database.shipperMap.get(id);
        if (shipper != null) {
            System.out.println("Nhap vao so ngay khoa tai khoan!");
            int day = Utils.inputInteger(scanner);
            lockAccount(shipper, day);
            Database.accountLockShipper.put(shipper.getId(), shipper);
        } else {
            System.out.println("Tai khoan ko ton tai!");
        }
    }

//mo tai khoan ship
    public void unlockAccountShipper(Scanner scanner) {
        if (Database.accountLockShipper.isEmpty()) {
            System.out.println("Khong co tai khoan nao");
            return;
        }
        for (Map.Entry<Integer, Shipper> entry : Database.accountLockShipper.entrySet()) {
            System.out.println(entry.getValue());
        }
        System.out.println("Nhap vao id tai khoan muon mo");
        int id = Utils.inputInteger(scanner);
        Shipper shipper = Database.shipperMap.get(id);
        if (shipper != null) {
            unlockAccount(shipper);
            Database.accountLockShipper.remove(shipper.getId());
            System.out.println("Tai khoan da duoc mo!");
        } else {
            System.out.println("Tai khoan ko ton tai!");
        }

    }
//Khoa tai khoan shipper
    private void lockAccount(Shipper shipper, int day) {
        shipper.setAvailable(false);
        shipper.setLockTime(LocalDateTime.now());
        shipper.setLockDuration(day);
        System.out.println("Tài khoản co ID = " + shipper.getId() + " đã bị khóa trong " + day + " ngày.");
    }

    //Ham mo khoa tai khoan shipper
    private void unlockAccount(Shipper shipper) {
        shipper.setLockTime(null);
        shipper.setAvailable(true);
        shipper.setLockDuration(0);
    }

    //Kiem tra tai khoan den thoi gian mo khoa chua
    public boolean isLockedShipper(Shipper shipper) {
        if (!shipper.isAvailable()) {
            if (shipper.getLockTime() != null && shipper.getLockTime().plusDays(shipper.getLockDuration()).isBefore(LocalDateTime.now())) {
                unlockAccount(shipper);
            }
        }
        return !shipper.isAvailable();
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
                TransactionHistory transactionHistory2 = new TransactionHistory(LocalDateTime.now(), BigDecimal.ZERO.subtract(withdrawMoney));
                Database.transactionHistory.put(transactionHistory2.getId(), transactionHistory2);
                Database.transactionHistory.get(transactionHistory2.getId()).setIdAdmin(Database.adminList.get(1).getId());
                return;
            } else {
                System.out.println("Không đủ tiền, vui lòng nhập lại.");
            }
        } while (true); // Tiếp tục yêu cầu cho đến khi có đầu vào hợp lệ

    }

    //Hien thi lich su giao dich
    public  void selectTransactionHistory(){
        if ( Database.transactionHistory.isEmpty()){
            System.out.println("Ko co giao dich !");
            return;

        }
        boolean check= false;
        for (Map.Entry<Integer,TransactionHistory> entry: Database.transactionHistory.entrySet()){
            if(entry.getValue().getIdAdmin()== Database.adminList.get(1).getId()){
                System.out.println(entry.getValue());
                check = true;
            }
        }

        if (!check){
            System.out.println("Khong co giao dich");
        }
    }

}
