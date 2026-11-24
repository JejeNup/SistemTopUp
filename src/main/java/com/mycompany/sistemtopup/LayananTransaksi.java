package com.mycompany.sistemtopup;
import java.io.FileWriter;
import java.io.IOException;

public class LayananTransaksi {
    public static void prosesValidasi(Pelanggan pelanggan, Transaksi transaksi) {
        System.out.println("\n===== STRUK VALIDASI =====");
        System.out.println("Pelanggan : " + pelanggan.getNama() + " (" + pelanggan.getUid() + ")");
        System.out.println("Item      : " + transaksi.getNamaGame() + " (Nominal: " + transaksi.getNominalTopUp() + ")");
        System.out.println("Status    : Validasi sukses. Dicatat oleh sistem.");
        
        transaksi.instruksiProses();
        System.out.println("==========================");

        try {
            FileWriter pipaTulis = new FileWriter("log_transaksi.txt", true);
            String log = "Pelanggan: " + pelanggan.getNama() + " (" + pelanggan.getUid() + ")"
                       + " | Game: " + transaksi.getNamaGame()
                       + " | Item: " + transaksi.getNominalTopUp()
                       + " | Harga: Rp" + (long) transaksi.getTotalHarga() + "\n";
            pipaTulis.write(log);
            pipaTulis.close();
            System.out.println("-> [FILE LOG] Riwayat validasi berhasil dicatat ke log_transaksi.txt");
        } catch (IOException e) {
            System.out.println("-> [PERINGATAN] Gagal menulis log transaksi ke file: " + e.getMessage());
        }
    }
}