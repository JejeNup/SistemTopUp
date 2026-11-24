package com.mycompany.sistemtopup;

import java.util.InputMismatchException;
import java.util.Scanner;

public class SistemTopUp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        LefelupStore tokoLefelup = new LefelupStore("Lefelup Top-Up Store");
        tokoLefelup.bukaToko();
        tokoLefelup.muatDataDariFile();
        
        if (tokoLefelup.isDataKosong()) {
            tokoLefelup.tambahPelanggan(new Pelanggan("102938475", "JeJuy"));
            tokoLefelup.tambahPelanggan(new Pelanggan("98765432", "Sultan Gantenk"));
            tokoLefelup.tambahPelanggan(new Pelanggan("OrangGanteng", "OTWIMO100"));

            try {
                tokoLefelup.tambahTransaksi(new TopUpGame("Genshin", "102938475", 300, 300 * 1500.0, "8012", "Asia"));
                tokoLefelup.tambahTransaksi(new TopUpGame("MLBB", "98765432", 1000, 1000 * 1500.0, "2021", "Global"));
                tokoLefelup.tambahTransaksi(new Voucher("Roblox", "OrangGanteng", 500, 500 * 1500.0));
                tokoLefelup.tambahTransaksi(new LanggananPremium("Genshin", "Plismenangrateoff", 1, 1 * 79000.0, 30));
            } catch (Exception e) {
                System.out.println("Gagal memuat data awal: " + e.getMessage());
            }
        }
        
        boolean isRunning = true;
        System.out.println("==========================================");
        System.out.println("     WELCOME TO LEFELUP TOP-UP STORE      ");
        System.out.println("==========================================");            
        
        while(isRunning) {
            System.out.println("\n--- MENU UTAMA ---");
            System.out.println("1. Tambah Transaksi Top-Up");
            System.out.println("2. Lihat Daftar Transaksi");
            System.out.println("3. Cari Riwayat Transaksi");
            System.out.println("4. Manajemen Pelanggan");
            System.out.println("5. Validasi Pesanan (Association & Tulis Log)");
            System.out.println("6. Baca File Log Transaksi (Demo File I/O Teks)");
            System.out.println("7. Simpan & Keluar (Demo Serialization)");
            System.out.print("Pilih Menu (1-7): ");
            
            try {
                int pilihan = scanner.nextInt();
                scanner.nextLine(); 

                switch(pilihan) {
                    case 1 -> {
                        System.out.println("\n-- Pilih Jenis Layanan --");
                        System.out.println("1. Top-Up Game Langsung (Direct UID & Server)");
                        System.out.println("2. Voucher Digital (Kode Redeem)");
                        System.out.println("3. Langganan/Membership");
                        System.out.print("Pilihan (1/2/3): ");
                        
                        int jenisLayanan = scanner.nextInt();
                        scanner.nextLine();
                        
                        System.out.print("Masukkan Nama Game/Platform : ");
                        String game = scanner.nextLine();
                        System.out.print("Masukkan ID Pelanggan / UID : ");
                        String uid = scanner.nextLine();
                        System.out.print("Masukkan Jumlah Item/Kuantitas: ");
                        int nominal = scanner.nextInt();
                        scanner.nextLine();
                        
                        double harga = (jenisLayanan == 3) ? nominal * 79000.0 : nominal * 1500.0;
                        
                        if (jenisLayanan == 1) {
                            System.out.print("Masukkan ID Server / Zone ID  : ");
                            String idServer = scanner.nextLine();
                            System.out.print("Masukkan Nama Server Region   : ");
                            String namaServer = scanner.nextLine();
                            tokoLefelup.tambahTransaksi(new TopUpGame(game, uid, nominal, harga, idServer, namaServer));
                        } else if (jenisLayanan == 2) {
                            tokoLefelup.tambahTransaksi(new Voucher(game, uid, nominal, harga));
                        } else if (jenisLayanan == 3) {
                            System.out.print("Masukkan Durasi Aktif (Hari)  : ");
                            int durasi = scanner.nextInt();
                            scanner.nextLine();
                            tokoLefelup.tambahTransaksi(new LanggananPremium(game, uid, nominal, harga, durasi));
                        } else {
                            System.out.println("Jenis layanan tidak valid!");
                        }
                    }
                    case 2 -> {
                        System.out.println("\n--- Daftar Transaksi di Toko ---");
                        tokoLefelup.lihatSemuaTransaksi();
                    }
                    case 3 -> {
                        System.out.println("\n-- Fitur Cari Pesanan (Method Overloading) --");
                        System.out.println("1. Cari berdasarkan UID Pelanggan (String)");
                        System.out.println("2. Cari berdasarkan Minimal Item (Integer)");
                        System.out.print("Pilih moda pencarian (1/2): ");
                        
                        int tipeCari = scanner.nextInt();
                        scanner.nextLine();
                        
                        try {
                            if (tipeCari == 1) {
                                System.out.print("Masukkan UID Pelanggan: ");
                                String kataKunci = scanner.nextLine();
                                tokoLefelup.cariTransaksi(kataKunci);
                            } else if (tipeCari == 2) {
                                System.out.print("Masukkan Minimal Nominal Item: ");
                                int angkaKunci = scanner.nextInt();
                                scanner.nextLine();
                                tokoLefelup.cariTransaksi(angkaKunci);
                            } else {
                                System.out.println("Pilihan tidak valid.");
                            }
                        } catch (TransaksiTidakDitemukanException e) {
                            System.out.println("Peringatan: " + e.getMessage());
                        }
                    }
                    case 4 -> {
                        System.out.println("\n-- Manajemen Pelanggan --");
                        System.out.println("1. Tambah Pelanggan Baru");
                        System.out.println("2. Cari Pelanggan via UID");
                        System.out.println("3. Lihat Semua Pelanggan");
                        System.out.print("Pilih: ");
                        int menuA = scanner.nextInt();
                        scanner.nextLine();

                        if (menuA == 1) {
                            System.out.print("Masukkan UID: ");
                            String uid = scanner.nextLine();
                            System.out.print("Masukkan Nama: ");
                            String nama = scanner.nextLine();
                            tokoLefelup.tambahPelanggan(new Pelanggan(uid, nama));
                        } else if (menuA == 2) {
                            System.out.print("UID yang dicari: ");
                            tokoLefelup.cariPelanggan(scanner.nextLine());
                        } else if (menuA == 3) {
                            tokoLefelup.lihatSemuaPelanggan();
                        }
                    }
                    case 5 -> {
                        System.out.println("\n-- Proses Validasi Transaksi (Association) --");
                        System.out.print("Masukkan UID Pelanggan: ");
                        String inputUid = scanner.nextLine();
                        
                        Pelanggan p = tokoLefelup.getPelanggan(inputUid);
                        
                        if (p == null) {
                            System.out.println("Gagal: Pelanggan belum terdaftar di sistem!");
                        } else if (tokoLefelup.getJumlahTransaksi() == 0) {
                            System.out.println("Gagal: Belum ada transaksi yang tersedia di toko.");
                        } else {
                            tokoLefelup.lihatSemuaTransaksi();
                            System.out.print("Pilih Nomor Transaksi yang ingin divalidasi: ");
                            int noTransaksi = scanner.nextInt() - 1;
                            scanner.nextLine();
                            
                            if (noTransaksi >= 0 && noTransaksi < tokoLefelup.getJumlahTransaksi()) {
                                Transaksi t = tokoLefelup.getTransaksiInt(noTransaksi);
                                LayananTransaksi.prosesValidasi(p, t);
                            } else {
                                System.out.println("Nomor transaksi tidak valid!");
                            }
                        }
                    }
                    case 6 -> {
                        tokoLefelup.bacaLogTransaksi();
                    }
                    case 7 -> {
                        System.out.println("Menyimpan sesi ke database internal...");
                        tokoLefelup.simpanDataKeFile();
                        System.out.println("Terima kasih telah menggunakan layanan Lefelup Store!");
                        isRunning = false;
                    }
                    default -> System.out.println("Pilihan tidak valid (1-7).");
                }
            } catch (InputMismatchException e) {
                System.out.println("[ERROR INPUT] Harap masukkan format angka yang benar.");
                scanner.nextLine(); 
            } catch (Exception e) {
                System.out.println("[SISTEM ERROR] Terjadi kesalahan: " + e.getMessage());
            }
        }
        scanner.close();
    }
}