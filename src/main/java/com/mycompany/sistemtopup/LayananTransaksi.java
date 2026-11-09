package com.mycompany.sistemtopup;

public class LayananTransaksi {
    public static void prosesValidasi(Pelanggan pelanggan, Transaksi transaksi) {
        System.out.println("\n===== STRUK VALIDASI =====");
        System.out.println("Pelanggan : " + pelanggan.getNama() + " (" + pelanggan.getUid() + ")");
        System.out.println("Item      : " + transaksi.getNamaGame() + " (Nominal: " + transaksi.getNominalTopUp() + ")");
        System.out.println("Status    : Validasi sukses. Dicatat oleh sistem.");
        
        transaksi.instruksiProses();
        System.out.println("==========================");
    }
}