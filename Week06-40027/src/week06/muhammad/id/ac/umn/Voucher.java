package week06.muhammad.id.ac.umn;

public class Voucher extends Barang {
    private double pajak;
    public static int total = 0;

    public Voucher(String id, String nama, double harga, int stok, double pajak) {
        super(id, nama, harga, stok);
        this.pajak = pajak;
    }

    public double getPajak() {
        return pajak;
    }

    public double getHargaJual() {
        return this.getHarga() + (this.getHarga() * this.getPajak());
    }
}
