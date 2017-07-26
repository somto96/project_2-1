package max;

import max.model.Admin;

public final class AdminPanel extends javax.swing.JFrame {
    private final Admin admin;
    
    public AdminPanel(Admin admin) {
        assert admin != null;
        
        this.admin = admin;
        initComponents();
        setLocationRelativeTo(null);
        welcome.setText("Welcome, " + admin.firstName);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        title = new javax.swing.JLabel();
        welcome = new javax.swing.JLabel();
        btnEnroll = new javax.swing.JButton();
        btnBiometrics = new javax.swing.JButton();
        btnVerify = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        btnLogout = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        title.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
        title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title.setText("ADMIN PANEL");

        welcome.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        welcome.setText("WELCOME, {NAME}");

        btnEnroll.setText("ENROLL STUDENT");
        btnEnroll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnrollActionPerformed(evt);
            }
        });

        btnBiometrics.setText("UPLOAD BIOMETRICS");
        btnBiometrics.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBiometricsActionPerformed(evt);
            }
        });

        btnVerify.setText("STUDENT VERIFICATION");
        btnVerify.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerifyActionPerformed(evt);
            }
        });

        btnLogout.setText("LOGOUT");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(welcome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEnroll, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBiometrics, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnVerify, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                    .addComponent(btnLogout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(welcome)
                .addGap(18, 18, 18)
                .addComponent(btnEnroll, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBiometrics, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnVerify, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEnrollActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnrollActionPerformed
        Util.redirect(this, new EnrollForm(admin));
    }//GEN-LAST:event_btnEnrollActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        Util.redirect(this, new AdminLogin());
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnVerifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerifyActionPerformed
        Util.redirect(this, new VerifyForm(admin));
    }//GEN-LAST:event_btnVerifyActionPerformed

    private void btnBiometricsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBiometricsActionPerformed
        Util.redirect(this, new StudentSelect
        (admin, "Upload Biometrics", BiometricsForm.class));
    }//GEN-LAST:event_btnBiometricsActionPerformed

    public static void main(String args[]) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new AdminPanel(Admin.DEBUG).setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBiometrics;
    private javax.swing.JButton btnEnroll;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnVerify;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel title;
    private javax.swing.JLabel welcome;
    // End of variables declaration//GEN-END:variables
}
