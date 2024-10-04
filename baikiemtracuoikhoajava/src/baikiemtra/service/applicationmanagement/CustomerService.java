package baikiemtra.service.applicationmanagement;

import baikiemtra.data.Database;
import baikiemtra.entities.applicationmanagement.*;
import baikiemtra.entities.login.User;
import baikiemtra.untils.Utils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

public class CustomerService implements GeneralInformation<Customer> {
    private static CustomerService customerService;
    private CustomerService(){

    }

    public static synchronized CustomerService getInstance(){
        if (customerService==null){
            customerService = new CustomerService();
        }
        return customerService;
    }

    SallerService sallerService =SallerService.getInstance();

    @Override
    public void insert(Customer object) {
        Database.customerMap.put(object.getId(), object);
    }

    @Override
    public void UpdateInformation(Scanner scanner, Customer object) {
        object.setName(getInputName(scanner));
        object.setLongitude(getInputLongitude(scanner));
        object.setLatitude(getInputLatitude(scanner));
        object.setPhoneNumber(getInputPhoneNumber(scanner));
        System.out.println("Doi thong tin thanh cong!");
    }
//Ham don giao thanh cong
    @Override
    public void displayDeliveredState(Customer object) {
        if ( Database.deliveredMap.isEmpty()){
            System.out.println("Ko co don hang !");
            return;
        }
        boolean hasOrders = false;
        for (Map.Entry<Integer,Order> entry : Database.deliveredMap.entrySet()){
            if (entry.getValue().getIdCustomer()==object.getId()){
                System.out.println(entry.getValue());
                hasOrders =true;
            }
        }
        if(!hasOrders) {
            System.out.println("Ban chua mua don hang nao!");
        }
    }
//Ham don hang da huy
    @Override
    public void CancelledState(Customer object) {
        if ( Database.abortMap.isEmpty()){
            System.out.println("Ko co don hang nhap!");
            return;
        }
        boolean hasOrders = false;
        for (Map.Entry<Integer,Order> entry : Database.abortMap.entrySet()){
            if (entry.getValue().getIdCustomer()==object.getId()){
                System.out.println(entry.getValue());
                hasOrders =true;
            }
        }
        if (!hasOrders){
            System.out.println("Ban chua huy don nao!");
        }
    }
//Ham rut tien
    @Override
    public void withdrawMoney(Scanner scanner, Customer object) {
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


    //Ham tim kiem theo ten
    public void searchMenu(Scanner scanner, Customer customer) {
        System.out.println("Nhập vào tên món ăn:");
        String name = scanner.nextLine();
        System.out.println("Thông tin tìm kiếm dựa trên " + name + " là:");
        boolean found = basicSearch(scanner, name, customer);
        if (found) {
            String choice ;
            searchFilter(scanner, name, customer);
        } else {
            System.out.println("Không cần tìm kiếm thêm bộ lọc vì không tìm thấy món ăn.");
        }
    }

    //Ham xem binh luan
    public void seeComments(Scanner scanner){
        if ( Database.feedbackMap.isEmpty()){
            System.out.println("Ko co don hang !");
            return;
        }
        System.out.println("Nhap id mon an ban can xem binh luan");
        int id =Utils.inputInteger(scanner);
        boolean hasOrders = false;
        for (Map.Entry<Integer,Feedback> entry : Database.feedbackMap.entrySet()){
            if (entry.getValue().getIdFood()==id){
                System.out.println(entry.getValue());
                hasOrders =true;
            }
        }
        if (!hasOrders){
            System.out.println("Don hang Chua co binh luan nao");
        }
    }

    //Ham mua hang
    public void purchase(Scanner scanner,Customer customer){
        DraftOder draftOder= productInspection(scanner,customer);
        if (draftOder!=null) {
            Saller saller = Database.sallerMap.get(draftOder.getIdSeller());
            double sumShip = sumShippingCost(draftOder, customer);
            BigDecimal foodBill = foodBill(draftOder);
            BigDecimal totalAmount = BigDecimal.valueOf(sumShip).add(foodBill).add(BigDecimal.valueOf(Utils.floorFee));
            Order order = new Order(draftOder.getIdSeller(), customer.getId(), customer.getName(), draftOder.getNameSeller(), draftOder.getMenuName(), draftOder.getQuantity(), Utils.floorFee, sumShip, foodBill, totalAmount, customer.getPhoneNumber(), draftOder.getIdMenu());

            System.out.println(order);
            Voucher voucherValue = chooseVoucher(scanner, saller);
            if (voucherValue!=null){
            double voucherValueReductionLevel= voucherValue.getReductionLevel();
                order.setFoodBill(foodBill.subtract(BigDecimal.valueOf(voucherValueReductionLevel)));
                order.setIdVoucher(voucherValue.getId());
                System.out.println("Don hang da ap dung voucher giam " + voucherValue + "k");
                System.out.println(order);
            } else {
                System.out.println("Khong co voucher, voucher khong hop le!");
            }
            PaymentType(scanner, order, customer, draftOder,voucherValue);

        }else {
            System.out.println("So luong ko hop le hoac ko co san pham");
        }
    }

    //Trang thai don hang
    public void orderState(Customer customer){
        List<Order> newOrder = new ArrayList<>();
        for (Map.Entry<Integer,Order> entry : Database.orderMap.entrySet()){
            if (customer.getId()==entry.getValue().getIdCustomer()){
                System.out.println(entry.getValue());
                newOrder.add(entry.getValue());
            }
        }
        if (newOrder.isEmpty()){
            System.out.println("Khong co don hang nao");
        }
    }

    //Danh sach don nhap
    public void displayDraftOrder(Customer customer){
        if ( Database.draftOrderMap.isEmpty()){
            System.out.println("Ko co don hang nhap!");
            return;
        }
        boolean hasOrders = false;
            for (Map.Entry<Integer,DraftOder> entry : Database.draftOrderMap.entrySet()){
                if (entry.getValue().getIdCustomer()==customer.getId()){
                    System.out.println(entry.getValue());
                    hasOrders =true;
                }
            }
        if(!hasOrders){
            System.out.println("Khong co don nhap nao nao!");
        }
    }


    //Mua hang trong don nhap
    public void buyDraftOrder( Scanner scanner,Customer customer){
        System.out.println("Nhap id don can mua: ");
        int id = Utils.inputInteger(scanner);
        DraftOder draftOder = Database.draftOrderMap.get(id);
        if (draftOder!=null){
            Saller saller = Database.sallerMap.get(draftOder.getIdSeller());
            double sumShip = sumShippingCost(draftOder, customer);
            BigDecimal foodBill = foodBill(draftOder);
            BigDecimal totalAmount = BigDecimal.valueOf(sumShip).add(foodBill).add(BigDecimal.valueOf(Utils.floorFee));
            Order order = new Order(draftOder.getIdSeller(), customer.getId(), customer.getName(), draftOder.getNameSeller(), draftOder.getMenuName(), draftOder.getQuantity(), Utils.floorFee, sumShip, foodBill, totalAmount, customer.getPhoneNumber(), draftOder.getIdMenu());
            System.out.println(order);
            Voucher voucherValue = chooseVoucher(scanner, saller);
            if (voucherValue!=null){
            double voucherValueReductionLevel= voucherValue.getReductionLevel();
                order.setFoodBill(foodBill.subtract(BigDecimal.valueOf(voucherValueReductionLevel)));
                System.out.println("Don hang da ap dung voucher giam " + voucherValue + "k");
                order.setIdVoucher(voucherValue.getId());
                System.out.println(order);
            } else {
                System.out.println("Khong co voucher, voucher khong hop le!");
            }
            PaymentType(scanner, order, customer, draftOder,voucherValue);
        }else {
            System.out.println("So luong ko hop le hoac ko co san pham");
        }
    }


    //Danh gia don hang
    public void feedback(Customer customer, Scanner scanner){
        if ( Database.deliveredMap.isEmpty()){
            System.out.println("Ko co don hang !");
            return;

        }
        Map<Integer,Order> notYetRated =new HashMap<>();
        boolean hasOrders = false;
        System.out.println("Don hang chua danh gia");
        for (Map.Entry<Integer,Order> entry : Database.deliveredMap.entrySet()){
            if (entry.getValue().getIdCustomer()==customer.getId()&& Database.singleReviewMap.get(entry.getValue().getId())==null){
                notYetRated.put(entry.getValue().getId(),entry.getValue());
                System.out.println(notYetRated.get(entry.getValue().getId()));
                hasOrders =true;
            }
        }
        if(!hasOrders) {
            System.out.println("khong co don nao de danh gia!");
            return;
        }
        System.out.println("Nhap vao id don hang can danh gia");
        int id = Utils.inputInteger(scanner);
        Order order = notYetRated.get(id);
        if (order!=null){
            Review review = chooseStars(scanner);
            System.out.println("Nhap noi dung danh gia");
            String feedback = scanner.nextLine();
                Feedback feedback1 = new Feedback(customer.getId(),order.getIdFood(),customer.getName(),review,feedback,order.getIdSeller());
                Database.feedbackMap.put(feedback1.getId(),feedback1);
            Database.singleReviewMap.put(order.getId(), order);
        }

    }


    //Ham nap tien
    public void recharge(Scanner scanner, Customer customer){
        System.out.println("Nhap so tien ban muon nap: ");
        String input = scanner.nextLine();
        BigDecimal recharge = new BigDecimal(input);
        RechargePurchases(scanner,customer,recharge);
    }

    //Ham huy don hang
    public void cancelOrder(Scanner scanner, Customer customer){
        if (displayAwaitingPickupState(customer)){
            System.out.println("Nhap id don hang de huy");
            int id = Utils.inputInteger(scanner);
            if (Database.orderMap.get(id) != null) {
                Order order = Database.orderMap.get(id);
                order.setState(new  CancelledState());
                System.out.println("Đơn hàng " + order.getId() + " đã bị hủy.");
                Database.abortMap.put(order.getId(),order);
                Database.orderMap.remove(order.getId());
            } else {
                System.out.println("Không tìm thấy đơn hàng với mã " + id);
            }
        }else {
            System.out.println("Khong co don nao co the huy duoc!");
        }
    }


    //Kiem tra xem nhung don nao dang cho lay
    private boolean displayAwaitingPickupState(Customer customer) {

        List<Order> newOrder = new ArrayList<>();
        for (Map.Entry<Integer, Order> entry : Database.orderMap.entrySet()) {
            if (customer.getId() == entry.getValue().getIdSeller() && entry.getValue().getState().getStateName().equals("Chờ lấy hàng")) {
                System.out.println(entry.getValue());
                newOrder.add(entry.getValue());
            }
        }

        if (!newOrder.isEmpty()) {
            return  true;
        }
        return false;
    }



    private void RechargePurchases(Scanner scanner,Customer customer, BigDecimal recharge){
        customer.setMoney(recharge);
    }

    //Ham chon so sao de danh gia
    private Review chooseStars(Scanner scanner) {
        Review review = null; // Khởi tạo review với giá trị null
        int choice;

        do {
            System.out.println("Ban hay chon sao de danh gia: ");
            System.out.println("1 - Mot sao");
            System.out.println("2 - Hai sao");
            System.out.println("3 - Ba sao");
            System.out.println("4 - Bon sao");
            System.out.println("5 - Nam sao");

            choice = Utils.inputInteger(scanner); // Nhập giá trị từ người dùng

            switch (choice) {
                case 1:
                    review = Review.ONESTARS;
                    break;
                case 2:
                    review = Review.TWOSTARS;
                    break;
                case 3:
                    review = Review.THREESTARS;
                    break;
                case 4:
                    review = Review.FOURSTARS;
                    break;
                case 5:
                    review = Review.FIVESTARS;
                    break;
                default:
                    System.out.println("Lua chon khong hop le, vui long chon lai.");
                    break;
            }
        } while (review == null);

        return review;
    }

    //Ham chon voucher
    private Voucher chooseVoucher(Scanner scanner, Saller saller) {
        sallerService.selectVoucher(saller);
        int count = 0;
        for (Map.Entry<Integer,Voucher> entry : Database.voucherMap.entrySet()){
            if (entry.getValue().getIdSaller()==saller.getId()){
                count++;
            }
        }
        if (count>0) {
            System.out.println("Moi ban nhap vao id voucher muon chon: ");
            int id = Utils.inputInteger(scanner);
            Voucher voucher = Database.voucherMap.get(id);
            // Kiểm tra voucher có tồn tại, thuộc về seller và nằm trong thời gian hợp lệ
            if (voucher != null && voucher.getIdSaller() == saller.getId()) {
                // Lấy thời gian hiện tại
                LocalDateTime currentTime = LocalDateTime.now();
                // Kiểm tra thời gian hiện tại nằm trong khoảng từ startTime đến endTime
                if (currentTime.isAfter(voucher.getStartTime()) && currentTime.isBefore(voucher.getEndTime())) {
                    return voucher;
                } else {
                    System.out.println("Voucher không hợp lệ trong thời gian hiện tại.");
                }
            } else {
                System.out.println("Voucher không hợp lệ hoặc không tìm thấy.");
            }
        }else {
            System.out.println("Khong co voucher");
        }

        return null;
    }


    //Ham chon kieu thanh toan
    private void PaymentType(Scanner scanner, Order order,Customer customer,DraftOder draftOder,Voucher voucher){
        System.out.println("Moi ban cho kieu thanh toan!");
        System.out.println("1 - Thanh toan bang tien mat:" );
        System.out.println("2 - Thanh toan bang chuyen khoan");
        int choice = Utils.inputInteger(scanner);
        switch (choice){
            case 1:
                Database.orderMap.put(order.getId(),order);
                Database.draftOrderMap.remove(draftOder.getId());
                break;
            case 2:
                if (order.getTotalAmount().compareTo(customer.getMoney()) > 0){
                    System.out.println("Tai khoan khong du ban can nap them"+order.getTotalAmount().subtract(customer.getMoney())+"k");
                    RechargePurchases(scanner,customer,order.getTotalAmount().subtract(customer.getMoney()));
                    customer.setMoney(customer.getMoney().subtract(order.getTotalAmount()));
                    System.out.println("Ban da thanh toan thanh cong don hang");
                    order.nextState();

                    if (voucher!=null) {
                        voucher.setQuantity(voucher.getQuantity() - 1);
                    }
                    Saller saller =Database.sallerMap.get(order.getIdSeller());
                    saller.setMoney(saller.getMoney().add(order.getFoodBill()));
                    order.setTotalAmount(BigDecimal.ZERO);
                    Database.orderMap.put(order.getId(),order);
                    Database.draftOrderMap.remove(draftOder.getId());
                }else {
                    customer.setMoney(customer.getMoney().subtract(order.getTotalAmount()));
                    System.out.println("Ban da thanh toan thanh cong don hang");
                    voucher.setQuantity(voucher.getQuantity()-1);
                    order.setTotalAmount(BigDecimal.ZERO);
                    Database.orderMap.put(order.getId(),order);
                    Database.draftOrderMap.remove(draftOder.getId());
                }
                break;
        }
    }



    //Ham tinh tien ship
    private double sumShippingCost(DraftOder draftOder,Customer customer){
        Saller saller = Database.sallerMap.get(draftOder.getIdSeller());
        return Utils.ship*Utils.calculateDistance(customer.getLatitude(),customer.getLongitude(),saller.getLatitude(),saller.getLongitude());
    }

    //Ham tinh tong tien hang
    private BigDecimal foodBill(DraftOder draftOder) {
        Food menu = Database.menuMap.get(draftOder.getIdMenu());
        BigDecimal price = BigDecimal.valueOf(menu.getPrice());
        BigDecimal quantity = BigDecimal.valueOf(draftOder.getQuantity());
        return price.multiply(quantity);
    }



    //Kiem tra san pham va them vao don nhap
    private DraftOder productInspection(Scanner scanner ,Customer customer){
        System.out.println("Nhap id san pham: ");
        int idMenu = Utils.inputInteger(scanner);
        System.out.println("Nhap so luong:");
        int quantity = Utils.inputInteger(scanner);
        Food menu = Database.menuMap.get(idMenu);
        if (menu!=null ){
            Saller saller = Database.sallerMap.get(menu.getIdSaller());
            DraftOder draftOder =new DraftOder(saller.getName(),menu.getName(),customer.getId(),quantity,saller.getId(),idMenu);
            Database.draftOrderMap.put(draftOder.getId(),draftOder);
            return draftOder;
        }else {
            System.out.println("Khong tim thay san pham");
        }
        return  null;
    }

    //Bo loc
    private void searchFilter(Scanner scanner, String name,Customer customer){
        System.out.println("Ban tim do an theo: ");
        System.out.println("1 - Gan nhat");
        System.out.println("2 - Ban chay ");
        System.out.println("3 - Danh gia cao ");
        int choice = Utils.inputInteger(scanner);
        switch (choice){
            case 1:
                nearestSearch(scanner,name, customer);
                break;
            case 2:

                break;
            case 3:

                break;
            default:
                System.out.println("Lua ch on khong hop le");
        }
    }

    //ham tim kiem theo ten
    private boolean basicSearch(Scanner scanner, String dishName, Customer customer) {
        if (Database.menuMap.isEmpty()) {
            System.out.println("Không có dữ liệu món ăn.");
            return false;
        }

        boolean found = false;  // Biến kiểm tra xem có tìm thấy món ăn hay không
        for (Map.Entry<Integer, Food> entry : Database.menuMap.entrySet()) {
            if (entry.getValue().getName().toLowerCase().contains(dishName.toLowerCase())) {
                Saller saller = Database.sallerMap.get(entry.getValue().getIdSaller());
                double distance = Utils.calculateDistance(customer.getLatitude(), customer.getLongitude(),
                        saller.getLatitude(), saller.getLongitude());
                System.out.println("Saller ID: " + saller.getId() +
                        " - Shop: " + saller.getName() +
                        " - Menu ID: " + entry.getValue().getId() +
                        " - Món ăn: " + entry.getValue().getName() +
                        " - Số lượng: " + entry.getValue().getQuantitySold() +
                        " - Giá: " + entry.getValue().getPrice() +
                        " - Danh gia: " + entry.getValue().getRateStars() +
                        " - Khoảng cách: " + distance + " km");
                found = true;

            }
        }
        if (!found) {
            System.out.println("Không tìm thấy món ăn nào.");
        }

        return found;
    }


    //Ham tim kiem mon an o gan
    private void nearestSearch(Scanner scanner, String dishName, Customer customer) {
        List<Saller> sallers = new ArrayList<>();

        // Lọc người bán có món ăn tương ứng với dishName
        for (Food menu : Database.menuMap.values()) {
            if (menu.getName().toLowerCase().contains(dishName.toLowerCase())) {
                Saller saller = Database.sallerMap.get(menu.getIdSaller());
                if (!sallers.contains(saller)) {
                    sallers.add(saller); // Thêm người bán nếu chưa tồn tại trong danh sách
                }
            }
        }
        // Sắp xếp theo khoảng cách
        sallers.sort(Comparator.comparingDouble(saller ->
                Utils.calculateDistance(customer.getLatitude(), customer.getLongitude(), saller.getLatitude(), saller.getLongitude()))
        );

        // Hiển thị thông tin
        for (Saller saller : sallers) {
            for (Food menu : Database.menuMap.values()) {
                if (menu.getIdSaller() == saller.getId() && menu.getName().toLowerCase().contains(dishName.toLowerCase())) {
                    double distance = Utils.calculateDistance(customer.getLatitude(), customer.getLongitude(), saller.getLatitude(), saller.getLongitude());
                    System.out.println("Saller ID: " + saller.getId() +
                            " - Shop: " + saller.getName() +
                            " - Menu ID: " + menu.getId() +
                            " - Món ăn: " + menu.getName() +
                            " - Số lượng: " + menu.getQuantitySold() +
                            " - Giá: " + menu.getPrice() +
                            " - Khoảng cách: " + distance + " km");
                }
            }
        }
    }



    //Ham nhap du lieu cho customer
    public Customer inputCustomer(Scanner scanner, User user) {
        String name = getInputName(scanner);
        double longitude = getInputLongitude(scanner);
        double latitude = getInputLatitude(scanner);
        String phoneNumber = getInputPhoneNumber(scanner);

        return new Customer(user.getUseName(), user.getPassWord(), user.getEmail(), user.getRole(), name, longitude, latitude, phoneNumber);
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

    private String getInputPhoneNumber(Scanner scanner) {
        System.out.println("Nhap vao sdt:");
        return scanner.nextLine();
    }

}
