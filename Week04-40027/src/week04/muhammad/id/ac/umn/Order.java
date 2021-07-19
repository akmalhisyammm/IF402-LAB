package week04.muhammad.id.ac.umn;

public class Order {
    private int id, jumlah;
    private Barang barang;
    public static int total = 0;

    public Order(int id, Barang barang, int jumlah) {
        this.id = id;
        this.barang = barang;
        this.jumlah = jumlah;
    }

    public int getId() {
        return id;
    }

    public int getJumlah() {
        return jumlah;
    }

    public Barang getBarang() {
        return barang;
    }
}
