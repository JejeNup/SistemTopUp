package com.mycompany.sistemtopup;

public class Transaksi {
    private String namaGame;
    private String idPelanggan;
    private int nominalTopUp; 
    private double totalHarga;
    
    public static int totalTransaksiBerhasil = 0;

    public Transaksi(String namaGame, String idPelanggan, int nominalTopUp, double totalHarga) {
        this.setNamaGame(namaGame);
        this.setIdPelanggan(idPelanggan);
        this.setNominalTopUp(nominalTopUp); 
        this.setTotalHarga(totalHarga);     
        
        totalTransaksiBerhasil++;
    }

    public String getNamaGame() {
        return this.namaGame;
    }

    public void setNamaGame(String namaGame) {
        if (namaGame != null && !namaGame.trim().isEmpty()) {
            this.namaGame = namaGame;
        } else {
            System.out.println("[Validasi Error] Nama game tidak boleh kosong!");
            this.namaGame = "Unknown Game";
        }
    }

    public String getIdPelanggan() { 
        return this.idPelanggan; 
    }

    public void setIdPelanggan(String idPelanggan) {
        if (idPelanggan != null && !idPelanggan.trim().isEmpty()) {
            this.idPelanggan = idPelanggan;
        } else {
            System.out.println("[Validasi Error] ID Pelanggan tidak boleh kosong!");
            this.idPelanggan = "Guest";
        }
    }

    public int getNominalTopUp() { 
        return this.nominalTopUp; 
    }

    public void setNominalTopUp(int nominalTopUp) {
        if (nominalTopUp > 0) {
            this.nominalTopUp = nominalTopUp;
        } else {
            System.out.println("[Validasi Error] Nominal top-up harus lebih dari 0!");
            this.nominalTopUp = 1;
        }
    }

    public double getTotalHarga() { 
        return this.totalHarga; 
    }

    public void setTotalHarga(double totalHarga) {
        if (totalHarga >= 0) {
            this.totalHarga = totalHarga;
        } else {
            System.out.println("[Validasi Error] Total harga tidak boleh negatif!");
            this.totalHarga = 0.0;
        }
    }

    public void tampilkanStruk() {
        System.out.printf("Game: %-12s | UID: %-10s | Item: %-5d | Harga: Rp%.0f",
                this.namaGame, this.idPelanggan, this.nominalTopUp, this.totalHarga);
    }

    public void instruksiProses() {
        System.out.println("-> Proses: Mengirim pesanan ke akun pelanggan secara default.");
    }
}