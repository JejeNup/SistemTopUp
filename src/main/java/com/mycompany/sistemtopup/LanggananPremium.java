package com.mycompany.sistemtopup;

public class LanggananPremium extends Transaksi implements DapatDiklaim {
    private int durasiHari;
    
    public LanggananPremium(String namaGame, String idPelanggan, int nominalTopUp, double totalHarga, int durasiHari) throws Exception {
        super(namaGame, idPelanggan, nominalTopUp, totalHarga);
        this.setDurasiHari(durasiHari);
    }
    
    public void setDurasiHari(int durasiHari) { this.durasiHari = (durasiHari > 0) ? durasiHari : 30; }

    @Override
    public void tampilkanStruk() {
        System.out.print("[Membership]  ");
        super.tampilkanStruk();
        System.out.printf(" | Durasi: %d Hari\n", this.durasiHari);
    }

    @Override
    public void instruksiProses() { System.out.println("-> Proses: Mengaktifkan langganan."); }
    @Override
    public void caraKlaim() { System.out.println("-> [INFO KLAIM]: Login ulang akun."); }
}