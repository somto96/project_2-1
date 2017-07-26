package max;

import java.util.ArrayList;
import java.util.List;
import max.model.Admin;
import max.model.Department;
import max.model.Faculty;
import max.model.LGA;
import max.model.State;
import max.model.Student;

public class EnrollForm extends javax.swing.JFrame {
    private final Admin admin;
    
    public EnrollForm(Admin admin) {
        this.admin = admin;
        initComponents();
        setLocationRelativeTo(null);
        
        Util.setTextLength(inputOtherNames, 45);
        Util.setTextLength(inputFirstName, 45);
        Util.setTextLength(inputSurname, 45);
        Util.setTextLength(inputAddress, 80);
        Util.setTextLength(inputRegNumber, 11);
        Util.setTextLength(inputPhone, 11);
        Util.setTextLength(inputEmail, 45);
    }
    
    void submit() {
        LGA lga = Util.get(inputLGA);
        State state = Util.get(inputState);
        Faculty fac = Util.get(inputFaculty);
        Department dept = Util.get(inputDepartment);
        String reg = inputRegNumber.getText().trim();
        String sname = inputSurname.getText().trim();
        String fname = inputFirstName.getText().trim();
        String other = inputOtherNames.getText().trim();
        String phone = inputPhone.getText().trim();
        String email = inputEmail.getText().trim();
        String address = inputAddress.getText().trim();
        int lvl = Integer.parseInt(Util.get(inputLevel)) / 100;
        
        String error = "";
        if (fac == null || fac == Faculty.NONE) error = "Select Faculty";
        else if (dept == null || dept == Department.NONE) error = "Select Department";
        else if (reg.isEmpty()) error = "Enter Reg. Number";
        else if (!reg.matches("2\\d{3}/\\d{6}")) error = "Invalid Reg. Number";
        else if (sname.isEmpty()) error = "Enter Surname";
        else if (fname.isEmpty()) error = "Enter First Name";
        else if (state == State.NONE) error = "Select State";
        else if (lga == LGA.NONE) error = "Select LGA";
        else if (phone.isEmpty()) error = "Enter Phone Number";
        else if (!phone.matches("0\\d{10}")) error = "Invalid Phone Number";
        else if (email.isEmpty()) error = "Enter Email Address";
        else if (!email.matches("[^ @]+@[^ @]+")) error = "Invalid Email Address";
        else if (address.isEmpty()) error = "Enter Address";
        else if (Student.get(reg) != null) {
            error = "Reg. Number " + reg + " is already enrolled";
        }
        
        if (!error.isEmpty()) {
            Util.error(error);
        } else {
            Student student = Student.enroll(reg, dept, lvl,
                    sname, fname, other, lga, phone, email, address);
            Util.redirect(this, new EnrollSuccess(admin, student));
        }
    }
    
    List<Department> shownDepartments() {
        List<Department> list = new ArrayList<>();
        list.add(Department.NONE);
        list.addAll(Util.get(inputFaculty).departments());
        return list;
    }
    
    List<LGA> shownLGAs() {
        List<LGA> list = new ArrayList<>();
        list.add(LGA.NONE);
        list.addAll(Util.get(inputState).LGAs());
        return list;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        container = new javax.swing.JPanel();
        logo = new javax.swing.JLabel();
        title = new javax.swing.JLabel();
        subtitle = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        labelReg = new javax.swing.JLabel();
        inputRegNumber = new javax.swing.JTextField();
        inputSurname = new javax.swing.JTextField();
        labelSurname = new javax.swing.JLabel();
        inputFirstName = new javax.swing.JTextField();
        labelFirstName = new javax.swing.JLabel();
        inputOtherNames = new javax.swing.JTextField();
        labelOtherNames = new javax.swing.JLabel();
        labelFaculty = new javax.swing.JLabel();
        labelDepartment = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        inputDepartment = new javax.swing.JComboBox<Department>();
        inputFaculty = new javax.swing.JComboBox<Faculty>();
        jLabel7 = new javax.swing.JLabel();
        inputLevel = new javax.swing.JComboBox<String>();
        jSeparator2 = new javax.swing.JSeparator();
        labelState = new javax.swing.JLabel();
        inputState = new javax.swing.JComboBox<State>();
        inputLGA = new javax.swing.JComboBox<LGA>();
        inputAddress = new javax.swing.JTextField();
        labelAddress = new javax.swing.JLabel();
        inputPhone = new javax.swing.JTextField();
        labelPhone = new javax.swing.JLabel();
        inputEmail = new javax.swing.JTextField();
        labelEmail = new javax.swing.JLabel();
        btnCancel = new javax.swing.JButton();
        btnSubmit = new javax.swing.JButton();
        error = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(100, 50));
        setResizable(false);

        container.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        container.setAutoscrolls(true);

        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/logo_small.jpg"))); // NOI18N

        title.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
        title.setText("UNIVERSITY OF NIGERIA, NSUKKA");

        subtitle.setText("UNDERGRADUATE ENROLLMENT FORM");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Student Information"));

        labelReg.setText("Reg. Number:");

        labelSurname.setText("Surname:");

        labelFirstName.setText("First name:");

        labelOtherNames.setText("Other names:");

