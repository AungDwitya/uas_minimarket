package Minimarket;

import java.util.HashSet;
import java.util.Scanner;

public class Main {

    public static Scanner scan = new Scanner(System.in);
    public static Minimarket kasir = new Minimarket();
    public static MemberDatabase memberDatabase = new MemberDatabase();

    public static void main(String[] args) {
        initBarang();
        initMember();
        String cobaLagi = "n";
        do {
            showBarang();
            int pilihan = pilihMenu();

            switch (pilihan) {
                case 1:
                    transaksi();
                    break;
                case 2:
                    tambahBarang();
                    break;
                case 3:
                    updateBarang();
                    break;
                case 4:
                    deleteBarang();
                    break;
                case 5:
                    showMember();
                    break;
                case 6:
                    tambahMember();
                    break;
                case 7:
                    hapusMember();
                    break;
                default:
                    System.out.println("Inputan Salah Silakan Pilih Angka 1-7");
                    break;
            }

            System.out.print("Continue? (y/n): ");
            while (true) {
                String input = scan.next();
                if (input.equalsIgnoreCase("y") || input.equalsIgnoreCase("n")) {
                    cobaLagi = input;
                    break;
                } else {
                    System.out.println("Invalid Input. Please enter 'y' or 'n'.");
                }
            }
        } while (cobaLagi.equalsIgnoreCase("y"));
    }
    public static void initMember() {
        Member member1 = new Member("alex1 Doe");
        Member member2 = new Member("Jane Smith");
        Member member3 = new Member("Alice jack ");

        memberDatabase.addMember(member1);
        memberDatabase.addMember(member2);
        memberDatabase.addMember(member3);
    }
    public static void initBarang() {
        Barang barang1 = new Barang("Air Mineral", 5000,2);
        Barang barang2 = new Barang("Mie Sedap", 10000,3);
        Barang barang3 = new Barang("Pulpen  ", 15000,5);
        Barang barang4 = new Barang();
        barang4.setNamaBarang("Pensil  "); // Mengatur nilai namaBarang menggunakan metode setNamaBarang()
        barang4.setHarga(5000); // Mengatur nilai harga menggunakan metode setHarga()
        barang4.setStock(5); // Mengatur nilai harga menggunakan metode setHarga()

        kasir.listBarang.add(barang1);
        kasir.listBarang.add(barang2);
        kasir.listBarang.add(barang3);
        kasir.listBarang.add(barang4);
    }

    public static void showBarang() {
        System.out.println("-------------------- Minimarket -------------------");
        System.out.println("No" + "\t\tNama Barang " + " \t\t\tHarga" + "\t\t\t\t\t\tStok");
        int i = 0;
        for (Barang barang : kasir.listBarang) {
            i++;
            System.out.println((i) + "\t\t" + barang.getNamaBarang() + "\t\t\t\tRP." + barang.getHarga()+ "\t\t\t\t\t\t"+ barang.getStock());
        }
        System.out.println("---------------------------------------------------");
    }

    public static void showMember() {
        System.out.println("=============== Member List ===============");
        System.out.println("No" + "\tNama" + "\t\t\tPoint");
        int i = 0;
        for (Member member : memberDatabase.getMembers()) {
            i++;
            System.out.println((i) + "\t" + member.getNama() + "\t\t" + member.getPoint());
        }
        System.out.println("============================================");
    }

