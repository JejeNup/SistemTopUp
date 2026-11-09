package com.mycompany.sistemtopup;

public class Pelanggan {
    private String uid;
    private String nama;

    public Pelanggan(String uid, String nama) {
        this.uid = uid;
        this.nama = nama;
    }

    public String getUid() { return uid; }
    public String getNama() { return nama; }
}