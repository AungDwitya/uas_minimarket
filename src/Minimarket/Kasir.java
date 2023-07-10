package Minimarket;
import java.util.ArrayList;

interface Penjualan {
    void tambahBarang(Barang barang);
    void deleteBarang(int nomor);
}

abstract class Kasir implements Penjualan {
    public ArrayList<Barang> listBarang = new ArrayList<Barang>();

    public void diskon(int totalHarga, Member member) {
        if (member != null) {
            int diskon = totalHarga * 20 / 100;
            int hargaDiskon = totalHarga - diskon;

            if (totalHarga >= 50000) {
                int bonusPoint = totalHarga / 50000 * 100;
                member.tambahPoint(bonusPoint);
                System.out.println("============================ TOTAL PEMBAYARAN =============================");
                System.out.println("Anda mendapatkan " + bonusPoint + " poin karena total pembelian mencapai kelipatan 50000.");


                System.out.println("Total Harga: " + totalHarga);
                System.out.println("Selamat  " + member.getNama() + " ! anda Mendapatkan Diskon Sebesar 20%");

                System.out.println("\nTotal pembayaran Anda Menjadi: Rp. " + hargaDiskon);
                System.out.println("======================== TERIMA KASIH =========================");
            } else {
                System.out.println("============================ TOTAL PEMBAYARAN =============================");
                System.out.println("\nTotal pembayaran Anda Menjadi: Rp. " + totalHarga);
                System.out.println("============================   TERIMA KASIH   =========================");
            }
        }
    }
}

class Minimarket extends Kasir {
    @Override
    public void tambahBarang(Barang barang) {
        this.listBarang.add(barang);
    }

    @Override
    public void deleteBarang(int nomor) {
        listBarang.remove(nomor-1);
        System.out.println("Barang Berhasil Dihapus");
    }
}
