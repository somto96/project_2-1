package max;

import com.digitalpersona.onetouch.DPFPFingerIndex;
import com.digitalpersona.onetouch.DPFPTemplate;
import com.digitalpersona.onetouch.ui.swing.DPFPEnrollmentControl;
import com.digitalpersona.onetouch.ui.swing.DPFPEnrollmentEvent;
import com.digitalpersona.onetouch.ui.swing.DPFPEnrollmentListener;
import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.EnumSet;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import max.model.Admin;
import max.model.Biometrics;
import max.model.Student;

public class BiometricsForm 
        extends javax.swing.JFrame 
        implements EnrollmentForm.Callback {
    private final Student student;
    private final Admin admin;
    private DPFPTemplate template = null;
    private BufferedImage image = null;
    
    public BiometricsForm(Admin admin, Student student) {
        this.student = student;
        this.admin = admin;
        initComponents();
        setLocationRelativeTo(null);
        
        textSurname.setText(student.surname);
        txtRegNumber.setText(student.regNumber);
        textFirstname.setText(student.firstName);
    }

    @Override
    public void onEnroll(DPFPTemplate template) {
        this.template = template;
        Util.setImageRes(imageFingerprint, 
                "/res/biometrics_enrol_passed.png");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btnFingerprint = new javax.swing.JButton();
        imageFingerprint = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnPicture = new javax.swing.JButton();
        picture = new javax.swing.JLabel();
        btnCancel = new javax.swing.JButton();
        btnSubmit = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        textSurname = new javax.swing.JTextField();
        textFirstname = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtRegNumber = new javax.swing.JTextField();
        error = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/logo_small.jpg"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
        jLabel2.setText("UNIVERSITY OF NIGERIA, NSUKKA");

        jLabel3.setText("BIOMETRICS UPLOAD FORM");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Capture Fingerprint"));

        btnFingerprint.setText("SCAN FINGERPRINT");
        btnFingerprint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFingerprintActionPerformed(evt);
            }
        });

        imageFingerprint.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imageFingerprint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/biometrics_enrol.png"))); // NOI18N
        imageFingerprint.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnFingerprint, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(imageFingerprint, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(imageFingerprint, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnFingerprint))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Capture Picture"));

        btnPicture.setText("SELECT PICTURE");
        btnPicture.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPictureActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnPicture, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
            .addComponent(picture, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(picture, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPicture))
        );

        btnCancel.setText("CANCEL");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnSubmit.setText("SUBMIT");
        btnSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitActionPerformed(evt);
            }
        });

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Student Information"));

        jLabel4.setText("Name:");

        textSurname.setEnabled(false);

        textFirstname.setEnabled(false);

        jLabel5.setText("Reg. Number:");

        txtRegNumber.setEnabled(false);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(textSurname)
            .addComponent(textFirstname)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(txtRegNumber)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textSurname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textFirstname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtRegNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        error.setForeground(new java.awt.Color(255, 0, 0));
        error.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        error.setText("    ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSubmit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnCancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2))
                                .addGap(0, 148, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(error, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(error)))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSubmit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnFingerprintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFingerprintActionPerformed
        // new EnrollDialog().engage();
        new EnrollmentForm(this, this).setVisible(true);
    }//GEN-LAST:event_btnFingerprintActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        Util.redirect(this, new AdminPanel(admin));
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnPictureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPictureActionPerformed
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter
        (new FileNameExtensionFilter("Image", "jpg", "png", "jpeg"));
        
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                BufferedImage img = ImageIO.read(chooser.getSelectedFile());
                image = Util.scaleWidth(img, 200);
                picture.setIcon(new ImageIcon(image));
                Util.success("Image captured");
            } catch (IOException ex) {
                Util.error("Error selecting file. Please try again.");
            }
        }
    }//GEN-LAST:event_btnPictureActionPerformed

    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitActionPerformed
        if (template == null) {
            Util.error("Capture fingerprint first");
        } else if (picture == null) {
            Util.error("Capture picture first");
        } else if (Biometrics.of(student) != null) {
            Util.error("Biometrics already captured for " + student.regNumber);
        } else {
            try {
                Biometrics.upload(student, image, template);
                Util.success("Biometrics successfully uploaded");
                Util.redirect(this, new AdminPanel(admin));
            } catch(Exception ex) {
                Util.error("Biometrics upload failed");
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnSubmitActionPerformed

    
    public static void main(String args[]) {
        Util.setLookAndFeel();
        java.awt.EventQueue.invokeLater(() -> {
            Student s = Student.get(1);
            new BiometricsForm(Admin.DEBUG, s).setVisible(true);
        });
    }
    
    class EnrollDialog extends JDialog implements DPFPEnrollmentListener {
        @Override public void fingerDeleted(DPFPEnrollmentEvent e) {}
        EnrollDialog() { super(BiometricsForm.this, true); }
        
        void engage() {
            DPFPEnrollmentControl control = new DPFPEnrollmentControl();
            control.setEnrolledFingers(EnumSet.allOf(DPFPFingerIndex.class));
            setLayout(new BorderLayout());
            add(control, BorderLayout.CENTER);
            pack();
            setVisible(true);
        }


        @Override
        public void fingerEnrolled(DPFPEnrollmentEvent e) {
            Util.success("Fingerprint template captured");
            Util.setImageRes(imageFingerprint, 
                    "/res/biometrics_enrol_passed.png");
            template = e.getTemplate();
            e.setStopCapture(true);
            setVisible(false);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnFingerprint;
    private javax.swing.JButton btnPicture;
    private javax.swing.JButton btnSubmit;
    private javax.swing.JLabel error;
    private javax.swing.JLabel imageFingerprint;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JLabel picture;
    private javax.swing.JTextField textFirstname;
    private javax.swing.JTextField textSurname;
    private javax.swing.JTextField txtRegNumber;
    // End of variables declaration//GEN-END:variables
}
