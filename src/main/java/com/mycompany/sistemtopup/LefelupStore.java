package com.mycompany.sistemtopup;

import java.util.ArrayList;
import java.util.HashMap;

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