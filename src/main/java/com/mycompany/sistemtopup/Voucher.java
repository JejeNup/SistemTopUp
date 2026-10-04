package com.mycompany.sistemtopup;

import java.util.Random; 

public class Voucher extends Transaksi {
    private String kodeRedeem;
    
    public Voucher(String namaGame, String idPelanggan, int nominalTopUp, double totalHarga) {
        super(namaGame, idPelanggan, nominalTopUp, totalHarga);
        this.kodeRedeem = generateKodeVoucher(); 
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
        System.out.printf("[Voucher]   Platform: %-6s | Akun: %-9s | Kode: %-12s | Item: %-5d | Harga: Rp%.0f\n", 
                          this.namaGame, this.idPelanggan, this.kodeRedeem, this.nominalTopUp, this.totalHarga);
    }
    
    @Override
    public void instruksiProses() {
        System.out.println("-> Proses: Transaksi berhasil! JANGAN berikan kode redeem (" + this.kodeRedeem + ") kepada siapa pun.");
    }
}