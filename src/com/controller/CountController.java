/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.controller;

import com.admin.view.DashAdminPanel;
import com.model.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author hp
 */
public class CountController {
    
    private DashAdminPanel dashAdmin;
    private int userCount;
    private int laporanBaruCount;
    private int laporanSelesaiCount;
    private int laporanDitolakCount;
    
    
    public CountController(DashAdminPanel dashAdmin) {
        this.dashAdmin = dashAdmin;
        
        loadCountLabel();
    }
    
    public void loadCountLabel(){
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try{
            conn = Database.getInstance().getConnection();
//            hitung jumlah user
            String sqlCheck = "SELECT COUNT(*) FROM users WHERE role != 'admin'";
            pstmt = conn.prepareStatement(sqlCheck);
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                userCount = rs.getInt(1);
            }
            
            String userCountStr = Integer.toString(userCount);
            dashAdmin.getUserCountLabel().setText(userCountStr);
            
//            Hitung jumlah laporan baru
            conn.setAutoCommit(false);
            String sqlCountLaporanBaru = "SELECT COUNT(*) FROM laporan WHERE status = 'terkirim'";
            pstmt = conn.prepareStatement(sqlCountLaporanBaru);
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                laporanBaruCount = rs.getInt(1);
            }
            
            String laporanBaruCountStr = Integer.toString(laporanBaruCount);
            dashAdmin.getLaporanBaruCountLabel().setText(laporanBaruCountStr);
            
//            hitung jumlah laporan selesai
            conn.setAutoCommit(false);
            String sqlCountLaporanSelesai = "SELECT COUNT(*) FROM laporan WHERE status = 'selesai'";
            pstmt = conn.prepareStatement(sqlCountLaporanSelesai);
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                laporanSelesaiCount = rs.getInt(1);
            }
            
            String laporanSelesaiCountStr = Integer.toString(laporanSelesaiCount);
            dashAdmin.getLaporanSelesaiCountLabel().setText(laporanSelesaiCountStr);
            
            //            hitung jumlah laporan ditolak
            conn.setAutoCommit(false);
            String sqlCountLaporanDitolak = "SELECT COUNT(*) FROM laporan WHERE status = 'ditolak'";
            pstmt = conn.prepareStatement(sqlCountLaporanDitolak);
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                laporanDitolakCount = rs.getInt(1);
            }
            
            String laporanDitolakCountStr = Integer.toString(laporanDitolakCount);
            dashAdmin.getLaporanDitolakCountLabel().setText(laporanDitolakCountStr);
        }catch(SQLException e){
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
