package week09.muhammad.id.ac.umn;

public class Tetap extends Karyawan {
    public Tetap() {}
    public Tetap(String id, String nama) {
        super(id, nama);
    }

    @Override
    public int getGaji() {
        return 3000000;
    }
}
