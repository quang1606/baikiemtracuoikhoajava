package baikiemtra.entities.applicationmanagement;

public enum Review {
    ONESTARS(1), TWOSTARS(2), THREESTARS(3), FOURSTARS(4), FIVESTARS(5);

    private final int diem;

    Review(int diem) {
        this.diem = diem;
    }

    public int getDiem() {
        return diem;
    }
}
