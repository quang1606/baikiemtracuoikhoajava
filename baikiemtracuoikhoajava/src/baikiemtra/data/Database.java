package baikiemtra.data;
import baikiemtra.entities.applicationmanagement.*;
import baikiemtra.entities.login.User;
import java.time.LocalDateTime;
import java.util.*;

public class Database {
  public static final Map<Integer,Customer> customerMap = new HashMap<>();
    public static final Map<Integer, Saller> sallerMap =new HashMap<>();
    public static final Map<Integer, Shipper> shipperMap =new HashMap<>();
    public static final Map<String, User> userMap = new HashMap<>();
    public static final Map<Integer, Food> menuMap = new HashMap<>();
    public static final Map<Integer, Voucher> voucherMap = new HashMap<>();
 public static final Map<Integer,DraftOder> draftOrderMap = new HashMap<>();
 public static  final  Map<Integer,Order> orderMap =new HashMap<>();
 public static final Map<Integer,Order> abortMap =new HashMap<>();
 public static final Map<Integer,Order> deliveredMap = new HashMap<>();
  public static final Map<Integer,Order> singleReviewMap = new HashMap<>();
 public static final  Map<Integer,Feedback > feedbackMap = new HashMap<>();
public static final Map<Integer,Saller> accountLockSeller = new HashMap<>();
 public static  final Map<Integer,Admin> adminList = new HashMap<>();

