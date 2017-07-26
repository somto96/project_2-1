package max;

import com.digitalpersona.onetouch.ui.swing.DPFPVerificationControl;
import com.digitalpersona.onetouch.ui.swing.DPFPVerificationEvent;
import com.digitalpersona.onetouch.ui.swing.DPFPVerificationListener;
import java.awt.BorderLayout;
import javax.swing.JDialog;
import max.model.Admin;
import max.model.Biometrics;
import max.model.Student;

public class VerifyForm 
        extends javax.swing.JFrame
        implements VerificationForm.Callback {
    private final Admin admin;
    
    public VerifyForm(Admin admin) {
        this.admin = admin;
        initComponents();
        setLocationRelativeTo(null);
    }

    @Override
    public void onVerify(Biometrics bio) {
        Util.redirect(this, new AdminPanel(admin));
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        inputRegnum = new javax.swing.JTextField();
        btnCheck = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        btnBack = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel1.setText("VERIFY STUDENT");

        btnCheck.setText("BEGIN STUDENT VERIFICATION");
        btnCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckActionPerformed(evt);
            }
        });

        jLabel2.setText("Enter Registration Number:");

        btnBack.setText("<<   BACK");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(inputRegnum)
                    .addComponent(btnCheck, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING))
                            .addComponent(btnBack))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnBack)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inputRegnum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCheck)
                .addGap(0, 9, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        Util.redirect(this, new AdminPanel(admin));
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckActionPerformed
        String regNumber = inputRegnum.getText().trim();
        if (regNumber.isEmpty()) {
            Util.error("Enter Reg. Number");
        } else {
            final Student s;
            final Biometrics bio;
            if ((s = Student.get(regNumber)) == null) {
                Util.error("Reg. Number " + regNumber + " does not exist");
            } else if ((bio = Biometrics.of(s)) == null) {
                //Util.error("No biometrics have been uploaded for " + s.regNumber);
                new VerificationForm(this, bio, this).setVisible(true);
            } else {
                new VerificationForm(this, bio, this).setVisible(true);
            }
        }
    }//GEN-LAST:event_btnCheckActionPerformed
    
    class VerifyDialog extends JDialog implements DPFPVerificationListener {
        final Biometrics bio;

        public VerifyDialog(Biometrics bio) {
            super(VerifyForm.this, true);
            this.bio = bio;
        }
        
        void engage() {
            DPFPVerificationControl control = new DPFPVerificationControl();
            control.addVerificationListener(this);
            setLayout(new BorderLayout());
            add(control, BorderLayout.CENTER);
            pack();
            setVisible(true);
        }

        @Override
        public void captureCompleted(DPFPVerificationEvent e) {
            String regNumber = bio.student.regNumber;
            
            if (bio.verify(e.getFeatureSet()).isVerified()) {
                Util.success(regNumber + " verified");
                e.setStopCapture(true);
                setVisible(false);
            } else {
                Util.error("Fingerprint does not match " + regNumber);
            }
        }
        
    }
    
    public static void main(String args[]) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new VerifyForm(Admin.DEBUG).setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnCheck;
    private javax.swing.JTextField inputRegnum;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
