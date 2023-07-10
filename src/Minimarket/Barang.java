package Minimarket;

public class Barang {
    private String namaBarang;
    private int harga;
    private int stock;

    public Barang(String namaBarang, int harga,int stock) {
        this.namaBarang = namaBarang;
        this.harga = harga;
        this.stock = stock;
    }

    public Barang(int harga, String barang, int jml) {
        this.namaBarang = barang;
        this.harga = harga;
        this.stock = jml;
    }

    public Barang() {

    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getStock() {
        return stock;
    }
    public String getNamaBarang() {
        return namaBarang;
    }

    public int getHarga() {
        return harga;
    }
}

