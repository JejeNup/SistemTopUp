package com.mycompany.sistemtopup;

public class ServerGateway {
    private String namaServer;
    private int kapasitasTraffic;

    public ServerGateway(String namaServer, int kapasitasTraffic) {
        this.namaServer = namaServer;
        this.kapasitasTraffic = kapasitasTraffic;
    }

    public void cekStatus() {
        System.out.println("-> [" + namaServer + "] Beroperasi Normal. Kapasitas Sistem: " + kapasitasTraffic + " req/sec.");
    }
}