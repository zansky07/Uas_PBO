/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.controller;

import com.admin.view.LaporanBaruPanel;
import com.admin.view.SelesaiLaporanPanel;
import com.admin.view.TolakLaporanPanel;
import com.model.Database;
import com.model.Laporan;
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
public class ListLaporanBaruController {
    
    private LaporanBaruPanel lbp;

    public ListLaporanBaruController(LaporanBaruPanel lbp) {
        this.lbp = lbp;
        
        loadTableData();
        this.lbp.getExportButton().addActionListener(e -> handleExport());
        this.lbp.getProsesButton().addActionListener(e -> handleProses());
        this.lbp.getTolakButton().addActionListener(e -> handleTolak());
    }
    
    public void handleTolak(){
        int selectedRow = lbp.getLaporanTable().getSelectedRow();
                    if (selectedRow == -1) {
                        JOptionPane.showMessageDialog(lbp, "Pilih laporan yang ingin ditolak", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    Laporan laporan = new Laporan();
                // Ambil nilai dari kolom pertama (index 0) sebagai String            
                String id_laporan_str = (String) lbp.getLaporanTable().getValueAt(selectedRow, 0);
                laporan.setJudulLaporan((String) lbp.getLaporanTable().getValueAt(selectedRow, 1));
                laporan.setBarang((String) lbp.getLaporanTable().getValueAt(selectedRow, 2));
                laporan.setLokasi((String) lbp.getLaporanTable().getValueAt(selectedRow, 3));
        try {
            // Konversi String ke int
            int id_laporan = Integer.parseInt(id_laporan_str);
            laporan.setIdLaporan(id_laporan);
            lbp.setVisible(false);       
            TolakLaporanPanel tolakLaporan = new TolakLaporanPanel(laporan);
            tolakLaporan.setVisible(true);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(lbp, "ID laporan tidak valid", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void handleProses(){
            int selectedRow = lbp.getLaporanTable().getSelectedRow();
                    if (selectedRow == -1) {
                        JOptionPane.showMessageDialog(lbp, "Pilih laporan yang ingin diproses ", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    Laporan laporan = new Laporan();
                // Ambil nilai dari kolom pertama (index 0) sebagai String            
                String id_laporan_str = (String) lbp.getLaporanTable().getValueAt(selectedRow, 0);
                laporan.setJudulLaporan((String) lbp.getLaporanTable().getValueAt(selectedRow, 1));
                laporan.setBarang((String) lbp.getLaporanTable().getValueAt(selectedRow, 2));
                laporan.setLokasi((String) lbp.getLaporanTable().getValueAt(selectedRow, 3));
        try {
            // Konversi String ke int
            int id_laporan = Integer.parseInt(id_laporan_str);
            laporan.setIdLaporan(id_laporan);
            lbp.setVisible(false);       
            SelesaiLaporanPanel selesaiLaporan = new SelesaiLaporanPanel(laporan);
            selesaiLaporan.setVisible(true);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(lbp, "ID laporan tidak valid", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void handleExport(){
        JFileChooser fileChooser =  lbp.getFileChooser();
        
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
                for (int col = 0; col < lbp.getLaporanTable().getColumnCount(); col++) {
                    csvWriter.append(lbp.getLaporanTable().getColumnName(col));
                    csvWriter.append(col == lbp.getLaporanTable().getColumnCount() - 1 ? "\n" : ",");
                }

                // Write data rows
                for (int row = 0; row < lbp.getLaporanTable().getRowCount(); row++) {
                    for (int col = 0; col < lbp.getLaporanTable().getColumnCount(); col++) {
                        csvWriter.append(lbp.getLaporanTable().getValueAt(row, col).toString());
                        csvWriter.append(col == lbp.getLaporanTable().getColumnCount() - 1 ? "\n" : ",");
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
        DefaultTableModel model = (DefaultTableModel) lbp.getLaporanTable().getModel();
        model.setRowCount(0); // Clear existing data
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try{
            conn = Database.getInstance().getConnection();
            String sql = "SELECT id_laporan, judul, barang, lokasi, tanggal_dibuat, users.nama AS pengirim FROM laporan JOIN users ON laporan.id_pengirim = users.id_user WHERE status = 'terkirim'";
            pstmt = conn.prepareStatement(sql);
             rs = pstmt.executeQuery();

            while (rs.next()) {
                String idLaporan = rs.getString("id_laporan");
                String judul = rs.getString("judul");
                String barang = rs.getString("barang");
                String lokasi = rs.getString("lokasi");
                String tanggalDibuat = rs.getString("tanggal_dibuat");
                String pengirim = rs.getString("pengirim");

                model.addRow(new Object[]{idLaporan, judul, barang, lokasi, pengirim, tanggalDibuat});
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
