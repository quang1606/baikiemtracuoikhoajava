package baikiemtra.entities.applicationmanagement;

public class Feedback {
    private static  int idCount;
    private int id;
    private int idCustomer;
    private int idFood;
    private String nameCustomer;
    private Review review;
    private String  Content;

    public Feedback(int idCustomer, int idFood, String nameCustomer, Review review, String content) {
        this.id = ++idCount;
        this.idCustomer = idCustomer;
        this.idFood = idFood;
        this.nameCustomer = nameCustomer;
        this.review = review;

        Content = content;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public static int getIdCount() {
        return idCount;
    }

    public static void setIdCount(int idCount) {
        Feedback.idCount = idCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public int getIdFood() {
        return idFood;
    }

    public void setIdFood(int idFood) {
        this.idFood = idFood;
    }

    public String getNameCustomer() {
        return nameCustomer;
    }

    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
    }




    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    @Override
    public String toString() {
        return "Feedback{" +
                ", nameCustomer='" + nameCustomer + '\'' +
                ", feedback=" + review +
                ", Content='" + Content + '\'' +
                '}';
    }
}
