package week09.muhammad.id.ac.umn;

public class Magang extends Karyawan {
    public Magang() {}
    public Magang(String id, String nama) {
        super(id, nama);
    }

    @Override
    public int getGaji() {
        return 1500000;
    }
}