    public static int pilihMenu() {
        System.out.println("================================ Menu ================================");
        System.out.printf("%-5s %-20s\n", "No", "Opsi");
        System.out.println("----------------------------------------");
        System.out.printf("%-5s %-20s\n", "|1|", "Transaksi");
        System.out.printf("%-5s %-20s\n", "|2|", "Tambah Barang");
        System.out.printf("%-5s %-20s\n", "|3|", "Update Barang");
        System.out.printf("%-5s %-20s\n", "|4|", "Delete Barang");
        System.out.printf("%-5s %-20s\n", "|5|", "Tampilkan Member");
        System.out.printf("%-5s %-20s\n", "|6|", "Tambah Member");
        System.out.printf("%-5s %-20s\n", "|7|", "Hapus Member");
        System.out.println("=====================================================================");
        System.out.print("Masukkan Opsi: ");

        boolean isValidMenu = false;
        int pilih = 0;
        while(!isValidMenu) {
            try {
                while(!scan.hasNextInt()) {
                    System.out.println("\nInput harus berupa angka!");
                    System.out.println("Masukkan Opsi: ");
                    scan.next();
                }
                pilih = scan.nextInt();

                if(pilih > 7 || pilih < 1) {
                    throw new Exception("Nomor Menu Tidak Ada");
                }
                isValidMenu = true;
            } catch(Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return pilih;
    }

    public static void transaksi() {
        System.out.println("Anda Memilih Pilihan Transaksi");

        String validasi = "n";
        int totalHarga = 0;
        Member member = null;

        System.out.print("Apakah Pelanggan adalah Member? (y/n): ");
        while (!scan.hasNext("[yn]")) {
            System.out.println("Input harus berupa y atau n!");
            System.out.print("Apakah Pelanggan adalah Member? (y/n): ");
            scan.next();
        }
        boolean isMember = scan.next().equals("y");
        if (isMember) {
            showMember();
            System.out.print("Masukkan Nomor Member: ");
            while (!scan.hasNextInt()) {
                System.out.println("\nInput harus berupa angka!");
                System.out.print("Masukkan Nomor Member: ");
                scan.next();
            }
            int nomorMember = scan.nextInt();
            try {
                member = memberDatabase.getMemberByIndex(nomorMember - 1);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return;
            }
        }
        do {
            boolean isValidBarang = false;
            showBarang();
            while (!isValidBarang) {
                System.out.print("Masukkan Nomor Barang: ");
                try {
                    while (!scan.hasNextInt()) {
                        System.out.println("\nInput harus berupa angka!");
                        System.out.print("Masukkan Nomor Barang: ");
                        scan.next();
                    }
                    int nomor = scan.nextInt();
                    if (nomor < 1 || nomor > kasir.listBarang.size()) {
                        throw new Exception("Nomor Barang Tidak Ada");
                    }
                    isValidBarang = true;
                    Barang barang = kasir.listBarang.get(nomor - 1);
                    System.out.print("Masukkan Jumlah Pesanan: ");
                    while (!scan.hasNextInt()) {
                        System.out.println("\nInput harus berupa angka!");
                        System.out.print("Masukkan Jumlah Pesanan: ");
                        scan.next();
                    }
                    int jumlah = scan.nextInt();
                    if (jumlah <= 0) {
                        throw new Exception("Jumlah Pesanan harus lebih dari 0");
                    }
                    int stock = barang.getStock();
                    if (jumlah > stock) {
                        System.out.println("Maaf, stok tidak mencukupi. Stok saat ini: " + stock);
                        isValidBarang = false;
                        continue;
                    }
                    int harga = barang.getHarga();
                    int total = harga * jumlah;

                    totalHarga += total;
                    barang.setStock(stock - jumlah);

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }

            System.out.print("Pesan lagi? (y/n): ");
            while (!scan.hasNext("[yn]")) {
                System.out.println("Input harus berupa y atau n!");
                scan.next();
            }
            validasi = scan.next();
        } while (validasi.equals("y"));

        kasir.diskon(totalHarga, member);
    }

    public static void tambahBarang() {
        System.out.println("Anda Memilih Pilihan Tambah Barang");
        HashSet<String> cek = new HashSet<String>();

        for (Barang barang : kasir.listBarang) {
            cek.add(barang.getNamaBarang());
        }

        System.out.print("Masukkan Nama Barang: ");
        scan.nextLine();
        String namaBarang = scan.nextLine();

        while (cek.contains(namaBarang)) {
            System.out.println("\nNama Barang Sudah Ada");
            System.out.print("Masukkan Nama Barang: ");
            namaBarang = scan.nextLine();
        }
//        System.out.print("Masukkan Harga Barang: ");
//        while (!scan.hasNextInt()) {
//            System.out.println("\nInput Harga harus berupa angka!");
//            System.out.print("Masukkan Harga Barang: ");
//            scan.next();
//        }
        int harga = getInputNumber("Masukan Harga Barang : ");
//        System.out.print("Masukkan Stock Barang: ");
//        while (!scan.hasNextInt()) {
//            System.out.println("\nInput Stock harus berupa angka!");
//            System.out.print("Masukkan Stock Barang: ");
//            scan.next();
//        }
        int stock = getInputNumber("Masukkan Stock Barang: ");

        Barang tambah = new Barang(harga, namaBarang, stock);
        kasir.tambahBarang(tambah);
    }

    public static void updateBarang() {
        System.out.println("Anda Memilih Pilihan Update Barang");

        boolean validNomor = false;
        while (!validNomor) {
            System.out.println("Masukkan Nomor Barang Yang Mau di Update: ");
            try {
                while (!scan.hasNextInt()) {
                    System.out.println("\nInput Nomor Barang harus berupa angka!");
                    System.out.println("Masukkan Nomor Barang Yang Mau di Update: ");
                    scan.next();
                }
                int nomor = scan.nextInt();
                if (nomor < 1 || nomor > kasir.listBarang.size()) {
                    throw new Exception("Nomor Barang Tidak Ada");
                }
                validNomor = true;
                Barang barang = kasir.listBarang.get(nomor - 1);
                System.out.print("Masukkan Nama Barang Baru: ");
                scan.nextLine();
                String namaBarang = scan.nextLine();
                if (!namaBarang.isEmpty()) {
                    barang.setNamaBarang(namaBarang);
                }

//                System.out.print("Masukkan Harga Barang Baru: ");
//                String hargaBarang = scan.nextLine();
//                if (!hargaBarang.isEmpty()) {
//                    try {
//                        int harga = Integer.parseInt(hargaBarang);
//                        barang.setHarga(harga);
//                    } catch (NumberFormatException e) {
//                        System.out.println("Input Harga harus berupa angka!");
//                    }
//                }
//                System.out.print("Masukkan Stock Barang Baru: ");
//                String stockBarang = scan.nextLine();
//                if (!stockBarang.isEmpty()) {
//                    try {
//                        int stock = Integer.parseInt(stockBarang);
//                        barang.setStock(stock);
//                    } catch (NumberFormatException e) {
//                        System.out.println("Input Stock harus berupa angka!");
//                    }
//                }
                int harga = getInputNumber("Masukkan Harga Barang Baru: ");
                if (harga > 0) {
                    barang.setHarga(harga);
                }

                int stock = getInputNumber("Masukkan Stock Barang Baru: ");
                if (stock >= 0) {
                    barang.setStock(stock);
                }

                System.out.println("Barang berhasil diupdate!");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void deleteBarang() {
        System.out.println("Anda Memilih Pilihan Delete Barang");

        boolean validNomor = false;
        while (!validNomor) {
            System.out.println("Masukkan Nomor Barang Yang Mau di Delete: ");

            try {
                while (!scan.hasNextInt()) {
                    System.out.println("\nInput harus berupa angka!");
                    System.out.println("Masukkan Nomor Barang Yang Mau di Delete: ");
                    scan.next();
                }

                int nomor = scan.nextInt();
                if (nomor < 1 || nomor > kasir.listBarang.size()) {
                    throw new Exception("Nomor Barang Tidak Ada");
                }
                validNomor = true;

                System.out.println("Apakah Anda Ingin Menghapus Barang " + kasir.listBarang.get(nomor - 1).getNamaBarang() + " Dari Menu? (y/n)");

                while (!scan.hasNext("[yn]")) {
                    System.out.println("Input harus berupa y atau n!");
                    scan.next();
                }

                String validasi = scan.next();

                if (validasi.equals("y")) {
                    kasir.deleteBarang(nomor);
                }

            } catch (Exception e) {
                System.out.println("Nomor Barang Tidak Ada");
            }
        }
    }

    public static void tambahMember() {
        System.out.println("Anda Memilih Pilihan Tambah Member");
        System.out.print("Masukkan Nama Member: ");
        scan.nextLine();
        String namaMember = scan.nextLine();

        Member member = new Member(namaMember);
        memberDatabase.addMember(member);

        System.out.println("Member berhasil ditambahkan!");
    }

    public static void hapusMember() {
        System.out.println("Anda Memilih Pilihan Hapus Member");
        showMember();

        System.out.print("Masukkan Nomor Member yang ingin dihapus: ");
        while (!scan.hasNextInt()) {
            System.out.println("\nInput harus berupa angka!");
            System.out.print("Masukkan Nomor Member yang ingin dihapus: ");
            scan.next();
        }
        int nomor = scan.nextInt();

        try {
            Member member = memberDatabase.getMemberByIndex(nomor-1);
            memberDatabase.removeMember(member);
            System.out.println("Member berhasil dihapus!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    // method revisi cek inputan angka harus berupa angka
    public static int getInputNumber(String message) {
        int number = 0;
        boolean isValidInput = false;

        while (!isValidInput) {
            System.out.print(message);
            try {
                number = Integer.parseInt(scan.nextLine());
                isValidInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka!");
            }
        }

        return number;
    }
}
