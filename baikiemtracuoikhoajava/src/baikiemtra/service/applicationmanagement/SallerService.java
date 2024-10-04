package baikiemtra.service.applicationmanagement;

import baikiemtra.data.Database;
import baikiemtra.entities.applicationmanagement.*;
import baikiemtra.entities.login.User;
import baikiemtra.untils.Utils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

public class SallerService implements GeneralInformation<Saller> {
    private static SallerService sallerServicec;

    private SallerService() {

    }

    public static synchronized SallerService getInstance() {
        if (sallerServicec == null) {
            sallerServicec = new SallerService();
        }
        return sallerServicec;
    }

    AdminService adminService = AdminService.getInstance();

    //Nhap thong tin quan
    @Override
    public void insert(Saller object) {
        Database.sallerMap.put(object.getId(), object);
    }

    //Ham update lai thong tin quan
    @Override
    public void UpdateInformation(Scanner scanner, Saller object) {

        object.setName(getInputName(scanner));
        object.setLongitude(getInputLongitude(scanner));
        object.setLatitude(getInputLatitude(scanner));
    }

    //Ham xem don giao thanh cong
    @Override
    public void displayDeliveredState(Saller object) {
        if (adminService.isLocked(object)){
            System.out.println("Tài khoản của bạn đã bị khóa "+object.getLockDuration()+" ngay");
            return;
        }
        if (adminService.isLocked(object)) {
            System.out.println("Tài khoản của bạn đã bị khóa " + object.getLockDuration() + " ngay");
            return;
        }
        boolean hasOrders = false;
        for (Map.Entry<Integer, Order> entry : Database.deliveredMap.entrySet()) {
            if (object.getId() == entry.getValue().getIdSeller()) {
                System.out.println(entry.getValue());
                hasOrders = true;
            }
        }
        if (!hasOrders) {
            System.out.println("Không có đơn nào.");
        }
    }

