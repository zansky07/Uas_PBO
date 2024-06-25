/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.controller;

import com.admin.view.DaftarUserPanel;
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
public class ListUserController {
    
    private DaftarUserPanel dp;

    public ListUserController(DaftarUserPanel dp) {
        this.dp = dp;
        loadUserData();
        
        this.dp.getDeleteButton().addActionListener(e -> handleDelete());
        this.dp.getExportButton().addActionListener(e -> handleExport());
    }
    
    public void handleExport(){
        JFileChooser fileChooser =  dp.getFileChooser();
        
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
                for (int col = 0; col < dp.getUserTable().getColumnCount(); col++) {
                    csvWriter.append(dp.getUserTable().getColumnName(col));
                    csvWriter.append(col == dp.getUserTable().getColumnCount() - 1 ? "\n" : ",");
                }

                // Write data rows
                for (int row = 0; row < dp.getUserTable().getRowCount(); row++) {
                    for (int col = 0; col < dp.getUserTable().getColumnCount(); col++) {
                        csvWriter.append(dp.getUserTable().getValueAt(row, col).toString());
                        csvWriter.append(col == dp.getUserTable().getColumnCount() - 1 ? "\n" : ",");
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
    
    public void handleDelete(){
            int selectedRow = dp.getUserTable().getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(dp, "Pilih baris yang ingin dihapus", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

            String id_user = (String) dp.getUserTable().getValueAt(selectedRow, 0);
                if (id_user == null) {
                    JOptionPane.showMessageDialog(dp, "ID pengguna tidak valid", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

            Connection conn = null;
            PreparedStatement pstmt = null;

            try {
                conn = Database.getInstance().getConnection();
                String sql = "DELETE FROM users WHERE id_user = ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, id_user);

                int affectedRows = pstmt.executeUpdate();
                if (affectedRows > 0) {
                    JOptionPane.showMessageDialog(dp, "Data berhasil dihapus");
                } else {
                    JOptionPane.showMessageDialog(dp, "Data tidak ditemukan");
                }
                loadUserData();
            } catch (SQLException ex) {
                System.err.println(ex);
                JOptionPane.showMessageDialog(dp, "Gagal menghapus data", "Error", JOptionPane.ERROR_MESSAGE);
            } finally {
                try {
                    if (pstmt != null) pstmt.close();
                    if (conn != null) conn.close();
                } catch (SQLException ex) {
                    System.err.println(ex);
                }
            }
    }
    
    public void loadUserData(){
        DefaultTableModel model = (DefaultTableModel) dp.getUserTable().getModel();
        model.setRowCount(0); // Clear existing data
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try{
            conn = Database.getInstance().getConnection();
            String sql = "SELECT id_user, nama, email FROM users WHERE role != 'admin'";
            pstmt = conn.prepareStatement(sql);
             rs = pstmt.executeQuery();

            while (rs.next()) {
                String idUser = rs.getString("id_user");
                String nama = rs.getString("nama");
                String email = rs.getString("email");

                model.addRow(new Object[]{idUser, nama, email});
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
