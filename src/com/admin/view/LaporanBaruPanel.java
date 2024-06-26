/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.admin.view;

import com.client.view.LoginPanel;
import com.controller.ListLaporanBaruController;
import com.model.Laporan;
import javax.swing.JOptionPane;

/**
 *
 * @author hp
 */
public class LaporanBaruPanel extends javax.swing.JFrame {
    
    
    /**
     * Creates new form LaporanBaruPanel
     */
    public LaporanBaruPanel() {
        initComponents();
        this.setLocationRelativeTo(null);
        
         ListLaporanBaruController listLaporanBaruController = new ListLaporanBaruController(this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        FileChooser = new javax.swing.JFileChooser();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        DashboardLabel = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        MenuPenggunaLabel = new javax.swing.JLabel();
        MenuLaporanSelesaiLabel = new javax.swing.JLabel();
        MenuLaporanDitolakLabel = new javax.swing.JLabel();
        LogoutButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        LaporanTable = new javax.swing.JTable();
        ExportButton = new javax.swing.JButton();
        ProsesButton = new javax.swing.JButton();
        TolakButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 102));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/admin.png"))); // NOI18N

        DashboardLabel.setBackground(new java.awt.Color(255, 255, 255));
        DashboardLabel.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        DashboardLabel.setForeground(new java.awt.Color(255, 255, 255));
        DashboardLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        DashboardLabel.setText("Dashboard");
        DashboardLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DashboardLabelMouseClicked(evt);
            }
        });

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 51, 153));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Laporan Baru");
        jLabel5.setOpaque(true);

        MenuPenggunaLabel.setBackground(new java.awt.Color(255, 255, 255));
        MenuPenggunaLabel.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        MenuPenggunaLabel.setForeground(new java.awt.Color(255, 255, 255));
        MenuPenggunaLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        MenuPenggunaLabel.setText("Pengguna");
        MenuPenggunaLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MenuPenggunaLabelMouseClicked(evt);
            }
        });

        MenuLaporanSelesaiLabel.setBackground(new java.awt.Color(255, 255, 255));
        MenuLaporanSelesaiLabel.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        MenuLaporanSelesaiLabel.setForeground(new java.awt.Color(255, 255, 255));
        MenuLaporanSelesaiLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        MenuLaporanSelesaiLabel.setText("Laporan Selesai");
        MenuLaporanSelesaiLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MenuLaporanSelesaiLabelMouseClicked(evt);
            }
        });

        MenuLaporanDitolakLabel.setBackground(new java.awt.Color(255, 255, 255));
        MenuLaporanDitolakLabel.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        MenuLaporanDitolakLabel.setForeground(new java.awt.Color(255, 255, 255));
        MenuLaporanDitolakLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        MenuLaporanDitolakLabel.setText("Laporan Ditolak");
        MenuLaporanDitolakLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MenuLaporanDitolakLabelMouseClicked(evt);
            }
        });

        LogoutButton.setBackground(new java.awt.Color(255, 0, 0));
        LogoutButton.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        LogoutButton.setForeground(new java.awt.Color(255, 255, 255));
        LogoutButton.setText("Keluar");
        LogoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogoutButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LogoutButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 31, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(20, 20, 20))
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(DashboardLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(MenuLaporanSelesaiLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(MenuPenggunaLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(MenuLaporanDitolakLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(DashboardLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(MenuPenggunaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(MenuLaporanSelesaiLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(MenuLaporanDitolakLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(LogoutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(105, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel21.setBackground(new java.awt.Color(255, 255, 255));
        jLabel21.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 0, 255));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("Dashboard Admin");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 10, 11, 362);
        jPanel2.add(jLabel21, gridBagConstraints);

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Daftar Laporan Baru");

        LaporanTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id Laporan", "Judul Laporan", "Barang Rusak", "Lokasi", "Pengirim", "Tanggal Dibuat"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(LaporanTable);

        ExportButton.setBackground(new java.awt.Color(0, 0, 255));
        ExportButton.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        ExportButton.setForeground(new java.awt.Color(255, 255, 255));
        ExportButton.setText("Export");

        ProsesButton.setBackground(new java.awt.Color(0, 0, 255));
        ProsesButton.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        ProsesButton.setForeground(new java.awt.Color(255, 255, 255));
        ProsesButton.setText("Proses");
        ProsesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProsesButtonActionPerformed(evt);
            }
        });

        TolakButton.setBackground(new java.awt.Color(255, 0, 0));
        TolakButton.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        TolakButton.setForeground(new java.awt.Color(255, 255, 255));
        TolakButton.setText("Tolak");
        TolakButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TolakButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 46, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 499, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(180, 180, 180)
                .addComponent(jLabel10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(TolakButton, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addComponent(ProsesButton, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(ExportButton, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(81, 81, 81))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ExportButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ProsesButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TolakButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(118, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void LogoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogoutButtonActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        new LoginPanel().setVisible(true); // Asumsi Anda memiliki kelas LoginPanel untuk layar login
        dispose(); // Menutup jendela saat ini
    }//GEN-LAST:event_LogoutButtonActionPerformed

    private void DashboardLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DashboardLabelMouseClicked
        // TODO add your handling code here:
        this.setVisible(false);       
        DashAdminPanel dashAdmin = new DashAdminPanel();
        dashAdmin.setVisible(true);
    }//GEN-LAST:event_DashboardLabelMouseClicked

    private void MenuPenggunaLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MenuPenggunaLabelMouseClicked
        // TODO add your handling code here:
        this.setVisible(false);       
        DaftarUserPanel daftarUser = new DaftarUserPanel();
        daftarUser.setVisible(true);
    }//GEN-LAST:event_MenuPenggunaLabelMouseClicked

    private void MenuLaporanSelesaiLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MenuLaporanSelesaiLabelMouseClicked
        // TODO add your handling code here:
        this.setVisible(false);       
        LaporanSelesaiPanel laporanSelesai = new LaporanSelesaiPanel();
        laporanSelesai.setVisible(true);
    }//GEN-LAST:event_MenuLaporanSelesaiLabelMouseClicked

    private void MenuLaporanDitolakLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MenuLaporanDitolakLabelMouseClicked
        // TODO add your handling code here:
        this.setVisible(false);       
        LaporanDitolakPanel laporanDitolak = new LaporanDitolakPanel();
        laporanDitolak.setVisible(true);
    }//GEN-LAST:event_MenuLaporanDitolakLabelMouseClicked
    
    Laporan laporan = new Laporan();
    
    private void ProsesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProsesButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ProsesButtonActionPerformed

    private void TolakButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TolakButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TolakButtonActionPerformed
    public javax.swing.JTable getLaporanTable() {
        return LaporanTable;
    }
    
    public javax.swing.JButton getExportButton() {
        return ExportButton;
    }
    
    public javax.swing.JButton getTolakButton() {
        return TolakButton;
    }
    
    public javax.swing.JButton getProsesButton() {
        return ProsesButton;
    }
    
    public javax.swing.JFileChooser getFileChooser() {
        return FileChooser;
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LaporanBaruPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LaporanBaruPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LaporanBaruPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LaporanBaruPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LaporanBaruPanel().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel DashboardLabel;
    private javax.swing.JButton ExportButton;
    private javax.swing.JFileChooser FileChooser;
    private javax.swing.JTable LaporanTable;
    private javax.swing.JButton LogoutButton;
    private javax.swing.JLabel MenuLaporanDitolakLabel;
    private javax.swing.JLabel MenuLaporanSelesaiLabel;
    private javax.swing.JLabel MenuPenggunaLabel;
    private javax.swing.JButton ProsesButton;
    private javax.swing.JButton TolakButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
