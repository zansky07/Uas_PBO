package com.controller;

import com.admin.view.DashAdminPanel;
import com.client.view.HomePanel;
import com.client.view.LoginPanel;
import com.model.Database;
import com.model.User;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


/**
 *
 * @author hp
 */
public class LoginController {
    private Database db;
    // Login frame untuk show message dialog
    private LoginPanel lf;
    private User user;
    private String userRole;
    
    public LoginController(LoginPanel lf) {
        this.db = Database.getInstance();
        this.lf = lf;
        
        
        // Add action listener to the login button
        this.lf.getMasukButton().addActionListener(e -> handleLogin());
    }
    
    private void handleLogin() {
    User user = new User();
    String email = lf.getEmailTextField().getText();
    String password = new String(lf.getPasswordField().getPassword());

    if (checkLogin(email, password)) {
            JOptionPane.showMessageDialog(lf, "Login Berhasil!");
            if (isAdmin()) {
                JOptionPane.showMessageDialog(lf, "Selamat datang, Admin!");
                // Lanjutkan ke langkah berikutnya
                lf.setVisible(false);   
                // Membuat dan menampilkan frame dash admin
                DashAdminPanel dashadminPanel = new DashAdminPanel();
                dashadminPanel.setVisible(true);
            } else {
                user.setEmail(email);
                user.setPassword(password);
                JOptionPane.showMessageDialog(lf, "Selamat datang, User!");
                // Lanjutkan ke langkah berikutnya
                lf.setVisible(false);   
                // Membuat dan menampilkan frame home
                HomePanel homePanel = new HomePanel(user);
                homePanel.setVisible(true);
            }
        }
}
    
    
    public boolean checkLogin(String email, String password) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        try {
            connection = db.getConnection();
            String sql = "SELECT * FROM users WHERE email = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                String PassDb = resultSet.getString("password");
                String Role = resultSet.getString("role");
                if (password.equals(PassDb)) {
                    this.userRole = Role;
                    return true;
                } else {
                    JOptionPane.showMessageDialog(lf, "Password salah");
                    return false;
                }
            } else {
                JOptionPane.showMessageDialog(lf, "User tidak ditemukan");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(lf, "Terjadi kesalahan pada database: " + e.getMessage());
            return false;
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public boolean isAdmin() {
        return "admin".equalsIgnoreCase(this.userRole);
    }
    
    
    
}
