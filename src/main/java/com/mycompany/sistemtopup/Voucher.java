package com.mycompany.sistemtopup;

import java.util.Random;

public class Voucher extends Transaksi {
    private String kodeRedeem;
    
    public Voucher(String namaGame, String idPelanggan, int nominalTopUp, double totalHarga) {
        super(namaGame, idPelanggan, nominalTopUp, totalHarga);
        this.kodeRedeem = generateKodeVoucher();
    }

    public String getKodeRedeem() {
        return this.kodeRedeem;
    }

    public void setKodeRedeem(String kodeRedeem) {
        if (kodeRedeem != null && !kodeRedeem.trim().isEmpty()) {
            this.kodeRedeem = kodeRedeem;
        } else {
            this.kodeRedeem = generateKodeVoucher();
        }
    }
    
    private String generateKodeVoucher() {
        String karakter = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder hasil = new StringBuilder();
        Random rnd = new Random();
        for (int i = 0; i < 12; i++) { 
            hasil.append(karakter.charAt(rnd.nextInt(karakter.length())));
        }
        return hasil.toString();
    }
    
    @Override
    public void tampilkanStruk() {
        System.out.print("[Voucher]     ");
        super.tampilkanStruk(); 
        System.out.printf(" | Kode Redeem: %-12s\n", this.kodeRedeem);
    }
    
    @Override
    public void instruksiProses() {
        System.out.println("-> Proses: Kode Redeem (" + this.kodeRedeem + ") telah terbit. Silakan klaim di situs resmi.");
    }
}