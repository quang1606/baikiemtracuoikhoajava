package baikiemtra.entities.applicationmanagement;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Voucher {
    private static int idCount;
    private int id;
    private int idSaller;
    private double reductionLevel ;
    private int quantity;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Voucher( int idSaller, double reductionLevel, int quantity, LocalDateTime startTime, LocalDateTime endTime) {
        this.id = ++idCount;
        this.idSaller = idSaller;
        this.reductionLevel = reductionLevel;
        this.quantity = quantity;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public static int getIdCount() {
        return idCount;
    }

    public static void setIdCount(int idCount) {
        Voucher.idCount = idCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdSaller() {
        return idSaller;
    }

    public void setIdSaller(int idSaller) {
        this.idSaller = idSaller;
    }

    public double getReductionLevel() {
        return reductionLevel;
    }

    public void setReductionLevel(double reductionLevel) {
        this.reductionLevel = reductionLevel;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Voucher{" +
                "id=" + id +
                ", reductionLevel=" + reductionLevel +
                ", quantity=" + quantity +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
