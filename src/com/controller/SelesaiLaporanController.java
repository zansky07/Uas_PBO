/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.controller;

import com.admin.view.LaporanBaruPanel;
import com.admin.view.SelesaiLaporanPanel;
import com.model.Database;
import com.model.Laporan;
import com.model.Tanggapan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author hp
 */
public class SelesaiLaporanController {
    
    private SelesaiLaporanPanel slp;
    private Laporan laporan;

    public SelesaiLaporanController(SelesaiLaporanPanel slp, Laporan laporan) {
        this.slp = slp;
        this.laporan = laporan;
        loadData(laporan);
        
        this.slp.getSendButton().addActionListener(e -> handleReport(laporan));
    }
    
    public void handleReport(Laporan laporan){
        String tanggapan = slp.getTanggapanTextArea().getText();
        
        if (addTanggapan(tanggapan,laporan)) {
                JOptionPane.showMessageDialog(slp, "Laporan Berhasil Diproses!");
                
                    
                    // Lanjutkan ke langkah berikutnya
                    slp.setVisible(false);   
                    // Membuat dan menampilkan frame login
                    LaporanBaruPanel laporanBaruPanel = new LaporanBaruPanel();
                    laporanBaruPanel.setVisible(true);
                
            }else{
            JOptionPane.showMessageDialog(slp, "Laporan Gagal Diproses!");
        }
    }
    
    public boolean addTanggapan(String deskripsi, Laporan laporan){
        
            // Periksa apakah deskripsi null atau kosong
            if (deskripsi == null || deskripsi.trim().isEmpty()) {
                return false;
            }

            // Buat objek Tanggapan dan atur deskripsi
            Tanggapan tanggapan = new Tanggapan();
            tanggapan.setDeskripsi(deskripsi);
            tanggapan.setLaporan(laporan);
            Connection conn = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            try {
                // Check if tanggapan already exists
                conn = Database.getInstance().getConnection();
                String sqlCheck = "SELECT COUNT(*) FROM tanggapan WHERE laporan = ?";
                pstmt = conn.prepareStatement(sqlCheck);
                pstmt.setInt(1, tanggapan.getLaporan().getIdLaporan());
                rs = pstmt.executeQuery();
                if (rs.next() && rs.getInt(1) > 0) {
                    JOptionPane.showMessageDialog(slp, "Laporan Telah Diberi Tanggapan");
                    return false;
                }

                // Insert into tanggapan table
                conn.setAutoCommit(false);
                String sql1 = "INSERT INTO tanggapan(deskripsi, laporan) VALUES(?, ?)";
                pstmt = conn.prepareStatement(sql1, PreparedStatement.RETURN_GENERATED_KEYS);
                pstmt.setString(1, tanggapan.getDeskripsi());
                pstmt.setInt(2, tanggapan.getLaporan().getIdLaporan());
                pstmt.executeUpdate();
                
                conn.setAutoCommit(false);
                String sqlup = "UPDATE laporan SET status = 'selesai' WHERE id_laporan = ?";
                pstmt = conn.prepareStatement(sqlup, PreparedStatement.RETURN_GENERATED_KEYS);
                pstmt.setInt(1, tanggapan.getLaporan().getIdLaporan());
                pstmt.executeUpdate();
                
                // Commit transaction
                conn.commit();
                return true;
                
                } catch (SQLException e) {
                e.printStackTrace();
                if (conn != null) {
                    try {
                        conn.rollback();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
                return false;
            } finally {
                try {
                    if (rs != null) rs.close();
                    if (pstmt != null) pstmt.close();
                    if (conn != null) conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        
        
        
    }
    
    public void loadData(Laporan laporan){
            Connection conn = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            try {
                conn = Database.getInstance().getConnection();
                String sql = "SELECT judul, barang, lokasi, deskripsi FROM laporan WHERE id_laporan = ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, laporan.getIdLaporan());

                rs = pstmt.executeQuery();

            while (rs.next()) {
                
                String judul = rs.getString("judul");
                String barang = rs.getString("barang");
                String lokasi = rs.getString("lokasi");
                String deskripsi = rs.getString("deskripsi");
                
                slp.getIdLaporanTextField().setText(String.valueOf(laporan.getIdLaporan()));
                slp.getJudulTextField().setText(judul);
                slp.getBarangTextField().setText(barang);
                slp.getLokasiTextField().setText(lokasi);
                slp.getDeskripsiTextArea().setText(deskripsi);
                
                slp.getIdLaporanTextField().setEnabled(false);
                slp.getJudulTextField().setEnabled(false);
                slp.getBarangTextField().setEnabled(false);
                slp.getLokasiTextField().setEnabled(false);
                slp.getDeskripsiTextArea().setEnabled(false);

               
            }
                
            } catch (SQLException ex) {
                System.err.println(ex);
            } finally {
                try {
                    if (pstmt != null) pstmt.close();
                    if (conn != null) conn.close();
                } catch (SQLException ex) {
                    System.err.println(ex);
                }
            }
    }
    
    
    
}
