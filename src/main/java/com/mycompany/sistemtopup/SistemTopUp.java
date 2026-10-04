package com.mycompany.sistemtopup;

import java.util.Scanner;

public class SistemTopUp {
    
    public static void cariPesanan(String idPelanggan, Transaksi[] daftarTransaksi, int jumlahTransaksi) {
        System.out.println("\n[Hasil Pencarian] UID/Akun: " + idPelanggan);
        boolean ditemukan = false;
        for (int i = 0; i < jumlahTransaksi; i++) {
            if (daftarTransaksi[i].getIdPelanggan().equalsIgnoreCase(idPelanggan)) { 
                System.out.print("- ");
                daftarTransaksi[i].tampilkanStruk();
                ditemukan = true;
            }
        }
        if (!ditemukan) System.out.println("Data transaksi tidak ditemukan.");
    }

    public static void cariPesanan(int nominalMaksimal, Transaksi[] daftarTransaksi, int jumlahTransaksi) {
        System.out.println("\n[Hasil Filter] Transaksi dengan Nominal >= " + nominalMaksimal + " Item:");
        boolean ditemukan = false;
        for (int i = 0; i < jumlahTransaksi; i++) {
            if (daftarTransaksi[i].getNominalTopUp() >= nominalMaksimal) { 
                System.out.print("- ");
                daftarTransaksi[i].tampilkanStruk();
                ditemukan = true;
            }
        }
        if (!ditemukan) System.out.println("Tidak ada transaksi yang memenuhi kriteria nominal tersebut.");
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Transaksi[] daftarTransaksi = new Transaksi[20];
            int jumlahTransaksi = 0;
            
            daftarTransaksi[jumlahTransaksi++] = new TopUpGame("Genshin", "102938475", 300, 300 * 1500.0, "8012", "Asia");
            daftarTransaksi[jumlahTransaksi++] = new TopUpGame("MLBB", "98765432", 1000, 1000 * 1500.0, "2021", "Global");
            daftarTransaksi[jumlahTransaksi++] = new Voucher("Roblox", "OrangGanteng", 500, 500 * 1500.0);
            
            boolean isRunning = true;
            
            System.out.println("==========================================");
            System.out.println("     WELCOME TO LEFELUP TOP-UP STORE      ");
            System.out.println("==========================================");            
            
            while(isRunning) {
                System.out.println("\n--- MENU UTAMA ---");
                System.out.println("1. Tambah Transaksi Top-Up Baru");
                System.out.println("2. Tampilkan Seluruh Data Transaksi");
                System.out.println("3. Cari Transaksi (Fitur Overloading)");
                System.out.println("4. Keluar");
                System.out.print("Pilih opsi (1-4): ");
                
                int pilihan = scanner.nextInt();
                scanner.nextLine(); 

                switch(pilihan) {
                    case 1 -> {
                        if (jumlahTransaksi < daftarTransaksi.length) {
                            System.out.println("\n-- Pilih Jenis Layanan --");
                            System.out.println("1. Top-Up Game Langsung (Direct UID & Server)");
                            System.out.println("2. Voucher Digital (Kode Redeem)");
                            System.out.print("Pilihan (1/2): ");
                            
                            int PCase1 = scanner.nextInt();
                            scanner.nextLine();
                            
                            System.out.print("Masukkan Nama Game/Platform : ");
                            String game = scanner.nextLine();
                            
                            System.out.print("Masukkan ID Pelanggan / UID : ");
                            String uid = scanner.nextLine();
                            
                            System.out.print("Masukkan Jumlah Item: ");
                            int nominal = scanner.nextInt();
                            scanner.nextLine();
                            
                            double harga = nominal * 1500.0;
                            System.out.println("-> Total harga: Rp" + (long)harga);
                            
                            if (PCase1 == 1) {
                                System.out.print("Masukkan ID Server / Zone ID  : ");
                                String idServer = scanner.nextLine();
                                System.out.print("Masukkan Nama Server Region   : ");
                                String namaServer = scanner.nextLine();
                                
                                daftarTransaksi[jumlahTransaksi] = new TopUpGame(game, uid, nominal, harga, idServer, namaServer);
                            } else if (PCase1 == 2) {
                                System.out.println("-> Membuat kode redeem unik...");
                                daftarTransaksi[jumlahTransaksi] = new Voucher(game, uid, nominal, harga);
                            } else {
                                System.out.println("Jenis layanan tidak valid!");
                                break;
                            }
                            
                            jumlahTransaksi++;
                            System.out.println(">> Sukses: Transaksi baru berhasil ditambahkan!");
                        } else {
                            System.out.println("Maaf yah kapasitas toko kami lagi penuh!");
                        }
                    }
                    case 2 -> {
                        System.out.println("\n=================== DAFTAR TRANSAKSI ===================");
                        if (jumlahTransaksi == 0) {
                            System.out.println("Belum ada data transaksi.");
                        } else {
                            for (int i = 0; i < jumlahTransaksi; i++) {
                                System.out.print((i + 1) + ". ");
                                daftarTransaksi[i].tampilkanStruk();
                                daftarTransaksi[i].instruksiProses();
                                System.out.println("--------------------------------------------------------");
                            }
                            System.out.println("Total Transaksi Berhasil Dibuat: " + Transaksi.totalTransaksiBerhasil);
                        }
                        System.out.print("\nTekan Enter untuk kembali ke menu...");
                        scanner.nextLine();
                    }
                    case 3 -> {
                        System.out.println("\n-- Fitur Cari Pesanan (Method Overloading) --");
                        System.out.println("1. Cari berdasarkan UID Pelanggan (String)");
                        System.out.println("2. Cari berdasarkan Minimal Item (Integer)");
                        System.out.print("Pilih moda pencarian (1/2): ");
                        int CariPesanan = scanner.nextInt();
                        scanner.nextLine();
                        
                        if (CariPesanan == 1) {
                            System.out.print("Masukkan UID Pelanggan: ");
                            String kataKunci = scanner.nextLine();
                            cariPesanan(kataKunci, daftarTransaksi, jumlahTransaksi);
                        } else if (CariPesanan == 2) {
                            System.out.print("Masukkan Minimal Nominal Item: ");
                            int angkaKunci = scanner.nextInt();
                            scanner.nextLine();
                            cariPesanan(angkaKunci, daftarTransaksi, jumlahTransaksi);
                        } else {
                            System.out.println("Pilihan tidak valid.");
                        }
                        System.out.print("\nTekan Enter untuk kembali ke menu...");
                        scanner.nextLine();
                    }
                    case 4 -> {
                        System.out.println("Terima kasih! Balik lagi yah.");
                        isRunning = false;
                    }
                    default -> System.out.println("Opsi menu tidak valid (Pilih 1-4).");
                }
            }
        }
    }
}