  static {
    adminList.put(1,new Admin("admin", "admin@123", "ADMIN",Role.ADMIN,"Quang"));
    userMap.put("admin",adminList.get(1));
// Thêm dữ liệu vào sallerMap và đồng thời thêm vào userMap
    sallerMap.put(2, new Saller("saller1", "Password1@", "saller1@gmail.com", Role.SALLER, "Shop A", 105.80, 21.00, true));
    userMap.put("saller1", sallerMap.get(2));
    sallerMap.put(3, new Saller("saller2", "Password2@", "saller2@gmail.com", Role.SALLER, "Shop B", 105.90, 21.10, true));
    userMap.put("saller2", sallerMap.get(3));
    sallerMap.put(4, new Saller("saller3", "Password3@", "saller3@gmail.com", Role.SALLER, "Shop C", 105.95, 21.25, true));
    userMap.put("saller3", sallerMap.get(4));
    sallerMap.put(5, new Saller("saller4", "Password4@", "saller4@gmail.com", Role.SALLER, "Shop D", 106.00, 21.20, true));
    userMap.put("saller4", sallerMap.get(5));
    sallerMap.put(6, new Saller("saller5", "Password5@", "saller5@gmail.com", Role.SALLER, "Shop E", 106.03, 21.30, true));
    userMap.put("saller5", sallerMap.get(6));
//Them du lieu khach
    customerMap.put(7,new Customer("customer1", "Password1@", "customer1@gmail.com", Role.CUSTOMER, "John Doe", 105.85, 21.15, "0901234567"));
    customerMap.put(8, new Customer("customer2", "Password2@", "customer2@gmail.com", Role.CUSTOMER, "Jane Smith", 106.00, 21.30, "0987654321"));
    userMap.put("customer1",customerMap.get(7));
    userMap.put("customer2",customerMap.get(8));

    // Thêm dữ liệu vào voucherMap
    voucherMap.put(1, new Voucher(1, 10000, 100, LocalDateTime.of(2024, 9, 1, 10, 0, 0), LocalDateTime.of(2024, 10, 1, 10, 0, 0)));
    voucherMap.put(2, new Voucher(2, 1500, 50, LocalDateTime.of(2024, 9, 2, 11, 0, 0), LocalDateTime.of(2024, 10, 12, 11, 0, 0)));
    voucherMap.put(3, new Voucher(3, 2000, 30, LocalDateTime.of(2024, 9, 3, 12, 0, 0), LocalDateTime.of(2024, 10, 13, 12, 0, 0)));
    voucherMap.put(4, new Voucher(4, 2500, 200, LocalDateTime.of(2024, 10, 4, 13, 0, 0), LocalDateTime.of(2024, 10, 1, 13, 0, 0)));
    voucherMap.put(5, new Voucher(5, 5000, 150, LocalDateTime.of(2024, 9, 5, 14, 0, 0), LocalDateTime.of(2024, 10, 15, 14, 0, 0)));
    voucherMap.put(6, new Voucher(1, 3000, 70, LocalDateTime.of(2024, 9, 6, 15, 0, 0), LocalDateTime.of(2024, 10, 16, 15, 0, 0)));
    voucherMap.put(7, new Voucher(2, 3500, 40, LocalDateTime.of(2024, 9, 7, 16, 0, 0), LocalDateTime.of(2024, 10, 17, 16, 0, 0)));
    voucherMap.put(8, new Voucher(3, 5000, 25, LocalDateTime.of(2024, 10, 8, 17, 0, 0), LocalDateTime.of(2024, 10, 18, 17, 0, 0)));
    voucherMap.put(9, new Voucher(4, 4500, 80, LocalDateTime.of(2024, 9, 9, 18, 0, 0), LocalDateTime.of(2024, 10, 19, 18, 0, 0)));
    voucherMap.put(10, new Voucher(5, 4000, 120, LocalDateTime.of(2024, 9, 10, 19, 0, 0), LocalDateTime.of(2024, 10, 20, 19, 0, 0)));


    // Thêm dữ liệu vào shipperMap
    Database.shipperMap.put(8, new Shipper("shipper1", "Password1@", "shipper1@gmail.com", Role.SHIPPER, "Shipper A", 105.85, 21.15));
    userMap.put("shipper1",shipperMap.get(8));
    Database.shipperMap.put(9, new Shipper("shipper2", "Password2@", "shipper2@gmail.com", Role.SHIPPER, "Shipper B", 106.00, 21.25));
    userMap.put("shipper2",shipperMap.get(9));
    //Them du lieu menu

    menuMap.put(1, new Food("Coffee", TypeOfFood.BEVERAGE, 25000, 2));
    Database.menuMap.put(2, new Food("Tea", TypeOfFood.BEVERAGE, 200000, 2));
    Database.menuMap.put(3, new Food("Orange Juice", TypeOfFood.BEVERAGE, 30000, 3));
    Database.menuMap.put(4, new Food("Lemonade",TypeOfFood.BEVERAGE, 28000, 3));
    Database.menuMap.put(5, new Food("Smoothie",TypeOfFood.BEVERAGE, 35000, 4));
    Database.menuMap.put(6, new Food("Water", TypeOfFood.DRINKS, 10000, 4));
    Database.menuMap.put(7, new Food("Milkshake", TypeOfFood.DRINKS, 40000, 5));
    Database.menuMap.put(8, new Food("Iced Tea", TypeOfFood.DRINKS, 22000, 5));
    Database.menuMap.put(9, new Food("Coke", TypeOfFood.DRINKS, 15000, 6));
    Database.menuMap.put(10, new Food("Sprite", TypeOfFood.DRINKS, 15000, 6));
    Database.menuMap.put(11, new Food("Cappuccino", TypeOfFood.BEVERAGE, 45000, 2));
    Database.menuMap.put(12, new Food("Latte", TypeOfFood.BEVERAGE, 50000, 2));
    Database.menuMap.put(13, new Food("Green Tea",TypeOfFood.BEVERAGE, 20000, 3));
    Database.menuMap.put(14, new Food("Peach Tea", TypeOfFood.BEVERAGE, 25000, 3));
    Database.menuMap.put(15, new Food("Ginger Ale", TypeOfFood.DRINKS, 35000, 4));
    Database.menuMap.put(16, new Food("Soda", TypeOfFood.DRINKS, 10000, 4));
    Database.menuMap.put(17, new Food("Energy Drink", TypeOfFood.DRINKS, 40000, 5));
    Database.menuMap.put(18, new Food("Cold Brew", TypeOfFood.BEVERAGE, 60000, 5));
    Database.menuMap.put(19, new Food("Hot Chocolate", TypeOfFood.BEVERAGE, 55000, 5));
    Database.menuMap.put(20, new Food("Fruit Punch", TypeOfFood.DRINKS, 35000, 6));
    Database.menuMap.put(21, new Food("Espresso", TypeOfFood.BEVERAGE, 35000, 2));
    Database.menuMap.put(22, new Food("Green Tea", TypeOfFood.BEVERAGE, 22000, 2));
    Database.menuMap.put(23, new Food("Smoothie", TypeOfFood.BEVERAGE, 38000, 3));
    Database.menuMap.put(24, new Food("Orange Juice", TypeOfFood.BEVERAGE, 32000, 3));
    Database.menuMap.put(25, new Food("Coke", TypeOfFood.DRINKS, 15000, 4));
    Database.menuMap.put(26, new Food("Sprite", TypeOfFood.DRINKS, 15000, 4));
    Database.menuMap.put(27, new Food("Latte", TypeOfFood.BEVERAGE, 55000, 5));
    Database.menuMap.put(28, new Food("Milkshake", TypeOfFood.DRINKS, 45000, 5));
    Database.menuMap.put(29, new Food("Water", TypeOfFood.DRINKS, 12000, 6));
    Database.menuMap.put(30, new Food("Smoothie", TypeOfFood.BEVERAGE, 40000, 6));
    Database.menuMap.put(31, new Food("Tea", TypeOfFood.BEVERAGE, 20000, 2));
    Database.menuMap.put(32, new Food("Iced Coffee", TypeOfFood.BEVERAGE, 50000, 2));
    Database.menuMap.put(33, new Food("Orange Juice", TypeOfFood.BEVERAGE, 33000, 3));
    Database.menuMap.put(34, new Food("Smoothie", TypeOfFood.BEVERAGE, 35000, 3));
    Database.menuMap.put(35, new Food("Water", TypeOfFood.DRINKS, 10000, 4));
    Database.menuMap.put(36, new Food("Iced Latte", TypeOfFood.BEVERAGE, 50000, 5));
    Database.menuMap.put(37, new Food("Latte", TypeOfFood.BEVERAGE, 55000, 5));
    Database.menuMap.put(38, new Food("Green Tea", TypeOfFood.BEVERAGE, 22000, 6));
    Database.menuMap.put(39, new Food("Coke", TypeOfFood.DRINKS, 15000, 6));
    Database.menuMap.put(40, new Food("Espresso", TypeOfFood.BEVERAGE, 35000, 2));
    Database.menuMap.put(41, new Food("Milkshake", TypeOfFood.DRINKS, 45000, 3));
    Database.menuMap.put(42, new Food("Smoothie", TypeOfFood.BEVERAGE, 38000, 4));
    Database.menuMap.put(43, new Food("Orange Juice", TypeOfFood.BEVERAGE, 32000, 4));
    Database.menuMap.put(44, new Food("Soda", TypeOfFood.DRINKS, 15000, 5));
    Database.menuMap.put(45, new Food("Peach Tea", TypeOfFood.BEVERAGE, 25000, 5));
    Database.menuMap.put(46, new Food("Cold Brew", TypeOfFood.BEVERAGE, 60000, 6));
    Database.menuMap.put(47, new Food("Fruit Punch", TypeOfFood.DRINKS, 35000, 6));
    Database.menuMap.put(48, new Food("Iced Coffee", TypeOfFood.BEVERAGE, 50000, 2));
    Database.menuMap.put(49, new Food("Espresso", TypeOfFood.BEVERAGE, 35000, 3));
    Database.menuMap.put(50, new Food("Soda", TypeOfFood.DRINKS, 10000, 4));


  }
}
