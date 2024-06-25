/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.controller;

import com.client.view.LihatLaporan;
import com.model.Database;
import com.model.User;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author hp
 */
public class LihatLaporanController {

    // Registration frame untuk show message dialog
    private LihatLaporan lp;
    private String id_pengirim;
    private User user;

    public LihatLaporanController(LihatLaporan lp, User user) {
        
        this.lp = lp;
        this.user = user;
        
        loadLaporanData();
        this.lp.getExportButton().addActionListener(e -> handleExport());
    }
    
    public void handleExport(){
        JFileChooser fileChooser =  lp.getFileChooser();
        
        fileChooser.setDialogTitle("Save as");

        // Set filter untuk file CSV
        FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV Files", "csv");
        fileChooser.setFileFilter(filter);

        int userSelection = fileChooser.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            String filePath = fileToSave.getAbsolutePath();

            // Tambahkan ekstensi .csv jika tidak ada
            if (!filePath.toLowerCase().endsWith(".csv")) {
                filePath += ".csv";
            }

            try (FileWriter csvWriter = new FileWriter(filePath)) {
                // Write header
                for (int col = 0; col < lp.getLaporanTable().getColumnCount(); col++) {
                    csvWriter.append(lp.getLaporanTable().getColumnName(col));
                    csvWriter.append(col == lp.getLaporanTable().getColumnCount() - 1 ? "\n" : ",");
                }

                // Write data rows
                for (int row = 0; row < lp.getLaporanTable().getRowCount(); row++) {
                    for (int col = 0; col < lp.getLaporanTable().getColumnCount(); col++) {
                        csvWriter.append(lp.getLaporanTable().getValueAt(row, col).toString());
                        csvWriter.append(col == lp.getLaporanTable().getColumnCount() - 1 ? "\n" : ",");
                    }
                }

                csvWriter.flush();
                JOptionPane.showMessageDialog(null, "Data berhasil diekspor ke " + filePath);

            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Terjadi kesalahan: " + e.getMessage());
            }
        }
    }
    
    public void loadLaporanData(){
        DefaultTableModel model = (DefaultTableModel) lp.getLaporanTable().getModel();
        model.setRowCount(0); // Clear existing data
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try{
            conn = Database.getInstance().getConnection();
            String sqlCheck = "SELECT id_user FROM users WHERE email = ?";
            pstmt = conn.prepareStatement(sqlCheck);
            pstmt.setString(1, user.getEmail());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                    id_pengirim = rs.getString("id_user");
                } else {
                    JOptionPane.showMessageDialog(lp, "User tidak ditemukan");               
                }
            
            conn.setAutoCommit(false);
            String sql = "SELECT id_laporan, judul, barang, lokasi, status, tanggal_dibuat, tanggapan.deskripsi AS tanggapan FROM laporan LEFT JOIN tanggapan ON laporan.id_laporan = tanggapan.laporan WHERE id_pengirim = ? ORDER BY tanggal_dibuat DESC";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id_pengirim);
             rs = pstmt.executeQuery();

            while (rs.next()) {
                String idLaporan = rs.getString("id_laporan");
                String judul = rs.getString("judul");
                String barang = rs.getString("barang");
                String lokasi = rs.getString("lokasi");
                String status = rs.getString("status");
                String tanggalDibuat = rs.getString("tanggal_dibuat");
                String tanggapan = rs.getString("tanggapan");
                
                if (tanggapan == null) {
                    tanggapan = "tidak ada tanggapan";
                }

                model.addRow(new Object[]{idLaporan, judul, barang, lokasi, status, tanggalDibuat,tanggapan});
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
                try {
                    if (pstmt != null) pstmt.close();
                    if (conn != null) conn.close();
                } catch (SQLException ex) {
                    System.err.println(ex);
                }
            }

    }
    
    
}
