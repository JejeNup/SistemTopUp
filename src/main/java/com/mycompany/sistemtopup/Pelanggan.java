package com.mycompany.sistemtopup;

import java.io.Serializable;

public class Pelanggan implements Serializable {
    private static final long serialVersionUID = 1L;
    private String uid;
    private String nama;

    public Pelanggan(String uid, String nama) {
        this.uid = uid;
        this.nama = nama;
    }

    public String getUid() { return uid; }
    public String getNama() { return nama; }
}