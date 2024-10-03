package baikiemtra.untils;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Utils {
    public static final int floorFee = 3000;
    public static final double ship = 8000;
    private static final double EARTH_RADIUS = 6371.0;
    public static final String EMAIL_REGEX = "^[\\w!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,7}$";
    public static final String PASSWORD_REGEX = "^(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?])(?=.*\\d)[A-Za-z\\d!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]{7,15}$";
    public static int inputInteger(Scanner scanner) {

        do {
            try {
                int n = Integer.parseInt(scanner.nextLine());
                return n;
            } catch (Exception e) {
                System.out.println("Bạn nhập không hợp lệ vui lòng nhập vào số nguyên");
            }
        } while (true);
    }
    public static double inputDouble(Scanner scanner) {

        do {
            try {
                double n = Double.parseDouble(scanner.nextLine());
                return n;
            } catch (Exception e) {
                System.out.println("Bạn nhập không hợp lệ vui lòng nhập vào số thuc");
            }
        } while (true);
    }
   public static LocalDateTime inputLocalDateTime(Scanner scanner) {
       DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
       do {
           try {
               System.out.println("Nhap vao thoi gian (định dạng yyyy-MM-dd HH:mm:ss): ");
               String time = scanner.nextLine();
               return LocalDateTime.parse(time, dateTimeFormatter);
           } catch (DateTimeParseException e) {
               System.out.println("Thời gian không hợp lệ, vui lòng nhập lại!");
           }
       } while (true) ;

   }

    public static double intPutLongitude(Scanner scanner){
        do {
            double longitude = inputDouble(scanner);
            if(105.75<=longitude && longitude<=106.05){
                return longitude;
            }else {
                System.out.println("Kinh do nam ngoai thu do Ha Noi");
            }
        }while (true);

    }
    public static double intPutLatitude(Scanner scanner){
        do {
            double latitude= inputDouble(scanner);
            if(20.75<=latitude && latitude<=21.33){
                return latitude;
            }else {
                System.out.println("Vi do nam ngoai thu do Ha Noi");
            }
        }while (true);
    }
    public static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        double lat1Rad = Math.toRadians(lat1);
        double lon1Rad = Math.toRadians(lon1);
        double lat2Rad = Math.toRadians(lat2);
        double lon2Rad = Math.toRadians(lon2);

        double deltaLat = lat2Rad - lat1Rad;
        double deltaLon = lon2Rad - lon1Rad;

        double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2)
                + Math.cos(lat1Rad) * Math.cos(lat2Rad)
                * Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = EARTH_RADIUS * c;
        // Làm tròn đến 1 chữ số thập phân
        return Math.round(distance * 10.0) / 10.0;
    }

}
