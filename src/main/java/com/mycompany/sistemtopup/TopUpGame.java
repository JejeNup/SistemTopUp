package com.mycompany.sistemtopup;

public class TopUpGame extends Transaksi implements DapatDibatalkan {
    private String idServer;
    private String namaServer;
    
    public TopUpGame(String namaGame, String idPelanggan, int nominalTopUp, double totalHarga, String idServer, String namaServer) throws Exception {
        super(namaGame, idPelanggan, nominalTopUp, totalHarga);
        this.setIdServer(idServer);
        this.setNamaServer(namaServer);
    }
    
    public String getIdServer() { return this.idServer; }
    public void setIdServer(String idServer) { this.idServer = (idServer != null && !idServer.trim().isEmpty()) ? idServer : "-"; }
    public String getNamaServer() { return this.namaServer; }
    public void setNamaServer(String namaServer) { this.namaServer = (namaServer != null && !namaServer.trim().isEmpty()) ? namaServer : "Global"; }
    
    @Override
    public void tampilkanStruk() {
        System.out.print("[Game Direct] ");
        super.tampilkanStruk(); 
        System.out.printf(" | Server ID: %-5s | Region: %-8s\n", this.idServer, this.namaServer);
    }
    
    @Override
    public void instruksiProses() { System.out.println("-> Proses: Pesanan dikirim langsung pada akun " + getIdPelanggan() + "."); }

    @Override
    public void prosesPembatalan() { System.out.println("-> [INFO BATAL]: Top-up game dibatalkan."); }
}