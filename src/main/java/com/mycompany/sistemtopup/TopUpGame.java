package com.mycompany.sistemtopup;

public class TopUpGame extends Transaksi {
    private String idServer;
    private String namaServer;
    
    public TopUpGame(String namaGame, String idPelanggan, int nominalTopUp, double totalHarga, String idServer, String namaServer) {
        super(namaGame, idPelanggan, nominalTopUp, totalHarga);
        this.idServer = idServer;
        this.namaServer = namaServer;
    }
    
    @Override
    public void tampilkanStruk() {
        System.out.printf("[Game]      Game: %-10s | UID: %-10s | ID Server: %-6s | Server: %-8s | Item: %-5d | Harga: Rp%.0f\n", 
                          this.namaGame, this.idPelanggan, this.idServer, this.namaServer, this.nominalTopUp, this.totalHarga);
    }
    
    @Override
    public void instruksiProses() {
        System.out.println("-> Sedang Proses pada akun  " + this.idPelanggan + " (" + this.namaServer + ").");
    }
}