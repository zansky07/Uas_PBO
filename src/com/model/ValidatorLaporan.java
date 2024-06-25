/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.model;

/**
 *
 * @author hp
 */
public class ValidatorLaporan implements Validator{
    public Laporan laporan;

    public ValidatorLaporan(Laporan laporan) {
        this.laporan = laporan;
    }
    
    @Override
    public boolean validate() throws ValidatorException {
        // Lakukan validasi untuk semua atribut laporan
        // Validasi untuk judul
        if (!isValidString(laporan.getJudulLaporan()) || laporan.getJudulLaporan().length() > 50) {
            throw new ValidatorException("Judul Laporan tidak boleh kosong dan tidak boleh lebih dari 50 karakter");
        }
        
        // Validasi untuk barang
        if (!isValidString(laporan.getBarang()) || laporan.getBarang().length() > 50) {
            throw new ValidatorException("Barang tidak boleh kosong dan tidak boleh lebih dari 50 karakter");
        }

        // Validasi untuk lokasi
        if (!isValidString(laporan.getLokasi()) || laporan.getLokasi().length() > 50) {
            throw new ValidatorException("Lokasi tidak boleh kosong dan tidak boleh lebih dari 50 karakter");
        }

        // Validasi untuk deskripsi
        if (!isValidString(laporan.getDeskripsi()) || laporan.getDeskripsi().length() > 255) {
            throw new ValidatorException("Deskripsi tidak boleh kosong dan tidak boleh lebih dari 255 karakter");
        }
      
        
      
        
        // Jika semua validasi berhasil, kembalikan true
        return true;
    }
    
    // Metode bantuan untuk memeriksa apakah string tidak kosong
    protected boolean isValidString(String str) {
        return str != null && !str.trim().isEmpty();
    }
}
