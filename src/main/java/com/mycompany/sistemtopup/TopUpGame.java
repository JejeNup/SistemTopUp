package com.mycompany.sistemtopup;

public class TopUpGame extends Transaksi implements DapatDibatalkan {
    private String idServer;
    private String namaServer;
    
    public TopUpGame(String namaGame, String idPelanggan, int nominalTopUp, double totalHarga, String idServer, String namaServer) {
        super(namaGame, idPelanggan, nominalTopUp, totalHarga);
        this.setIdServer(idServer);
        this.setNamaServer(namaServer);
    }

    public String getIdServer() { return this.idServer; }

    public void setIdServer(String idServer) {
        if (idServer != null && !idServer.trim().isEmpty()) {
            this.idServer = idServer;
        } else {
            this.idServer = "-";
        }
    }

    public String getNamaServer() { return this.namaServer; }

    public void setNamaServer(String namaServer) {
        if (namaServer != null && !namaServer.trim().isEmpty()) {
            this.namaServer = namaServer;
        } else {
            this.namaServer = "Global";
        }
    }
    
    @Override
    public void tampilkanStruk() {
        System.out.print("[Game Direct] ");
        super.tampilkanStruk(); 
        System.out.printf(" | Server ID: %-5s | Region: %-8s\n", this.idServer, this.namaServer);
    }
    
    @Override
    public void instruksiProses() {
        System.out.println("-> Proses: Pesanan dikirim langsung pada akun " + getIdPelanggan() + " (Server: " + this.namaServer + ").");
    }

    @Override
    public void prosesPembatalan() {
        System.out.println("-> [INFO BATAL]: Top-up game dibatalkan, dana dikembalikan ke e-wallet pelanggan.");
    }
}