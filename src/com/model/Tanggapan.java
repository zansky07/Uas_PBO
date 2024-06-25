/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.model;

/**
 *
 * @author hp
 */
public class Tanggapan {
    private int idTanggapan;
    private String deskripsi;
    private Laporan laporan;

    public Tanggapan(int idTanggapan, String deskripsi, Laporan laporan) {
        this.idTanggapan = idTanggapan;
        this.deskripsi = deskripsi;
        this.laporan = laporan;
    }

    public Tanggapan() {
        deskripsi = null;
    }

    public int getIdTanggapan() {
        return idTanggapan;
    }

    public void setIdTanggapan(int idTanggapan) {
        this.idTanggapan = idTanggapan;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public Laporan getLaporan() {
        return laporan;
    }

    public void setLaporan(Laporan laporan) {
        this.laporan = laporan;
    }

    
    
    
}
