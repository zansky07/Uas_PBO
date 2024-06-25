package com.controller;


import com.client.view.LoginPanel;
import com.client.view.SignupPanel;
import com.model.Database;
import com.model.User;
import com.model.ValidatorException;
import com.model.ValidatorUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import javax.swing.JOptionPane;


/**
 *
 * @author LENOVO
 */
public class RegisterController {
  
    // Registration frame untuk show message dialog
    private SignupPanel rf;

    public RegisterController(SignupPanel rf) {
        
        this.rf = rf;
        
        // Add action listener to the login button
        this.rf.getDaftarButton().addActionListener(e -> handleRegister());
    }
    
    private void handleRegister() {
        String nim = rf.getNIMTextField().getText();
        String nama = rf.getNamaTextField().getText();
        String email = rf.getEmailTextField().getText();
        String password = new String(rf.getPasswordField().getPassword());
        String cpassword = new String(rf.getConfirmPasswordField().getPassword());

        if (addUser(nim, nama, email, password, cpassword)) {
                JOptionPane.showMessageDialog(rf, "Pendaftaran Berhasil!");
                
                    JOptionPane.showMessageDialog(rf, "Silahkan melakukan Log In");
                    // Lanjutkan ke langkah berikutnya
                    rf.setVisible(false);   
                    // Membuat dan menampilkan frame login
                    LoginPanel loginPanel = new LoginPanel();
                    loginPanel.setVisible(true);
                
            }
    }
    
    public boolean addUser(String nim, String nama, String email, String password, String cpassword){
        User user = new User();
               user.setNama(nama);
               user.setIdUser(nim);
               user.setEmail(email);

               if (password.equals(cpassword)) {
                   user.setPassword(password);
               } else {
                   JOptionPane.showMessageDialog(rf, "Password tidak sama");
                   return false;
               }    
        
               ValidatorUser validator = new ValidatorUser(user);
                try {
                    if (!validator.validate()) {
                        return false; // validation failed
                    }
                } catch (ValidatorException e) {
                    JOptionPane.showMessageDialog(rf, e.getMessage());
                    return false;
                }
                
            Connection conn = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            try {
                // Check if username already exists
                conn = Database.getInstance().getConnection();
                String sqlCheck = "SELECT COUNT(*) FROM users WHERE email = ?";
                pstmt = conn.prepareStatement(sqlCheck);
                pstmt.setString(1, user.getEmail());
                rs = pstmt.executeQuery();
                if (rs.next() && rs.getInt(1) > 0) {
                    JOptionPane.showMessageDialog(rf, "Email sudah digunakan");
                    return false;
                }

                // Insert into users table
                conn.setAutoCommit(false);
                String sql1 = "INSERT INTO users(id_user, nama , email ,password,role) VALUES(?, ?, ?, ?, 'user')";
                pstmt = conn.prepareStatement(sql1, PreparedStatement.RETURN_GENERATED_KEYS);
                pstmt.setString(1, user.getIdUser());
                pstmt.setString(2, user.getNama());
                pstmt.setString(3, user.getEmail());
                pstmt.setString(4, user.getPassword());
                pstmt.executeUpdate();
                
                // Commit transaction
                conn.commit();
                JOptionPane.showMessageDialog(rf, "Pendaftaran sukses");
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

   
}
