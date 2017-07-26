package max;

import javax.swing.JFrame;
import max.model.Admin;
import max.model.Student;

public class StudentSelect extends javax.swing.JFrame {
    private final Class<? extends JFrame> target;
    private final Admin admin;
    
    public StudentSelect(Admin admin, String text, 
            Class<? extends JFrame> target) {
        this.target = target;
        this.admin = admin;
        initComponents();
        
        Util.setTextLength(input, 11);
        button.setText(text.toUpperCase());
        setLocationRelativeTo(null);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        label = new javax.swing.JLabel();
        input = new javax.swing.JTextField();
        button = new javax.swing.JButton();
        back = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label.setText("ENTER REGISTRATION NUMBER");

        button.setText("{TEXT}");
        button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonActionPerformed(evt);
            }
        });

        back.setText("<<   BACK TO PANEL");
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(label, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                    .addComponent(input)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(back)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(back)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(input, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(button, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        Util.redirect(this, new AdminPanel(admin));
    }//GEN-LAST:event_backActionPerformed

    private void buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonActionPerformed
        String reg = input.getText();
        String error = "";
        
        Student student = null;
        if (reg.isEmpty()) {
            error = "Enter Reg. Number";
        } else if (!reg.matches("2\\d{3}/\\d{6}")) {
            error = "Invalid Reg. Number";
        } else if ((student = Student.get(reg)) == null) {
            error = "Reg. Number does not exist";
        }
        
        if (!error.isEmpty()) {
            Util.error(error);
        } else {
            try {
                JFrame frame = target
                        .getConstructor(Admin.class, Student.class)
                        .newInstance(admin, student);
                Util.redirect(this, frame);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }//GEN-LAST:event_buttonActionPerformed

    public static void main(String args[]) {
        Util.setLookAndFeel();
        java.awt.EventQueue.invokeLater(() -> {
            new StudentSelect(Admin.DEBUG, "UPLOAD BIOMETRICS", 
                    BiometricsForm.class).setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton back;
    private javax.swing.JButton button;
    private javax.swing.JTextField input;
    private javax.swing.JLabel label;
    // End of variables declaration//GEN-END:variables
}
