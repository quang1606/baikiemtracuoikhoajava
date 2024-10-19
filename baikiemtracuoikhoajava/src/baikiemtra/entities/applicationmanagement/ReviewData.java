package baikiemtra.entities.applicationmanagement;

public class ReviewData {
    private double totalStars;
    private int count;

    public ReviewData(double totalStars, int count) {
        this.totalStars = totalStars;
        this.count = count;
    }

    public double getTotalStars() {
        return totalStars;
    }

    public int getCount() {
        return count;
    }

    public void addReview(double stars) {
        totalStars += stars;
        count++;
    }

    public double getAverage() {
        if (count == 0) {
            return 0;
        }
        return Math.round((totalStars / count) * 10.0) / 10.0; // Làm tròn 1 chữ số thập phân
    }
}
