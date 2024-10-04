package baikiemtra.service.applicationmanagement;

import baikiemtra.data.Database;
import baikiemtra.entities.applicationmanagement.*;
import baikiemtra.entities.login.User;
import baikiemtra.untils.Utils;

import java.math.BigDecimal;
import java.util.*;

public class ShipperService implements GeneralInformation<Shipper> {
    private static ShipperService shipperService;

    private ShipperService(){

    }

    public static synchronized ShipperService getInstance(){
        if (shipperService==null){
            shipperService = new ShipperService();
        }
        return shipperService;
    }
    AdminService adminService = AdminService.getInstance();

    @Override
    public void insert(Shipper object) {
        Database.shipperMap.put(object.getId(),object);
    }

    @Override
    public void UpdateInformation(Scanner scanner, Shipper object) {
        object.setName(getInputName(scanner));
        object.setLatitude(getInputLatitude(scanner));
        object.setLongitude(getInputLongitude(scanner));
        System.out.println("Thong tin da duoc cap nhat !");

    }
//Ham xem don giao thanh cong
    @Override
    public void displayDeliveredState(Shipper object) {
        if (adminService.isLockedShipper(object)){
            System.out.println("Tài khoản của bạn đã bị khóa "+object.getLockDuration()+" ngay");
            return;
        }
        if ( Database.deliveredMap.isEmpty()){
            System.out.println("Ko co don hang nhap!");
            return;
        }
        boolean hasOrders = false;
        for (Map.Entry<Integer,Order> entry : Database.deliveredMap.entrySet()){
            if (entry.getValue().getIdShipper()==object.getId()){
                System.out.println(entry.getValue());
                hasOrders =true;
            }
        }
        if(!hasOrders) {
            System.out.println("Ban chua giao don hang nao!");
        }
    }
//Ham Xem don hang da huy
    @Override
    public void CancelledState(Shipper object) {
        if (adminService.isLockedShipper(object)){
            System.out.println("Tài khoản của bạn đã bị khóa "+object.getLockDuration()+" ngay");
            return;
        }
        if (Database.abortMap.isEmpty()) {
            System.out.println("Không có đơn nào da huy");
            return;
        }
        boolean hasOrders = false;
        for (Map.Entry<Integer, Order> entry : Database.abortMap.entrySet()) {
            if (object.getId() == entry.getValue().getIdShipper()) {
                System.out.println(entry.getValue());
                hasOrders = true;
            }
        }
        if (!hasOrders) {
            System.out.println("Không có đơn nào.");
        }
    }
    //Ham rut tien
    @Override
    public void withdrawMoney(Scanner scanner, Shipper object) {
        if (object.getMoney().compareTo(BigDecimal.ZERO)==0){
            System.out.println("Tai khoan ban 0 co tien ");
            return;
        }
        System.out.println("Số tiền bạn đang có: " + object.getMoney() + " K");
        double money;

        do {
            System.out.println("Nhập số tiền bạn muốn rút: ");
            money = Utils.inputDouble(scanner);

            BigDecimal withdrawMoney = BigDecimal.valueOf(money);
            if (withdrawMoney.compareTo( object.getMoney()) <= 0 && withdrawMoney.compareTo(BigDecimal.ZERO)>0) {
                System.out.println("Rút tiền thành công: " + withdrawMoney + " K");
                object.setMoney(object.getMoney().subtract(withdrawMoney));
                return;

            } else {
                System.out.println("So tien khong hop le, vui lòng nhập lại.");
            }
        } while (true); // Tiếp tục yêu cầu cho đến khi có đầu vào hợp lệ
    }

    //Ham hien thi danh sach cac don o gan
    public void NearbyOrderList(Shipper shipper){
        if (adminService.isLockedShipper(shipper)){
            System.out.println("Tài khoản của bạn đã bị khóa "+shipper.getLockDuration()+" ngay");
            return;
        }
        List<Order> shipperOrder = new ArrayList<>();
        for (Map.Entry<Integer,Order > entry : Database.orderMap.entrySet()) {
            if (entry.getValue().getState().getStateName().equals("Chờ lấy hàng")) {
                Saller saller = Database.sallerMap.get(entry.getValue().getIdSeller());
                double distance = Utils.calculateDistance(shipper.getLatitude(), shipper.getLongitude(), saller.getLatitude(), saller.getLongitude());
                entry.getValue().setDistance(distance);
                shipperOrder.add(entry.getValue());
            }
        }
        if (!shipperOrder.isEmpty()) {
            shipperOrder.sort(Comparator.comparingDouble(Order::getDistance));
            for (Order order : shipperOrder) {
                System.out.println("Order ID: " + order.getId() +
                        " - Shop: " + Database.sallerMap.get(order.getIdSeller()).getName() +
                        " - Mon an: " + order.getFoodName() +
                        " - Tien ship: " + order.getSumShip() +
                        " - Tien hang: " + order.getFoodBill() +
                        " - Khoảng cách: " + order.getDistance() + " km");
            }
        }else {
            System.out.println("Khong co don nao cho giao!");
        }
    }

