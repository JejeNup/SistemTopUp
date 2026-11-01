package com.mycompany.sistemtopup;
import java.util.Random;

public class Voucher extends Transaksi implements DapatDibatalkan, DapatDiklaim {
    private String kodeRedeem;
    
    public Voucher(String namaGame, String idPelanggan, int nominalTopUp, double totalHarga) throws Exception {
        super(namaGame, idPelanggan, nominalTopUp, totalHarga);
        this.kodeRedeem = generateKodeVoucher();
    }
    
    private String generateKodeVoucher() {
        String karakter = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder hasil = new StringBuilder();
        Random rnd = new Random();
        for (int i = 0; i < 12; i++) hasil.append(karakter.charAt(rnd.nextInt(karakter.length())));
        return hasil.toString();
    }
    
    @Override
    public void tampilkanStruk() {
        System.out.print("[Voucher]     ");
        super.tampilkanStruk(); 
        System.out.printf(" | Kode Redeem: %-12s\n", this.kodeRedeem);
    }
    
    @Override
    public void instruksiProses() { System.out.println("-> Proses: Kode Redeem (" + this.kodeRedeem + ") diterbitkan."); }
    @Override
    public void prosesPembatalan() { System.out.println("-> [INFO BATAL]: Transaksi voucher ditarik."); }
    @Override
    public void caraKlaim() { System.out.println("-> [INFO KLAIM]: Salin kode redeem dan klaim."); }
}