    //Ham xem don da huy
    @Override
    public void CancelledState(Saller object) {
        if (adminService.isLocked(object)){
            System.out.println("Tài khoản của bạn đã bị khóa "+object.getLockDuration()+" ngay");
            return;
        }

        if (adminService.isLocked(object)) {
            System.out.println("Tài khoản của bạn đã bị khóa " + object.getLockDuration() + " ngay");
            return;
        }
        if (Database.abortMap.isEmpty()) {
            System.out.println("Không có đơn nào da huy");
            return;
        }
        boolean hasOrders = false;
        for (Map.Entry<Integer, Order> entry : Database.abortMap.entrySet()) {
            if (object.getId() == entry.getValue().getIdSeller()) {
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
    public void withdrawMoney(Scanner scanner, Saller object) {

        if (object.getMoney().compareTo(BigDecimal.ZERO) == 0) {
            System.out.println("Tai khoan ban 0 co tien ");
            return;
        }
        System.out.println("Số tiền bạn đang có: " + object.getMoney() + " K");
        double money;

        do {
            System.out.println("Nhập số tiền bạn muốn rút: ");
            money = Utils.inputDouble(scanner);

            BigDecimal withdrawMoney = BigDecimal.valueOf(money);
            if (withdrawMoney.compareTo(object.getMoney()) <= 0 && withdrawMoney.compareTo(BigDecimal.ZERO) > 0) {
                System.out.println("Rút tiền thành công: " + withdrawMoney + " K");
                object.setMoney(object.getMoney().subtract(withdrawMoney));
                return;

            } else {
                System.out.println("So tien khong hop le, vui lòng nhập lại.");
            }
        } while (true); // Tiếp tục yêu cầu cho đến khi có đầu vào hợp lệ
    }


    //Ham them mon an
    public void addFood(Scanner scanner,Saller saller){
        if (adminService.isLocked(saller)){
            System.out.println("Tài khoản của bạn đã bị khóa "+saller.getLockDuration()+" ngay");
            return;
        }
        TypeOfFood typeOfFood = selectFoodType(scanner);
        String name = inputFoodName(scanner);
        int quantity = inputQuantity(scanner);
        double price = inputPrice(scanner);
        int idSaller = saller.getId();
        Food menu = new Food(name,typeOfFood,price,idSaller);
        Database.menuMap.put(menu.getId(),menu);
    }

    //ham xoa mon an
    public void deleteFood(Scanner scanner, Saller saller){
        if (adminService.isLocked(saller)){
            System.out.println("Tài khoản của bạn đã bị khóa "+saller.getLockDuration()+" ngay");
            return;
        }
        System.out.println("Nhap id mon: ");
        int id = Utils.inputInteger(scanner);
        Food menu = Database.menuMap.get(id);
        if(menu!=null && menu.getIdSaller()==saller.getId()){
            Database.menuMap.remove(id);
        }else {
            System.out.println("Khong tim thay mon an");
        }
    }

    //Ham chinh sua thong tin mon an
    public void updateFood(Scanner scanner, Saller saller){
        if (adminService.isLocked(saller)){
            System.out.println("Tài khoản của bạn đã bị khóa "+saller.getLockDuration()+" ngay");
            return;
        }
        System.out.println("Nhap id mon: ");
        int id = Utils.inputInteger(scanner);
        Food menu = Database.menuMap.get(id);
        if (menu!=null && menu.getIdSaller()==saller.getId()){
            menu.setName(getInputName(scanner));
            menu.setPrice(inputPrice(scanner));
        }
    }

    //Ham hien thi danh sach mon an
    public void selectFood(Saller saller) {
        if (adminService.isLocked(saller)){
            System.out.println("Tài khoản của bạn đã bị khóa "+saller.getLockDuration()+" ngay");
            return;
        }
        if (Database.menuMap.isEmpty()) {
            System.out.println("Không có món ăn nào trong menu");
            return;
        }
        boolean found = false;
        for (Map.Entry<Integer, Food> entry : Database.menuMap.entrySet()) {
            if (entry.getValue().getIdSaller() == saller.getId()) {
                System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
                found = true; // Có ít nhất một món khớp với idSaller
            }
        }
        if (!found) {
            System.out.println("Không có món nào trong menu của bạn!");
        }
    }


    //Ham them voucher
    public void insertVoucher(Scanner scanner,Saller saller){
        if (adminService.isLocked(saller)){
            System.out.println("Tài khoản của bạn đã bị khóa "+saller.getLockDuration()+" ngay");
            return;
        }
        double reductionLevel = inputPrice(scanner);
        int quantity = inputQuantity(scanner);
        LocalDateTime staterTime = Utils.inputLocalDateTime(scanner);
        LocalDateTime endTime = Utils.inputLocalDateTime(scanner);
        Voucher voucher = new Voucher(saller.getId(),reductionLevel,quantity,staterTime,endTime);
        Database.voucherMap.put(voucher.getId(),voucher);
    }

    //Ham hien thi danh sach voucher
    public void selectVoucher(Saller saller) {
        if (adminService.isLocked(saller)){
            System.out.println("Tài khoản của bạn đã bị khóa "+saller.getLockDuration()+" ngay");
            return;
        }
        if (Database.voucherMap.isEmpty()) {
            System.out.println("Không có voucher");
            return;
        }

        boolean found = false;
        LocalDateTime currentTime = LocalDateTime.now(); // Lấy thời gian hiện tại
        // Dùng iterator để có thể xóa phần tử trong quá trình duyệt
        Iterator<Map.Entry<Integer, Voucher>> iterator = Database.voucherMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Voucher> entry = iterator.next();
            Voucher voucher = entry.getValue();

            // Kiểm tra idSaller, số lượng và thời gian hết hạn
            if (voucher.getIdSaller() == saller.getId() && voucher.getQuantity() > 0 && voucher.getEndTime().isAfter(currentTime)) {
                System.out.println( " Voucher: " + voucher);
                found = true;
            } else {
                // Xóa voucher nếu hết hạn hoặc số lượng <= 0
                if (voucher.getQuantity() <= 0 || voucher.getEndTime().isBefore(currentTime)) {
                    iterator.remove();
                }
            }
        }

        if (!found) {
            System.out.println("Không có voucher hợp lệ");
        }
    }


        // Phương thức chính để xác nhận hoặc hủy đơn hàng
        public void orderConfirmation(Scanner scanner, Saller saller) {
            if (adminService.isLocked(saller)){
                System.out.println("Tài khoản của bạn đã bị khóa "+saller.getLockDuration()+" ngay");
                return;
            }
            if (Database.orderMap.isEmpty()) {
                System.out.println("Không có đơn nào chờ duyệt");
                return;
            }
            // Hiển thị danh sách đơn hàng của người bán
                if (displayOrdersForSeller(saller)) {
                    String choice;
                    do {
                        System.out.print("Nhập mã đơn hàng: ");
                        int id = Utils.inputInteger(scanner);
                        processOrderDecision(scanner, id);
                        if (!displayOrdersForSeller(saller)){
                            return;
                        }
                        System.out.println("Chon Y de tiep tuc, N de thoat ra");
                        choice =scanner.nextLine();
                    }while (choice.equalsIgnoreCase("Y"));

                }else {
                    System.out.println("Không có đơn nào thuộc người bán này.");
                }
        }

        // Hiển thị danh sách đơn hàng cho duyet thuộc người bán
        private boolean displayOrdersForSeller(Saller saller) {
            List<Order> newOrder = new ArrayList<>();
            for (Map.Entry<Integer, Order> entry : Database.orderMap.entrySet()) {
                if (saller.getId() == entry.getValue().getIdSeller() && entry.getValue().getState().getStateName().equals("Chờ xác nhận")) {
                    System.out.println(entry.getValue());
                    newOrder.add(entry.getValue());
                }
            }

            if (!newOrder.isEmpty()) {
                return  true;
            }
            return false;
        }

        //Xem don hang cho lay
    public void displayAwaitingPickupState(Saller saller) {
        if (adminService.isLocked(saller)){
            System.out.println("Tài khoản của bạn đã bị khóa "+saller.getLockDuration()+" ngay");
            return;
        }
        if (Database.orderMap.isEmpty()) {
            System.out.println("Không có đơn nào chờ lay");
            return;
        }
        boolean hasOrders = false;
        for (Map.Entry<Integer, Order> entry : Database.orderMap.entrySet()) {
            if (saller.getId() == entry.getValue().getIdSeller() && entry.getValue().getState().getStateName().equals("Chờ lấy hàng")) {
                System.out.println(entry.getValue());
                hasOrders = true;
            }
        }
        if (!hasOrders) {
            System.out.println("Không có đơn nào.");
        }
    }

    //Xem danh sach don cho giao
    public void displayAwaitingDeliveryState(Saller saller) {
        if (adminService.isLocked(saller)){
            System.out.println("Tài khoản của bạn đã bị khóa "+saller.getLockDuration()+" ngay");
            return;
        }
        if (Database.orderMap.isEmpty()) {
            System.out.println("Không có đơn nào chờ giao");
            return;
        }
        boolean hasOrders = false;
        for (Map.Entry<Integer, Order> entry : Database.orderMap.entrySet()) {
            if (saller.getId() == entry.getValue().getIdSeller() && entry.getValue().getState().getStateName().equals("Chờ giao hàng")) {
                System.out.println(entry.getValue());
                hasOrders = true;
            }
        }
        if (!hasOrders) {
            System.out.println("Không có đơn nào.");
        }
    }


    //Xem danh gia don hang cua shop
    public void SeeOrderReviews(Saller saller){
        if (adminService.isLocked(saller)){
            System.out.println("Tài khoản của bạn đã bị khóa "+saller.getLockDuration()+" ngay");
            return;
        }
        if (Database.feedbackMap.isEmpty()) {
            System.out.println("Không có danh gia nao cho shop");
            return;
        }
        boolean hasOrders = false;
        for (Map.Entry<Integer, Feedback> entry : Database.feedbackMap.entrySet()) {
            if (saller.getId() == entry.getValue().getIdSeller()) {
                System.out.println(entry.getValue());
                hasOrders = true;
            }
        }
        if (!hasOrders) {
            System.out.println("Không có đanh gia nao!.");
        }

    }


        // Xử lý quyết định của người bán cho đơn hàng (xác nhận hoặc hủy)
        private void processOrderDecision(Scanner scanner, int orderId) {
        boolean check = false;

        do {
            if (Database.orderMap.get(orderId) != null) {
                Order order = Database.orderMap.get(orderId);
                    handleOrderChoice(scanner, order);
                check=true;

            } else {
                System.out.println("Không tìm thấy đơn hàng với mã " + orderId + ". Vui lòng nhập lại.");
            }
        }while (!check);

        }

    // Xử lý lựa chọn xác nhận hoặc hủy đơn hàng
        private void handleOrderChoice(Scanner scanner, Order order) {
                // Hiển thị menu lựa chọn
                System.out.println("\nBạn muốn làm gì với đơn hàng " + order.getId() + "?");
                System.out.println("1. Xác nhận đơn hàng");
                System.out.println("2. Hủy đơn hàng");
                System.out.print("Nhập lựa chọn của bạn: ");
                int choice = Utils.inputInteger(scanner);
                switch (choice) {
                    case 1:
                        order.nextState(); // Chuyển trạng thái sang "Chờ lấy hàng"
                        System.out.println("Đơn hàng " + order.getId() + " đã được duyệt.");
                        break;
                    case 2:
                        order.setState(new CancelledState()); // Chuyển trạng thái sang "Đã hủy"
                        System.out.println("Đơn hàng " + order.getId() + " đã bị hủy.");
                        Database.abortMap.put(order.getId(),order);
                        Database.orderMap.remove(order.getId());
                        break;
                    default:
                        System.out.println("Lựa chọn không hợp lệ, vui lòng nhập lại.");
                        break;
                }
        }

    public Saller intputSaller(Scanner scanner, User user){
       String name = getInputName(scanner);
        double longitude = getInputLongitude(scanner);
        double latitude = getInputLatitude(scanner);
        return new Saller(user.getUseName(),user.getPassWord(),user.getEmail(),user.getRole(),name,longitude,latitude,true);
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

    //Ham nhap ten
    private String inputFoodName(Scanner scanner) {
        System.out.println("Ten thuc pham:");
        return scanner.nextLine();
    }

    // Hàm nhập số lượng
    private int inputQuantity(Scanner scanner) {
        int quantity;
        do {
            System.out.println("Nhap so luong: ");
            quantity = Utils.inputInteger(scanner);
            if (quantity > 0) {
                break;
            } else {
                System.out.println("So luong phai lon hon 0");
            }
        } while (true);
        return quantity;
    }
    //Ham nhap gia
    private double inputPrice(Scanner scanner){
        double price;
        do {
            System.out.println("Moi ban nhap so tien");
            price =Utils.inputDouble(scanner);
            if(price>0){
                return price;
            }else {
                System.out.println("So tien phai lon hon 0");
            }
        }while (true);

    }

    // Hàm chọn loại món ăn
    private TypeOfFood selectFoodType(Scanner scanner) {
        System.out.println("Moi ban chon kieu mon:");
        System.out.println("1 - Do an");
        System.out.println("2 - Do uong");

        int choose = Utils.inputInteger(scanner);
        switch (choose) {
            case 1:
                return TypeOfFood.BEVERAGE;
            case 2:
                return TypeOfFood.DRINKS;
            default:
                System.out.println("Lua chon khong hop le!");
                return null;
        }
    }









}
