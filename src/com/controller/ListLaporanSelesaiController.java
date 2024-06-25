/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.controller;

import com.admin.view.LaporanSelesaiPanel;
import com.model.Database;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author hp
 */
public class ListLaporanSelesaiController {
    private LaporanSelesaiPanel lsp;

    public ListLaporanSelesaiController(LaporanSelesaiPanel lsp) {
        this.lsp = lsp;
        loadTableData();
        
        this.lsp.getDeleteButton().addActionListener(e -> handleDelete());
        this.lsp.getExportButton().addActionListener(e -> handleExport());
    }
    
    public void handleDelete(){
            int selectedRow = lsp.getLaporanTable().getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(lsp, "Pilih baris yang ingin dihapus", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

            String id_user = (String) lsp.getLaporanTable().getValueAt(selectedRow, 0);
                if (id_user == null) {
                    JOptionPane.showMessageDialog(lsp, "ID laporan tidak valid", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

            Connection conn = null;
            PreparedStatement pstmt = null;

            try {
                conn = Database.getInstance().getConnection();
                String sql = "DELETE FROM laporan WHERE id_laporan = ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, id_user);

                int affectedRows = pstmt.executeUpdate();
                if (affectedRows > 0) {
                    JOptionPane.showMessageDialog(lsp, "Data berhasil dihapus");
                } else {
                    JOptionPane.showMessageDialog(lsp, "Data tidak ditemukan");
                }
                loadTableData();
            } catch (SQLException ex) {
                System.err.println(ex);
                JOptionPane.showMessageDialog(lsp, "Gagal menghapus data", "Error", JOptionPane.ERROR_MESSAGE);
            } finally {
                try {
                    if (pstmt != null) pstmt.close();
                    if (conn != null) conn.close();
                } catch (SQLException ex) {
                    System.err.println(ex);
                }
            }
    }
    
    public void handleExport(){
        JFileChooser fileChooser =  lsp.getFileChooser();
        
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
                for (int col = 0; col < lsp.getLaporanTable().getColumnCount(); col++) {
                    csvWriter.append(lsp.getLaporanTable().getColumnName(col));
                    csvWriter.append(col == lsp.getLaporanTable().getColumnCount() - 1 ? "\n" : ",");
                }

                // Write data rows
                for (int row = 0; row < lsp.getLaporanTable().getRowCount(); row++) {
                    for (int col = 0; col < lsp.getLaporanTable().getColumnCount(); col++) {
                        csvWriter.append(lsp.getLaporanTable().getValueAt(row, col).toString());
                        csvWriter.append(col == lsp.getLaporanTable().getColumnCount() - 1 ? "\n" : ",");
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
    
    public void loadTableData(){
        DefaultTableModel model = (DefaultTableModel) lsp.getLaporanTable().getModel();
        model.setRowCount(0); // Clear existing data
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try{
            conn = Database.getInstance().getConnection();
            String sql = "SELECT id_laporan, judul, barang, lokasi, tanggal_dibuat, users.nama AS pengirim, tanggapan.deskripsi AS tanggapan FROM laporan JOIN users ON laporan.id_pengirim = users.id_user JOIN tanggapan ON laporan.id_laporan = tanggapan.laporan WHERE status = 'selesai'";
            pstmt = conn.prepareStatement(sql);
             rs = pstmt.executeQuery();

            while (rs.next()) {
                String idLaporan = rs.getString("id_laporan");
                String judul = rs.getString("judul");
                String barang = rs.getString("barang");
                String lokasi = rs.getString("lokasi");
                String tanggalDibuat = rs.getString("tanggal_dibuat");
                String pengirim = rs.getString("pengirim");
                String tanggapan = rs.getString("tanggapan");

                model.addRow(new Object[]{idLaporan, judul, barang, lokasi, pengirim, tanggalDibuat,tanggapan});
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
