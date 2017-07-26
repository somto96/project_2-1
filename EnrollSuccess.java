package max;

import max.model.Admin;
import max.model.Student;

public class EnrollSuccess extends javax.swing.JFrame {
    private final Admin admin;
    private final Student student;
    
    public EnrollSuccess(Admin admin, Student student) {
        this.student = student;
        this.admin = admin;
        initComponents();
        
        labelName.setText(student.fullName());
        labelRegNumber.setText(student.regNumber);
        labelDepartment.setText(student.department.name);
        setLocationRelativeTo(null);
    }

    EnrollSuccess(Admin admin) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        header = new javax.swing.JLabel();
        labelName = new javax.swing.JLabel();
        labelRegNumber = new javax.swing.JLabel();
        labelDepartment = new javax.swing.JLabel();
        btnEnroll = new javax.swing.JToggleButton();
        btnPanel = new javax.swing.JToggleButton();
        btnBiometrics = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        header.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        header.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        header.setText("ENROLMENT SUCCESSFUL");

        labelName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelName.setText("{NAME}");

        labelRegNumber.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelRegNumber.setText("{REG NUMBER}");

        labelDepartment.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelDepartment.setText("{DEPARTMENT}");

        btnEnroll.setText("ENROLL ANOTHER STUDENT");
        btnEnroll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnrollActionPerformed(evt);
            }
        });

        btnPanel.setText("RETURN TO ADMIN PANEL");
        btnPanel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPanelActionPerformed(evt);
            }
        });

        btnBiometrics.setText("REGISTER STUDENT BIOMETRICS");
        btnBiometrics.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBiometricsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                    .addComponent(labelName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelRegNumber, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelDepartment, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEnroll, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBiometrics, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(header)
                .addGap(18, 18, 18)
                .addComponent(labelName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelRegNumber)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelDepartment)
                .addGap(18, 18, 18)
                .addComponent(btnBiometrics, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnEnroll, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBiometricsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBiometricsActionPerformed
        Util.redirect(this, new BiometricsForm(admin, student));
    }//GEN-LAST:event_btnBiometricsActionPerformed

    private void btnEnrollActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnrollActionPerformed
        Util.redirect(this, new EnrollForm(admin));
    }//GEN-LAST:event_btnEnrollActionPerformed

    private void btnPanelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPanelActionPerformed
        Util.redirect(this, new AdminPanel(admin));
    }//GEN-LAST:event_btnPanelActionPerformed

    public static void main(String args[]) {
        Util.setLookAndFeel();
        java.awt.EventQueue.invokeLater(() -> {
            Student s = Student.get(1);
            new EnrollSuccess(Admin.DEBUG, s).setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btnBiometrics;
    private javax.swing.JToggleButton btnEnroll;
    private javax.swing.JToggleButton btnPanel;
    private javax.swing.JLabel header;
    private javax.swing.JLabel labelDepartment;
    private javax.swing.JLabel labelName;
    private javax.swing.JLabel labelRegNumber;
    // End of variables declaration//GEN-END:variables
}