        labelFaculty.setText("Faculty:");

        labelDepartment.setText("Department:");

        inputDepartment.setModel(Util.model(Faculty.NONE.departments()));

        inputFaculty.setModel(Util.model(Faculty.list()));
        inputFaculty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputFacultyActionPerformed(evt);
            }
        });

        jLabel7.setText("Level:");

        inputLevel.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "100", "200", "300", "400", "500" }));

        labelState.setText("State / L.G.A:");

        inputState.setModel(Util.model(State.list()));
        inputState.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputStateActionPerformed(evt);
            }
        });

        inputLGA.setModel(Util.model(State.NONE.LGAs()));

        labelAddress.setText("Home Address:");

        labelPhone.setText("Phone Number:");

        labelEmail.setText("Email Address:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2)
                    .addComponent(jSeparator1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(labelReg, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                            .addComponent(labelDepartment, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelFaculty, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(inputFaculty, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(inputDepartment, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(inputRegNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(inputLevel, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(labelOtherNames, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                            .addComponent(labelFirstName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelSurname, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(inputSurname)
                            .addComponent(inputFirstName)
                            .addComponent(inputOtherNames)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(labelState, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelAddress, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelPhone, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                            .addComponent(labelEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(inputState, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(inputLGA, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(inputAddress)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(inputPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(inputEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 130, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelFaculty)
                    .addComponent(inputFaculty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelDepartment)
                    .addComponent(inputDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelReg)
                    .addComponent(inputRegNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(inputLevel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelSurname)
                    .addComponent(inputSurname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelFirstName)
                    .addComponent(inputFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelOtherNames)
                    .addComponent(inputOtherNames, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelState)
                    .addComponent(inputState, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inputLGA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inputAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelAddress))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inputPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelPhone))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inputEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelEmail)))
        );

        btnCancel.setText("CANCEL");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnSubmit.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        btnSubmit.setText("SUBMIT");
        btnSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitActionPerformed(evt);
            }
        });

        error.setForeground(new java.awt.Color(255, 8, 14));
        error.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        error.setText(" ");

        javax.swing.GroupLayout containerLayout = new javax.swing.GroupLayout(container);
        container.setLayout(containerLayout);
        containerLayout.setHorizontalGroup(
            containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(containerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(containerLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(error, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSubmit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancel))
                    .addGroup(containerLayout.createSequentialGroup()
                        .addGroup(containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(containerLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(logo)
                                .addGap(18, 18, 18)
                                .addGroup(containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(subtitle)
                                    .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        containerLayout.setVerticalGroup(
            containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(containerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(containerLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(title)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(subtitle))
                    .addComponent(logo))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSubmit)
                    .addComponent(btnCancel)
                    .addComponent(error))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(container, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(container, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitActionPerformed
        submit();
    }//GEN-LAST:event_btnSubmitActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        Util.redirect(this, new AdminPanel(admin));
    }//GEN-LAST:event_btnCancelActionPerformed

    private void inputFacultyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputFacultyActionPerformed
        inputDepartment.setModel(Util.model(shownDepartments()));
    }//GEN-LAST:event_inputFacultyActionPerformed

    private void inputStateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputStateActionPerformed
        inputLGA.setModel(Util.model(shownLGAs()));
    }//GEN-LAST:event_inputStateActionPerformed
    
    public static void main(String args[]) {
        Util.setLookAndFeel();
        java.awt.EventQueue.invokeLater(() -> {
            System.out.println(State.list());
            System.out.println(LGA.list());
            System.out.println(Faculty.list());
            System.out.println(Department.list());
            
            System.out.println(State.get("Anambra").LGAs());
            System.out.println(State.get("Anambra").id);
            System.out.println(State.get("Anambra").LGAs().get(0).state);
            System.out.println(Faculty.get("Engineering").departments());
            System.out.println(Student.get(1));
        
            new EnrollForm(Admin.DEBUG).setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnSubmit;
    private javax.swing.JPanel container;
    private javax.swing.JLabel error;
    private javax.swing.JTextField inputAddress;
    private javax.swing.JComboBox<Department> inputDepartment;
    private javax.swing.JTextField inputEmail;
    private javax.swing.JComboBox<Faculty> inputFaculty;
    private javax.swing.JTextField inputFirstName;
    private javax.swing.JComboBox<LGA> inputLGA;
    private javax.swing.JComboBox<String> inputLevel;
    private javax.swing.JTextField inputOtherNames;
    private javax.swing.JTextField inputPhone;
    private javax.swing.JTextField inputRegNumber;
    private javax.swing.JComboBox<State> inputState;
    private javax.swing.JTextField inputSurname;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel labelAddress;
    private javax.swing.JLabel labelDepartment;
    private javax.swing.JLabel labelEmail;
    private javax.swing.JLabel labelFaculty;
    private javax.swing.JLabel labelFirstName;
    private javax.swing.JLabel labelOtherNames;
    private javax.swing.JLabel labelPhone;
    private javax.swing.JLabel labelReg;
    private javax.swing.JLabel labelState;
    private javax.swing.JLabel labelSurname;
    private javax.swing.JLabel logo;
    private javax.swing.JLabel subtitle;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables
}
