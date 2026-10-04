package com.mycompany.sistemtopup;

public class Transaksi {
    protected String namaGame;
    protected String idPelanggan;
    protected int nominalTopUp; 
    protected double totalHarga;
    
    public static int totalTransaksiBerhasil = 0;

    public Transaksi(String namaGame, String idPelanggan, int nominalTopUp, double totalHarga) {
        this.namaGame = namaGame;
        this.idPelanggan = idPelanggan;
        this.nominalTopUp = nominalTopUp;
        this.totalHarga = totalHarga;
        
        totalTransaksiBerhasil++;
    }

    public String getIdPelanggan() { return this.idPelanggan; }
    public int getNominalTopUp() { return this.nominalTopUp; }

    public void tampilkanStruk() {
        System.out.printf("Game: %-15s | UID: %-12s | Item: %-5d | Harga: Rp%.0f\n",
                this.namaGame, this.idPelanggan, this.nominalTopUp, this.totalHarga);
    }

    public void instruksiProses() {
        System.out.println("-> Proses: Mengirim pesanan ke akun pelanggan secara default.");
    }
}