    //Xac nhan don hang de giao
    public void orderConfirmation(Scanner scanner,Shipper shipper){
        if (adminService.isLockedShipper(shipper)){
            System.out.println("Tài khoản của bạn đã bị khóa "+shipper.getLockDuration()+" ngay");
            return;
        }
        //Kiem tra moi shipper chi duoc nhan 1 don de giao
        if (shipper.getCount()==0) {
            System.out.println("Nhap id don hang");
            int id = Utils.inputInteger(scanner);
            Order order = Database.orderMap.get(id);
            if (order != null && order.getState().getStateName().equals("Chờ lấy hàng")) {
                order.setIdShipper(shipper.getId());
                shipper.setCount(1);
                order.nextState();
                System.out.println("Đơn hàng " + order.getId() + " da dang giao.");
            } else {
                System.out.println("Khong tim thay don");
            }
        }else {
            System.out.println("Ban phai giao not don hang, moi duoc nhan don moi!");
        }
    }


    //Xac nhan giao hang thanh cong hoac huy don
    public void deliveredState(Scanner scanner, Shipper shipper){
        if (adminService.isLockedShipper(shipper)){
            System.out.println("Tài khoản của bạn đã bị khóa "+shipper.getLockDuration()+" ngay");
            return;
        }
        System.out.println("Nhap id don hang");
        int id= Utils.inputInteger(scanner);
        Order order = Database.orderMap.get(id);
        if (order!=null && order.getState().getStateName().equals("Chờ giao hàng") && order.getIdShipper()==shipper.getId()){
            System.out.println("\nBạn muốn làm gì với đơn hàng " + order.getId() + "?");
            System.out.println("1. Xác nhận đơn hàng");
            System.out.println("2. Hủy đơn hàng");
            System.out.print("Nhập lựa chọn của bạn: ");
            int choice = Utils.inputInteger(scanner);
            switch (choice) {
                case 1:
                    order.nextState();
                    System.out.println("Đơn hàng " + order.getId() + " đã  giao hang thanh cong.");
                    Database.menuMap.get(order.getIdFood()).setQuantitySold(order.getQuantity());
                    Voucher voucher= Database.voucherMap.get(order.getIdVoucher());
                    voucher.setQuantity(voucher.getQuantity()-1);
                    if (order.getTotalAmount().compareTo(BigDecimal.ZERO)==0){
                        shipper.setMoney(shipper.getMoney().add(BigDecimal.valueOf(order.getSumShip())));
                    }
                    shipper.setCount(0);
                    Database.adminList.get(1).setMoney(Database.adminList.get(1).getMoney().add(BigDecimal.valueOf(Utils.floorFee)));
                    Database.deliveredMap.put(order.getId(),order);
                    Database.orderMap.remove(order.getId());
                    break;
                case 2:
                    order.setState(new CancelledState()); // Chuyển trạng thái sang "Đã hủy"
                    System.out.println("Đơn hàng " + order.getId() + " đã bị hủy.");
                    Database.adminList.get(1).setMoney(Database.adminList.get(1).getMoney().add(BigDecimal.valueOf(Utils.floorFee)));
                    Customer customer = Database.customerMap.get(order.getIdCustomer());
                    if (order.getTotalAmount().compareTo(BigDecimal.ZERO)==0){
                        shipper.setMoney(shipper.getMoney().add(BigDecimal.valueOf(order.getSumShip())));
                    }
                    shipper.setCount(0);
                    Database.abortMap.put(order.getId(),order);
                    Database.orderMap.remove(order.getId());
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ, vui lòng nhập lại.");
                    break;
            }
        }else {
            System.out.println("Khong tim thay don");
        }
    }


    public Shipper intputShipper(Scanner scanner, User user){
        String name = getInputName(scanner);
        double longitude = getInputLongitude(scanner);
        double latitude = getInputLatitude(scanner);
        return new Shipper(user.getUseName(),user.getPassWord(),user.getEmail(),user.getRole(),name,longitude,latitude);
    }

    private String getInputName(Scanner scanner) {
        System.out.println("Nhap vao ten : ");
        return scanner.nextLine();
    }

    private double getInputLongitude(Scanner scanner) {
        System.out.println("Nhap vao kinh do: ");
        return Utils.intPutLongitude(scanner);
    }

    private double getInputLatitude(Scanner scanner) {
        System.out.println("Nhap vao vi do:");
        return Utils.intPutLatitude(scanner);
    }

}
