package com.mycompany.sistemtopup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SistemTopUp {
    
    public static void cariPesanan(String idPelanggan, ArrayList<Transaksi> daftarTransaksi) throws TransaksiTidakDitemukanException {
        System.out.println("\n[Hasil Pencarian] UID/Akun: " + idPelanggan);
        boolean ditemukan = false;
        
        for (int i = 0; i < daftarTransaksi.size(); i++) {
            if (daftarTransaksi.get(i).getIdPelanggan().equalsIgnoreCase(idPelanggan)) { 
                System.out.print("- ");
                daftarTransaksi.get(i).tampilkanStruk();
                ditemukan = true;
            }
        }
        if (!ditemukan) {
            throw new TransaksiTidakDitemukanException("Data transaksi untuk UID '" + idPelanggan + "' tidak ditemukan di sistem.");
        }
    }

    public static void eksekusiTransaksi(Transaksi pesanan) {
        pesanan.instruksiProses(); 
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Transaksi> daftarTransaksi = new ArrayList<>();
        HashMap<String, String> dataPelanggan = new HashMap<>();
        
        dataPelanggan.put("102938475", "JeJuy");
        dataPelanggan.put("98765432", "Sultan Gantenk");
        dataPelanggan.put("OrangGanteng", "OTWIMO100");

        try {
            daftarTransaksi.add(new TopUpGame("Genshin", "102938475", 300, 300 * 1500.0, "8012", "Asia"));
            daftarTransaksi.add(new TopUpGame("MLBB", "98765432", 1000, 1000 * 1500.0, "2021", "Global"));
            daftarTransaksi.add(new Voucher("Roblox", "OrangGanteng", 500, 500 * 1500.0));
            daftarTransaksi.add(new LanggananPremium("Genshin", "Plismenangrateoff", 1, 1 * 79000.0, 30));
        } catch (Exception e) {
            System.out.println("Gagal memuat data awal: " + e.getMessage());
        }
        
        boolean isRunning = true;
        System.out.println("==========================================");
        System.out.println("     WELCOME TO LEFELUP TOP-UP STORE      ");
        System.out.println("==========================================");            
        
        while(isRunning) {
            System.out.println("\n--- MENU UTAMA ---");
            System.out.println("1. Tambah Transaksi Top-Up");
            System.out.println("2. Tampilkan Seluruh Data Pesanan");
            System.out.println("3. Cari Riwayat Pesanan (Custom Exception)");
            System.out.println("4. Manajemen Pelanggan (Fitur HashMap)");
            System.out.println("5. Simulasi Pesanan");
            System.out.println("6. Keluar");
            System.out.print("Pilih opsi (1-6): ");
            
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
                            daftarTransaksi.add(new TopUpGame(game, uid, nominal, harga, idServer, namaServer));
                        } else if (jenisLayanan == 2) {
                            daftarTransaksi.add(new Voucher(game, uid, nominal, harga));
                        } else if (jenisLayanan == 3) {
                            System.out.print("Masukkan Durasi Aktif (Hari)  : ");
                            int durasi = scanner.nextInt();
                            scanner.nextLine();
                            daftarTransaksi.add(new LanggananPremium(game, uid, nominal, harga, durasi));
                        } else {
                            System.out.println("Jenis layanan tidak valid!");
                        }
                        System.out.println(">> Sukses: Transaksi berhasil ditambahkan! Total antrian: " + daftarTransaksi.size());
                    }
                    case 2 -> {
                        System.out.println("\n=================== DAFTAR TRANSAKSI ===================");
                        if (daftarTransaksi.isEmpty()) {
                            System.out.println("Belum ada data transaksi tersimpan.");
                        } else {
                            for (int i = 0; i < daftarTransaksi.size(); i++) {
                                System.out.print((i + 1) + ". ");
                                daftarTransaksi.get(i).tampilkanStruk();
                            }
                        }
                    }
                    case 3 -> {
                        System.out.println("\n-- Fitur Cari Pesanan (Custom Exception) --");
                        System.out.print("Masukkan UID Pelanggan yang dicari: ");
                        String kataKunci = scanner.nextLine();
                        
                        try {
                            cariPesanan(kataKunci, daftarTransaksi);
                        } catch (TransaksiTidakDitemukanException e) {
                            System.out.println("[PERINGATAN] " + e.getMessage());
                        }
                    }
                    case 4 -> {
                        System.out.println("\n-- Manajemen Pelanggan (HashMap) --");
                        System.out.println("1. Tambah Data Pelanggan Baru");
                        System.out.println("2. Cari Pelanggan via UID");
                        System.out.println("3. Tampilkan Semua Pelanggan");
                        System.out.print("Pilih menu: ");
                        int menuPelanggan = scanner.nextInt();
                        scanner.nextLine();

                        if (menuPelanggan == 1) {
                            System.out.print("Masukkan UID (sebagai Key): ");
                            String uid = scanner.nextLine();
                            System.out.print("Masukkan Nama Lengkap (sebagai Value): ");
                            String nama = scanner.nextLine();
                            dataPelanggan.put(uid, nama);
                            System.out.println("Berhasil menambahkan pelanggan!");
                        } else if (menuPelanggan == 2) {
                            System.out.print("Masukkan UID yang dicari: ");
                            String searchUid = scanner.nextLine();
                            if (dataPelanggan.containsKey(searchUid)) {
                                System.out.println("Ditemukan! Nama Pelanggan: " + dataPelanggan.get(searchUid));
                            } else {
                                System.out.println("Pelanggan dengan UID tersebut tidak terdaftar.");
                            }
                        } else if (menuPelanggan == 3) {
                            System.out.println("\nTotal Pelanggan Terdaftar: " + dataPelanggan.size());
                            for (String keyUid : dataPelanggan.keySet()) {
                                System.out.println("- UID: " + keyUid + " | Nama: " + dataPelanggan.get(keyUid));
                            }
                        }
                    }
                    case 5 -> {
                        System.out.println("\n============= SIMULASI PESANAN =============");
                        if (daftarTransaksi.isEmpty()) {
                            System.out.println("Belum ada pesanan untuk diproses.");
                        } else {
                            for (int i = 0; i < daftarTransaksi.size(); i++) {
                                System.out.println("\n=== Antrian " + (i+1) + " | Pesanan " + daftarTransaksi.get(i).getNamaGame() + " ===");
                                eksekusiTransaksi(daftarTransaksi.get(i)); 
                                
                                if (daftarTransaksi.get(i) instanceof DapatDibatalkan itemBatal) {
                                    itemBatal.prosesPembatalan();
                                }
                                if (daftarTransaksi.get(i) instanceof DapatDiklaim itemKlaim) {
                                    itemKlaim.caraKlaim();
                                }
                            }
                        }
                    }
                    case 6 -> {
                        System.out.println("Terima kasih! Balik lagi yah.");
                        isRunning = false;
                    }
                    default -> System.out.println("Opsi menu tidak valid (Pilih 1-6).");
                }
            } catch (InputMismatchException e) {
                System.out.println("[ERROR INPUT] Tipe data tidak valid! Harap masukkan format angka.");
                scanner.nextLine(); 
            } catch (Exception e) {
                System.out.println("[SISTEM ERROR] Terjadi kesalahan: " + e.getMessage());
            } finally {
                System.out.println("-> Sesi interaksi menu selesai dieksekusi.");
            }
        }
        scanner.close();
    }
}