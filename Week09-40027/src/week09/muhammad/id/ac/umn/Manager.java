package week09.muhammad.id.ac.umn;

public class Manager extends Karyawan {
    public Manager() {}
    public Manager(String id, String nama) {
        super(id, nama);
    }

    @Override
    public int getGaji() {
        return 10000000;
    }
}
