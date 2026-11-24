package com.mycompany.sistemtopup;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class LefelupStore {
    private String namaToko;
    private ServerGateway serverUtama;
    private ArrayList<Transaksi> daftarTransaksi;
    private HashMap<String, Pelanggan> dataPelanggan;

    public LefelupStore(String namaToko) {
        this.namaToko = namaToko;
        this.serverUtama = new ServerGateway("Gateway Pembayaran Lefelup", 5000);
        this.daftarTransaksi = new ArrayList<>();
        this.dataPelanggan = new HashMap<>();
    }

    public void bukaToko() {
        System.out.println("\n*** Menginisialisasi sistem " + this.namaToko + " ***");
        serverUtama.cekStatus();
    }

    public void simpanDataKeFile() {
        try {
            FileOutputStream fileKeluar = new FileOutputStream("data_lefelup.dat");
            ObjectOutputStream mesinPembeku = new ObjectOutputStream(fileKeluar);

            mesinPembeku.writeObject(this.daftarTransaksi);
            mesinPembeku.writeObject(this.dataPelanggan);
            mesinPembeku.close();
            fileKeluar.close();
            System.out.println("-> [SYSTEM LOG] Seluruh data transaksi & pelanggan berhasil disimpan (Serialization) ke data_lefelup.dat!");
        } catch (Exception e) {
            System.out.println("-> [SYSTEM ERROR] Gagal menyimpan data: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void muatDataDariFile() {
        try {
            File cekFile = new File("data_lefelup.dat");
            if (!cekFile.exists()) {
                System.out.println("-> [SYSTEM LOG] File penyimpanan belum ditemukan. Memuat data default awal...");
                return;
            }

            FileInputStream fileMasuk = new FileInputStream(cekFile);
            ObjectInputStream mesinPembangkit = new ObjectInputStream(fileMasuk);
            this.daftarTransaksi = (ArrayList<Transaksi>) mesinPembangkit.readObject();
            this.dataPelanggan = (HashMap<String, Pelanggan>) mesinPembangkit.readObject();
            Transaksi.totalTransaksiBerhasil = this.daftarTransaksi.size();
            mesinPembangkit.close();
            fileMasuk.close();
            System.out.println("-> [SYSTEM LOG] Data berhasil dimuat ulang (Deserialization) dari data_lefelup.dat! ("
                    + this.daftarTransaksi.size() + " transaksi, " + this.dataPelanggan.size() + " pelanggan)");
        } catch (Exception e) {
            System.out.println("-> [SYSTEM ERROR] Gagal memuat data: " + e.getMessage());
        }
    }

    public void bacaLogTransaksi() {
        System.out.println("\n--- Riwayat Log Transaksi (log_transaksi.txt) ---");
        try {
            File fileLog = new File("log_transaksi.txt");
            if (!fileLog.exists()) {
                System.out.println("Belum ada catatan log transaksi di file.");
                return;
            }
            Scanner mesinBaca = new Scanner(fileLog);
            while (mesinBaca.hasNextLine()) {
                System.out.println(mesinBaca.nextLine());
            }
            mesinBaca.close();
        } catch (FileNotFoundException e) {
            System.out.println("Gagal membuka file log: " + e.getMessage());
        }
    }

    public boolean isDataKosong() {
        return daftarTransaksi.isEmpty() && dataPelanggan.isEmpty();
    }

    public void tambahTransaksi(Transaksi t) {
        daftarTransaksi.add(t);
        System.out.println("Sukses! Transaksi ditambahkan. (Total Antrian: " + daftarTransaksi.size() + ")");
    }

    public void tambahPelanggan(Pelanggan p) {
        dataPelanggan.put(p.getUid(), p);
        System.out.println("Berhasil menambahkan pelanggan!");
    }

    public void lihatSemuaTransaksi() {
        if (daftarTransaksi.isEmpty()) {
            System.out.println("Belum ada data transaksi tersimpan.");
            return;
        }
        for (int i = 0; i < daftarTransaksi.size(); i++) {
            System.out.print((i + 1) + ". ");
            daftarTransaksi.get(i).tampilkanStruk();
        }
    }

    public void cariTransaksi(String uid) throws TransaksiTidakDitemukanException {
        System.out.println("Mencari Transaksi dengan UID: " + uid);
        boolean ditemukan = false;
        for (Transaksi t : daftarTransaksi) {
            if (t.getIdPelanggan().equalsIgnoreCase(uid)) {
                System.out.print("- ");
                t.tampilkanStruk();
                ditemukan = true;
            }
        }
        if (!ditemukan) {
            throw new TransaksiTidakDitemukanException("Gagal: Transaksi untuk UID '" + uid + "' tidak terdaftar di sistem.");
        }
    }

    public void cariTransaksi(int minimalItem) throws TransaksiTidakDitemukanException {
        System.out.println("Mencari Transaksi dengan Minimal Item/Kuantitas: " + minimalItem);
        boolean ditemukan = false;
        for (Transaksi t : daftarTransaksi) {
            if (t.getNominalTopUp() >= minimalItem) {
                System.out.print("- ");
                t.tampilkanStruk();
                ditemukan = true;
            }
        }
        if (!ditemukan) {
            throw new TransaksiTidakDitemukanException("Gagal: Transaksi dengan minimal nominal " + minimalItem + " tidak ditemukan.");
        }
    }

    public void cariPelanggan(String uid) {
        if (dataPelanggan.containsKey(uid)) {
            System.out.println("Ditemukan! Nama: " + dataPelanggan.get(uid).getNama());
        } else {
            System.out.println("Pelanggan dengan UID tersebut tidak terdaftar.");
        }
    }

    public void lihatSemuaPelanggan() {
        System.out.println("Total Pelanggan Terdaftar: " + dataPelanggan.size());
        for (Pelanggan p : dataPelanggan.values()) {
            System.out.println("- UID: " + p.getUid() + " | Nama: " + p.getNama());
        }
    }

    public Pelanggan getPelanggan(String uid) { return dataPelanggan.get(uid); }
    public Transaksi getTransaksiInt(int index) { return daftarTransaksi.get(index); }
    public int getJumlahTransaksi() { return daftarTransaksi.size(); }
}