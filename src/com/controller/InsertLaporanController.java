/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.controller;


import com.client.view.InsertLaporanPanel;
import com.model.Database;
import com.model.Laporan;
import com.model.User;
import com.model.ValidatorException;
import com.model.ValidatorLaporan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 *
 * @author hp
 */
public class InsertLaporanController {
    private Database db;
    // Registration frame untuk show message dialog
    private InsertLaporanPanel ip;
    private User user;

    public InsertLaporanController(InsertLaporanPanel ip, User user) {
        this.ip = ip;
        this.user = user;
        
        // Add action listener to the login button
        this.ip.getSubmitButton().addActionListener(e -> handleInsert(user));
        
    }
    
    private void handleInsert(User user){
        String judul = ip.getJudulTextField().getText();
        String barang = ip.getBarangTextField().getText();
        String lokasi = ip.getLokasiTextField().getText();
        String deskripsi = ip.getDeskripsiTextArea().getText();
        
        if (addLaporan(judul, barang,  lokasi, deskripsi, user)) {
                
                
                    JOptionPane.showMessageDialog(ip, "Terima Kasih");
                    // Lanjutkan ke langkah berikutnya
                    ip.getJudulTextField().setText("");  
                    ip.getBarangTextField().setText("");
                    ip.getLokasiTextField().setText("");
                    ip.getDeskripsiTextArea().setText("");
            }
    }
    
    public boolean addLaporan(String judul, String barang, String lokasi, String deskripsi, User user){
        Laporan laporan = new Laporan();
        laporan.setJudulLaporan(judul);
        laporan.setBarang(barang);
        laporan.setLokasi(lokasi);
        laporan.setDeskripsi(deskripsi);
        laporan.setUser(user);
        
        ValidatorLaporan validator = new ValidatorLaporan(laporan);
        try {
            if (!validator.validate()) {
                return false; // validation failed
            }
        } catch (ValidatorException e) {
            JOptionPane.showMessageDialog(ip, e.getMessage());
            return false;
        }
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
        conn = Database.getInstance().getConnection();
        String sqlCheck = "SELECT id_user FROM users WHERE email = ?";
        pstmt = conn.prepareStatement(sqlCheck);
        pstmt.setString(1, laporan.getUser().getEmail());
        rs = pstmt.executeQuery();
        if (rs.next()) {
                laporan.getUser().setIdUser(rs.getString("id_user"));
            } else {
                JOptionPane.showMessageDialog(ip, "User tidak ditemukan");
                return false;
            }
        
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = today.format(formatter);
        
        conn.setAutoCommit(false);
        String sql1 = "INSERT INTO laporan(judul, barang , lokasi ,deskripsi, id_pengirim, tanggal_dibuat) VALUES(?, ?, ?, ?, ?, ?)";
        pstmt = conn.prepareStatement(sql1, PreparedStatement.RETURN_GENERATED_KEYS);
        pstmt.setString(1, laporan.getJudulLaporan());
        pstmt.setString(2, laporan.getBarang());
        pstmt.setString(3, laporan.getLokasi());
        pstmt.setString(4, laporan.getDeskripsi());
        pstmt.setString(5, laporan.getUser().getIdUser());
        pstmt.setString(6, formattedDate);
        
        pstmt.executeUpdate();

        // Commit transaction
        conn.commit();
        JOptionPane.showMessageDialog(ip, "Laporan Berhasil Ditambahkan!");
        return true;
        }catch (SQLException e) {
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
}
