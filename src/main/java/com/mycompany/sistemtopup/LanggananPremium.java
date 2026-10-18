package com.mycompany.sistemtopup;

public class LanggananPremium extends Transaksi implements DapatDiklaim {
    private int durasiHari;

    public LanggananPremium(String namaGame, String idPelanggan, int nominalTopUp, double totalHarga, int durasiHari) {
        super(namaGame, idPelanggan, nominalTopUp, totalHarga);
        this.setDurasiHari(durasiHari);
    }

    public int getDurasiHari() { return this.durasiHari; }

    public void setDurasiHari(int durasiHari) {
        if (durasiHari > 0) {
            this.durasiHari = durasiHari;
        } else {
            System.out.println("Durasi hari minimal 1, ditetapkan ke 30 hari.");
            this.durasiHari = 30;
        }
    }

    @Override
    public void tampilkanStruk() {
        System.out.print("[Membership]  ");
        super.tampilkanStruk();
        System.out.printf(" | Durasi: %d Hari\n", this.durasiHari);
    }

    @Override
    public void instruksiProses() {
        System.out.println("-> Proses: Mengaktifkan langganan bulanan selama " + this.durasiHari + " hari pada akun " + getIdPelanggan() + ".");
    }

    @Override
    public void caraKlaim() {
        System.out.println("-> [INFO KLAIM]: Login ulang atau relog in-game untuk merefresh sisa durasi premium.");
    }
}