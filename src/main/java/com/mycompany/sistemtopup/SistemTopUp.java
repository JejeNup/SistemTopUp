package com.mycompany.sistemtopup;

import java.util.Scanner;

public class SistemTopUp {
    
    public static void cariPesanan(String idPelanggan, Transaksi[] daftarTransaksi, int jumlahTransaksi) {
        System.out.println("Mencari Riwayat Transaksi untuk UID/Akun: " + idPelanggan);
        boolean ditemukan = false;
        for (int i = 0; i < jumlahTransaksi; i++) {
            if (daftarTransaksi[i].getIdPelanggan().equalsIgnoreCase(idPelanggan)) { 
                System.out.print("- Ditemukan: ");
                daftarTransaksi[i].tampilkanStruk();
                ditemukan = true;
            }
        }
        if (!ditemukan) System.out.println("Tidak ada transaksi untuk akun tersebut.");
    }

    public static void cariPesanan(int nominalMaksimal, Transaksi[] daftarTransaksi, int jumlahTransaksi) {
        System.out.println("Mencari Transaksi dengan Nominal Setidaknya: " + nominalMaksimal + " Item");
        boolean ditemukan = false;
        for (int i = 0; i < jumlahTransaksi; i++) {
            if (daftarTransaksi[i].getNominalTopUp() >= nominalMaksimal) { 
                System.out.print("- Ditemukan: ");
                daftarTransaksi[i].tampilkanStruk();
                ditemukan = true;
            }
        }
        if (!ditemukan) System.out.println("Tidak ada transaksi dengan nominal sebesar itu.");
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Transaksi[] daftarTransaksi = new Transaksi[15];
            
            int jumlahTransaksi = 0;
            boolean isRunning = true;
            
            System.out.println("========================================");
            System.out.println("             LEFELUP STROE              ");
            System.out.println("========================================");            
            
            while(isRunning) {
                System.out.println("\nMenu Utama:");
                System.out.println("1. Buat Pesanan Top-Up");
                System.out.println("2. Lihat Riwayat Pesanan");
                System.out.println("3. Cari Pesanan ");
                System.out.println("4. Keluar");
                System.out.print("Pilih Menu: 1-4: ");
                
                int pilihan = scanner.nextInt();
                scanner.nextLine(); 
                
                switch(pilihan) {
                    case 1 -> {
                        if (jumlahTransaksi < daftarTransaksi.length) {
                            System.out.println("\n-- Pilih Kategori Layanan --");
                            System.out.println("1. Top-Up Game (UID & Server)");
                            System.out.println("2. Voucher Digital (Kode Redeem)");
                            System.out.print("Pilihan (1/2): ");
                            
                            int PCase1 = scanner.nextInt();
                            scanner.nextLine();
                            
                            System.out.print("Masukkan Nama Game: ");
                            String game = scanner.nextLine();
                            
                            System.out.print("Masukkan ID Pelanggan / UID: ");
                            String uid = scanner.nextLine();
                            
                            System.out.print("Masukkan Jumlah item : ");
                            int nominal = scanner.nextInt();
                            scanner.nextLine();
                            
                            double harga = nominal * 1500.0;
                            System.out.println("-> Sistem: Total transaksi = Rp" + harga);
                            
                            if (PCase1 == 1) {
                                System.out.print("Masukkan ID Server / Zone ID (Bila tidak ada isi '-'): ");
                                String idServer = scanner.nextLine();
                                System.out.print("Masukkan Nama Server (ex: Asia / Global): ");
                                String namaServer = scanner.nextLine();
                                daftarTransaksi[jumlahTransaksi] = new TopUpGame(game, uid, nominal, harga, idServer, namaServer);
                            } else if (PCase1 == 2){
                                System.out.println("-> Sistem: Kode Redeem berhasil dibuat, lihat di riwayat pesanan...");
                                daftarTransaksi[jumlahTransaksi] = new Voucher(game, uid, nominal, harga);
                            } else {
                                System.out.println("Pilihan layanan tidak valid!");
                                continue; 
                            }
                            
                            jumlahTransaksi++;
                            System.out.println("Sukses! Pesanan berhasil masuk ke sistem.");
                        } else {
                            System.out.println("Saat ini LEFELUP STORE lagi ramai, balik lagi nanti ya!");
                        }
                    }
                    case 2 -> {
                        System.out.println("\n--- Riwayat Pesanan LEFELUP STORE ---");
                        if (jumlahTransaksi == 0) {
                            System.out.println("YAH MASIH KOSONG :( ");
                        } else {
                            for (int i = 0; i < jumlahTransaksi; i++) {
                                System.out.print((i + 1) + ". ");
                                daftarTransaksi[i].tampilkanStruk();
                                daftarTransaksi[i].instruksiProses();
                                System.out.println();
                            }
                            System.out.println("* Total Transaksi Sukses: " + Transaksi.totalTransaksiBerhasil);
                        }
                        System.out.print("Tekan Enter untuk melanjutkan...");
                        scanner.nextLine();
                    }
                    case 3 -> {
                        System.out.println("\n-- Fitur Cari Pesanan --");
                        System.out.println("1. Cari berdasarkan UID Pemain. ");
                        System.out.println("2. Cari berdasarkan jumlah topup. ");
                        System.out.print("Pilih (1/2): ");
                        int Cari = scanner.nextInt();
                        scanner.nextLine();
                        
                        switch (Cari) {
                            case 1 -> {
                                System.out.print("Masukkan UID yang dicari: ");
                                String kataKunci = scanner.nextLine();
                                cariPesanan(kataKunci, daftarTransaksi, jumlahTransaksi);
                            }
                            case 2 -> {
                                System.out.print("Masukkan Minimal Item: ");
                                int angkaKunci = scanner.nextInt();
                                scanner.nextLine();
                                cariPesanan(angkaKunci, daftarTransaksi, jumlahTransaksi);
                            }
                            default -> System.out.println("Pilihan tidak valid.");
                        }
                        System.out.print("Tekan Enter untuk melanjutkan...");
                        scanner.nextLine();
                    }
                    case 4 -> {
                        System.out.println("Balik lagi yah!");
                        isRunning = false;
                    }
                    default -> {
                        System.out.println("Pilihan tidak valid. Silahkan masukkan angka 1-4.");
                    }
                }
            }
        }
    }
}