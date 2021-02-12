
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ritwik Basak
 */
public class CustomerRegistrationUI extends javax.swing.JFrame {

    /**
     * Creates new form CustomerRegistrationUI
     */
    private AccountManager accMgr = new AccountManager();
    private void lookSettingCode(){
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {}
    }
    public CustomerRegistrationUI() {
        lookSettingCode();
        
        initComponents();
        
        mobileField.setDocument(new LongRangeDocument(0, Long.MAX_VALUE));
        
        String locationList[] = new String[LocationManager.size() + 1];
        locationList[0] = "NO LOCATION SELECTED";
        for(int i = 0; i < LocationManager.size(); i ++)
            locationList[i + 1] = LocationManager.getLocation(i);
        locationDropdown.setModel(new javax.swing.DefaultComboBoxModel<>(locationList));
        
        passwordField.setToolTipText("Password must contain at least one digit, one lowercase and one uppercase letter and must have at least 8 characters");
        
        passwordError.setVisible(false);
        cnfPasswordError.setVisible(false);
        emailError.setVisible(false);
        loginError.setVisible(false);
        mobileError.setVisible(false);
        locationError.setVisible(false);
        
        registerButton.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent me){
                registerClicked(me);
            }
        });
        passwordField.addKeyListener(new KeyAdapter(){
           public void keyPressed(KeyEvent evt){
               if(evt.getKeyCode() == KeyEvent.VK_ENTER){
                    passwordField.setFocusable(false);
                    passwordField.setFocusable(true);
               }
           } 
        });
        loginField.addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent evt){
                if(evt.getKeyCode() == KeyEvent.VK_ENTER){
                    loginField.setFocusable(false);
                    loginField.setFocusable(true);
                }
            }
        });        
        
        emailField.addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent evt){
                if(evt.getKeyCode() == KeyEvent.VK_ENTER){
                    emailField.setFocusable(false);
                    emailField.setFocusable(true);
                }
            }
        });
        
        cnfPasswordField.addKeyListener(new KeyAdapter(){
           public void keyPressed(KeyEvent evt){
               if(evt.getKeyCode() == KeyEvent.VK_ENTER){
                   cnfPasswordField.setFocusable(false);
                   cnfPasswordField.setFocusable(true);
               }
           } 
        });
        mobileField.addKeyListener(new KeyAdapter(){
           public void keyPressed(KeyEvent evt){
               if(evt.getKeyCode() == KeyEvent.VK_ENTER){
                   mobileField.setFocusable(false);
                   mobileField.setFocusable(true);
               }
           } 
        });
        
        //focus
        mobileField.addFocusListener(new FocusAdapter(){
            public void focusLost(FocusEvent fe){
                mobileError();
            }
        });        
        
        loginField.addFocusListener(new FocusAdapter(){
            public void focusLost(FocusEvent fe){
                loginError();
            }
        });        
        
        emailField.addFocusListener(new FocusAdapter(){
            public void focusLost(FocusEvent fe){
                emailError();
            }
        });
             
        passwordField.addFocusListener(new FocusAdapter(){
           public void focusLost(FocusEvent fe){
                passwordError();
           } 
        });
        cnfPasswordField.addFocusListener(new FocusAdapter(){
           public void focusLost(FocusEvent fe){
                cnfPasswordError();
           } 
        });
        //focus
    }

    private boolean passwordStrength(){
        return Pattern.compile("[0-9]+").matcher(passwordField.getText()).find()
                && Pattern.compile("[A-Z]+").matcher(passwordField.getText()).find()
                && Pattern.compile("[a-z]+").matcher(passwordField.getText()).find();
    }
    private void registerClicked(MouseEvent me){
        if(loginError() | emailError() | mobileError() | passwordError() | cnfPasswordError() | locationError()) return;
        JOptionPane.showMessageDialog(this, "Customer Account Created");
        accMgr.addCustomerAccount(loginField.getText(), emailField.getText(), mobileField.getText(), passwordField.getText(), locationDropdown.getItemAt(locationDropdown.getSelectedIndex()));
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        loginField = new javax.swing.JTextField();
        emailField = new javax.swing.JTextField();
        registerButton = new javax.swing.JButton();
        loginError = new javax.swing.JLabel();
        emailError = new javax.swing.JLabel();
        passwordError = new javax.swing.JLabel();
        cnfPasswordError = new javax.swing.JLabel();
        passwordField = new javax.swing.JPasswordField();
        cnfPasswordField = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        mobileField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        mobileError = new javax.swing.JLabel();
        locationDropdown = new javax.swing.JComboBox<>();
        locationError = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Create Account");
        setResizable(false);

        loginField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginFieldActionPerformed(evt);
            }
        });

        registerButton.setText("Register");

        loginError.setForeground(new java.awt.Color(255, 0, 0));
        loginError.setText("This field cannot be empty");

        emailError.setForeground(new java.awt.Color(255, 0, 51));
        emailError.setText("Enter a valid email ID");

        passwordError.setForeground(new java.awt.Color(255, 0, 0));
        passwordError.setText("Not a strong password");

        cnfPasswordError.setForeground(new java.awt.Color(255, 0, 0));
        cnfPasswordError.setText("Entered password doesn't match");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Login Name");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Email ID");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Password");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Confirm Password");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Mobile Number");

        mobileError.setForeground(new java.awt.Color(255, 0, 51));
        mobileError.setText("Invalid Mobile Number");

        locationDropdown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                locationDropdownActionPerformed(evt);
            }
        });

        locationError.setForeground(new java.awt.Color(255, 0, 51));
        locationError.setText("Invalid Location");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(emailError)
                            .addComponent(jLabel3)
                            .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(323, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(mobileField, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(mobileError)
                            .addComponent(jLabel5))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(loginError)
                                    .addComponent(loginField, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(emailField))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(locationDropdown, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(locationError)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(passwordError)
                                    .addComponent(cnfPasswordError)
                                    .addComponent(cnfPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 227, Short.MAX_VALUE)
                                .addComponent(registerButton)))
                        .addGap(23, 23, 23))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(loginField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(locationDropdown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(loginError)
                    .addComponent(locationError))
                .addGap(40, 40, 40)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(emailField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(emailError)
                .addGap(47, 47, 47)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mobileField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mobileError)
                .addGap(48, 48, 48)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(passwordError)
                .addGap(45, 45, 45)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cnfPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(registerButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cnfPasswordError, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loginFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_loginFieldActionPerformed

    private boolean loginError(){
        if(loginField.getText().length() == 0){
            loginError.setText("This field cannot be empty");
            loginError.setVisible(true);
            return true;
        }
        if(accMgr.searchByLoginId(loginField.getText()) != null){
            loginError.setText("Username Already Present");
            loginError.setVisible(true);
            return true;
        }
        loginError.setVisible(false);
        return false;
    }
    private boolean emailError(){
        if(!Pattern.compile("^(?=.{1,64}@)[\\p{L}0-9_-]+(\\.[\\p{L}0-9_-]+)*@"
            + "[^-][\\p{L}0-9-]+(\\.[\\p{L}0-9-]+)*(\\.[\\p{L}]{2,})$").matcher(emailField.getText()).find()){
            emailError.setVisible(true);
            return true;
        }
        emailError.setVisible(false);
        return false;
    }
    private boolean mobileError(){
        String temp = mobileField.getText();
        if(temp.length() != 10 && !(temp.length() == 13 && temp.substring(0,3).equals("+91"))){
            mobileError.setVisible(true);
            return true;
        }
        mobileError.setVisible(false);
        return false;
    }
    private boolean passwordError(){
        if(passwordField.getText().length() < 8 || !passwordStrength()){
            passwordError.setVisible(true);
            cnfPasswordError();
            return true;
        }
        passwordError.setVisible(false);
        cnfPasswordError();
        return false;
    }
    private boolean cnfPasswordError(){
        if(passwordField.getText().length() != 0 && !cnfPasswordField.getText().equals(passwordField.getText())){
            cnfPasswordError.setVisible(true);
            return true;
        }
        cnfPasswordError.setVisible(false);
        return false;
    }
    private boolean locationError(){
        String temp = locationDropdown.getItemAt(locationDropdown.getSelectedIndex());
        if(temp.equals("NO LOCATION SELECTED")){
            locationError.setVisible(true);
            return true;
        }
        locationError.setVisible(false);
        return false;
    }
    
    private void locationDropdownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_locationDropdownActionPerformed
        // TODO add your handling code here:
        locationError();
    }//GEN-LAST:event_locationDropdownActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CustomerRegistrationUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel cnfPasswordError;
    private javax.swing.JPasswordField cnfPasswordField;
    private javax.swing.JLabel emailError;
    private javax.swing.JTextField emailField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JComboBox<String> locationDropdown;
    private javax.swing.JLabel locationError;
    private javax.swing.JLabel loginError;
    private javax.swing.JTextField loginField;
    private javax.swing.JLabel mobileError;
    private javax.swing.JTextField mobileField;
    private javax.swing.JLabel passwordError;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JButton registerButton;
    // End of variables declaration//GEN-END:variables
}
