package com.mycompany.sistemtopup;

import java.io.Serializable;

public abstract class Transaksi implements Serializable {
    private static final long serialVersionUID = 1L;
    private String namaGame;
    private String idPelanggan;
    private int nominalTopUp; 
    private double totalHarga;
    
    public static int totalTransaksiBerhasil = 0;

    public Transaksi(String namaGame, String idPelanggan, int nominalTopUp, double totalHarga) throws Exception {
        if (nominalTopUp <= 0) {
            throw new Exception("Nominal Top-up tidak boleh 0 atau negatif!");
        }
        if (totalHarga < 0) {
            throw new Exception("Total harga tidak boleh negatif!");
        }
        
        this.setNamaGame(namaGame);
        this.setIdPelanggan(idPelanggan);
        this.nominalTopUp = nominalTopUp; 
        this.totalHarga = totalHarga;     
        
        totalTransaksiBerhasil++;
    }

    public String getNamaGame() { return this.namaGame; }

    public void setNamaGame(String namaGame) {
        if (namaGame != null && !namaGame.trim().isEmpty()) {
            this.namaGame = namaGame;
        } else {
            this.namaGame = "Unknown Game";
        }
    }

    public String getIdPelanggan() { return this.idPelanggan; }

    public void setIdPelanggan(String idPelanggan) {
        if (idPelanggan != null && !idPelanggan.trim().isEmpty()) {
            this.idPelanggan = idPelanggan;
        } else {
            this.idPelanggan = "Guest";
        }
    }

    public int getNominalTopUp() { return this.nominalTopUp; }
    public double getTotalHarga() { return this.totalHarga; }

    public void tampilkanStruk() {
        System.out.printf("Game: %-12s | UID: %-10s | Item: %-5d | Harga: Rp%.0f",
                this.namaGame, this.idPelanggan, this.nominalTopUp, this.totalHarga);
    }

    public abstract void instruksiProses();
}