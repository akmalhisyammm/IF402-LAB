package week06.muhammad.id.ac.umn;

public class Handphone extends Barang {
    private String warna;
    public static int total = 0;

    public Handphone(String id, String nama, double harga, int stok, String warna) {
        super(id, nama, harga, stok);
        this.warna = warna;
    }

    public String getWarna() {
        return warna;
    }
}
