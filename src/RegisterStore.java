/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rishabh Sharma
 */
import java.awt.GridLayout;
import java.awt.event.*;
import java.io.File;
import java.util.*;
import javax.swing.*;
import java.util.regex.*;
import javax.swing.text.*;
public class RegisterStore extends javax.swing.JFrame {
    /**
     * Creates new form RegisterStore
     */
    private SystemManager sysMgr;
    private DisplayManager dispMgr;
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
    public RegisterStore(SystemManager sysMgr, DisplayManager dispMgr) {
        lookSettingCode();
        initComponents();
        this.sysMgr = sysMgr;
        this.dispMgr = dispMgr;
        
        addWindowListener(new WindowAdapter(){
                public void windowClosing(WindowEvent e){
                    
                    setVisible(false);
                    dispMgr.showHomeUI(true);
                }
            }
        );
        
        passwordError.setVisible(false);
        cnfPasswordError.setVisible(false);
        storeNameError.setVisible(false);
        storeNameError.setVisible(false);
        emailIdError.setVisible(false);
        mobileNoError.setVisible(false);
        userNameError.setVisible(false);
        String locationList[] = new String[LocationManager.size() + 1];
        locationList[0] = "NO LOCATION SELECTED";
        for(int i = 0; i < LocationManager.size(); i ++)
            locationList[i + 1] = LocationManager.getLocation(i);
        selectLocationComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(locationList));
        
        InvalidLocationError.setVisible(false);
        registerButton.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent me){
                registerClicked(me);
            }
        });
        userNameText.addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent evt){
                if(evt.getKeyCode() == KeyEvent.VK_ENTER){
                    
                    userNameText.setFocusable(false);
                    userNameText.setFocusable(true);
                }
            }
        });        
        mobileNoText.addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent evt){
                if(evt.getKeyCode() == KeyEvent.VK_ENTER){
                    
                    mobileNoText.setFocusable(false);
                    mobileNoText.setFocusable(true);
                }
            }
        });
        emailIdText.addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent evt){
                if(evt.getKeyCode() == KeyEvent.VK_ENTER){
                    
                    emailIdText.setFocusable(false);
                    emailIdText.setFocusable(true);
                }
            }
        });
        storeNameText.addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent evt){
                if(evt.getKeyCode() == KeyEvent.VK_ENTER){
                    
                    storeNameText.setFocusable(false);
                    storeNameText.setFocusable(true);
                }
            }
        });        
        mobileNoText.setDocument(new LongRangeDocument(0, Long.MAX_VALUE));
        Password.setToolTipText("Password must contain at least one digit, one lowercase and one uppercase letter and must have at least 8 characters");
        Password.addKeyListener(new KeyAdapter(){
           public void keyPressed(KeyEvent evt){
               if(evt.getKeyCode() == KeyEvent.VK_ENTER){
                    
                    Password.setFocusable(false);
                    Password.setFocusable(true);
                    
               }
           } 
        });
        cnfPassword.addKeyListener(new KeyAdapter(){
           public void keyPressed(KeyEvent evt){
               if(evt.getKeyCode() == KeyEvent.VK_ENTER){
                   
                   cnfPassword.setFocusable(false);
                   cnfPassword.setFocusable(true);
               }
           } 
        });
        //focus
        userNameText.addFocusListener(new FocusAdapter(){
            public void focusLost(FocusEvent fe){
                loginError();
            }
        });        
        mobileNoText.addFocusListener(new FocusAdapter(){
            public void focusLost(FocusEvent fe){
                if(true){
                    String temp = mobileNoText.getText();
                    if(temp.length() != 10 && !(temp.length() == 13 && temp.substring(0,3).equals("+91"))) mobileNoError.setVisible(true);
                    else mobileNoError.setVisible(false);
                    
                }
            }
        });
        emailIdText.addFocusListener(new FocusAdapter(){
            public void focusLost(FocusEvent fe){
                if(true){
                    if(!Pattern.compile("^(?=.{1,64}@)[\\p{L}0-9_-]+(\\.[\\p{L}0-9_-]+)*@"
            + "[^-][\\p{L}0-9-]+(\\.[\\p{L}0-9-]+)*(\\.[\\p{L}]{2,})$").matcher(emailIdText.getText()).find()) emailIdError.setVisible(true);
                    else emailIdError.setVisible(false);
                    
                }
            }
        });
        storeNameText.addFocusListener(new FocusAdapter(){
            public void focusLost(FocusEvent fe){
                if(true){
                    if(storeNameText.getText().length() == 0) storeNameError.setVisible(true);
                    else storeNameError.setVisible(false);
                    
                }
            }
        });        
        Password.addFocusListener(new FocusAdapter(){
           public void focusLost(FocusEvent fe){
               if(true){
                    if(Password.getText().length() < 8 || !passwordStrength(Password.getText())) passwordError.setVisible(true);
                    else passwordError.setVisible(false);
                    
                    if(Password.getText().length() != 0 && !cnfPassword.getText().equals(Password.getText())) cnfPasswordError.setVisible(true);
                }
           } 
        });
        cnfPassword.addFocusListener(new FocusAdapter(){
           public void focusLost(FocusEvent fe){
               if(true){
                   if(Password.getText().length() != 0 && !cnfPassword.getText().equals(Password.getText())) cnfPasswordError.setVisible(true);
                   else cnfPasswordError.setVisible(false);
                   
               }
           } 
        });
        //focus
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        storeNameLabel = new javax.swing.JLabel();
        userNameLabel = new javax.swing.JLabel();
        emailIdLabel = new javax.swing.JLabel();
        mobileNoLabel = new javax.swing.JLabel();
        passwordLabel = new javax.swing.JLabel();
        confirmPasswordLabel = new javax.swing.JLabel();
        storeNameText = new javax.swing.JTextField();
        userNameText = new javax.swing.JTextField();
        emailIdText = new javax.swing.JTextField();
        mobileNoText = new javax.swing.JTextField();
        cnfPassword = new javax.swing.JPasswordField();
        Password = new javax.swing.JPasswordField();
        registerButton = new javax.swing.JButton();
        setLocationLabel = new javax.swing.JLabel();
        storeNameError = new javax.swing.JLabel();
        userNameError = new javax.swing.JLabel();
        mobileNoError = new javax.swing.JLabel();
        emailIdError = new javax.swing.JLabel();
        cnfPasswordError = new javax.swing.JLabel();
        passwordError = new javax.swing.JLabel();
        selectLocationComboBox = new javax.swing.JComboBox<>();
        InvalidLocationError = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("REGISTER STORE - RISHABH SHARMA - 54");

        storeNameLabel.setText("STORE NAME");

        userNameLabel.setText("USERNAME");

        emailIdLabel.setText("EMAIL ID");

        mobileNoLabel.setText("MOBILE NO.");

        passwordLabel.setText("PASSWORD");

        confirmPasswordLabel.setText("CONFIRM PASSWORD");

        emailIdText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailIdTextActionPerformed(evt);
            }
        });

        cnfPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cnfPasswordActionPerformed(evt);
            }
        });

        Password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PasswordActionPerformed(evt);
            }
        });

        registerButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        registerButton.setText("REGISTER");
        registerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerButtonActionPerformed(evt);
            }
        });

        setLocationLabel.setText("SET STORE LOCATION");

        storeNameError.setForeground(new java.awt.Color(153, 0, 0));
        storeNameError.setText("This field cannot be empty");

        userNameError.setForeground(new java.awt.Color(153, 0, 0));
        userNameError.setText("This field cannot be empty");

        mobileNoError.setForeground(new java.awt.Color(153, 0, 0));
        mobileNoError.setText("Invalid Mobile No.");

        emailIdError.setForeground(new java.awt.Color(153, 0, 0));
        emailIdError.setText("Invalid Email Id");

        cnfPasswordError.setForeground(new java.awt.Color(153, 0, 0));
        cnfPasswordError.setText("Entered Password doesn't Match");

        passwordError.setForeground(new java.awt.Color(153, 0, 0));
        passwordError.setText("Not a Strong Password");

        selectLocationComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectLocationComboBoxActionPerformed(evt);
            }
        });

        InvalidLocationError.setForeground(new java.awt.Color(153, 0, 0));
        InvalidLocationError.setText("Invalid Location");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(storeNameLabel)
                    .addComponent(mobileNoLabel)
                    .addComponent(userNameLabel)
                    .addComponent(confirmPasswordLabel)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(emailIdLabel)
                        .addGap(93, 93, 93)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(emailIdText)
                            .addComponent(cnfPasswordError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(mobileNoText)
                            .addComponent(Password)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(mobileNoError)
                                    .addComponent(emailIdError)
                                    .addComponent(passwordError)
                                    .addComponent(userNameError)
                                    .addComponent(storeNameError))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(cnfPassword)
                            .addComponent(userNameText)
                            .addComponent(storeNameText)))
                    .addComponent(passwordLabel))
                .addGap(129, 129, 129)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(setLocationLabel)
                    .addComponent(selectLocationComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(InvalidLocationError)
                    .addComponent(registerButton, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(57, 57, 57))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(storeNameLabel)
                    .addComponent(storeNameText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(setLocationLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(selectLocationComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(storeNameError))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(InvalidLocationError)
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userNameText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(userNameLabel))
                .addGap(5, 5, 5)
                .addComponent(userNameError)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(emailIdText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(emailIdLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(emailIdError)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mobileNoText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mobileNoLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mobileNoError)
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(passwordLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(passwordError)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cnfPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(confirmPasswordLabel)
                    .addComponent(registerButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cnfPasswordError)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cnfPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cnfPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cnfPasswordActionPerformed

    private boolean loginError(){
        if(userNameText.getText().length() == 0){
            userNameError.setText("This field cannot be empty");
            userNameError.setVisible(true);
            return true;
        }
        if(sysMgr.searchByLoginId(userNameText.getText()) != null){
            userNameError.setText("Username Already Present");
            userNameError.setVisible(true);
            return true;
        }
        userNameError.setVisible(false);
        return false;
    }
    private void PasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PasswordActionPerformed

    private void emailIdTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailIdTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailIdTextActionPerformed

    private void registerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_registerButtonActionPerformed

    private void selectLocationComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectLocationComboBoxActionPerformed
        // TODO add your handling code here:
        String temp = selectLocationComboBox.getItemAt(selectLocationComboBox.getSelectedIndex());
        if(temp.equals("NO LOCATION SELECTED")) InvalidLocationError.setVisible(true);
        else InvalidLocationError.setVisible(false);
    }//GEN-LAST:event_selectLocationComboBoxActionPerformed

    /**
     * @param args the command line arguments
     */
    /*
    public static void main(String args[]) {
        
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegisterStore().setVisible(true);
            }
        });
    }*/
    private boolean passwordStrength(String s){
        return Pattern.compile("[0-9]+").matcher(Password.getText()).find()
                && Pattern.compile("[A-Z]+").matcher(Password.getText()).find()
                && Pattern.compile("[a-z]+").matcher(Password.getText()).find();
    }
    private void registerClicked(MouseEvent me){
        String temp = mobileNoText.getText();
        boolean flag = false;
        if(loginError()) {userNameError.setVisible(true); flag = true; }
        if(temp.length() != 10 && !(temp.length() == 13 && temp.substring(0,3).equals("+91"))) {mobileNoError.setVisible(true); flag = true; }
        if(!Pattern.compile("@+").matcher(emailIdText.getText()).find()) {emailIdError.setVisible(true); flag = true; }
        if(storeNameText.getText().length() == 0) {storeNameError.setVisible(true); flag = true; }
        if(Password.getText().length() < 8 || !passwordStrength(Password.getText())) {passwordError.setVisible(true); flag = true; }
        if(cnfPassword.getText().length() != 0 && !cnfPassword.getText().equals(Password.getText())) {cnfPasswordError.setVisible(true); flag = true; }
        temp = selectLocationComboBox.getItemAt(selectLocationComboBox.getSelectedIndex());
        if(temp.equals("NO LOCATION SELECTED")){
            InvalidLocationError.setVisible(true);
            flag = true;
        }
        if(flag == true) return;
        JOptionPane.showMessageDialog(this, "Store Will Be Verified And Registered");
        sysMgr.addChemistAccount(userNameText.getText(), emailIdText.getText(), mobileNoText.getText(), Password.getText(), selectLocationComboBox.getItemAt(selectLocationComboBox.getSelectedIndex()));
        sysMgr.addStore(userNameText.getText(), storeNameText.getText(), selectLocationComboBox.getItemAt(selectLocationComboBox.getSelectedIndex()));
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }
        
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel InvalidLocationError;
    private javax.swing.JPasswordField Password;
    private javax.swing.JPasswordField cnfPassword;
    private javax.swing.JLabel cnfPasswordError;
    private javax.swing.JLabel confirmPasswordLabel;
    private javax.swing.JLabel emailIdError;
    private javax.swing.JLabel emailIdLabel;
    private javax.swing.JTextField emailIdText;
    private javax.swing.JLabel mobileNoError;
    private javax.swing.JLabel mobileNoLabel;
    private javax.swing.JTextField mobileNoText;
    private javax.swing.JLabel passwordError;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JButton registerButton;
    private javax.swing.JComboBox<String> selectLocationComboBox;
    private javax.swing.JLabel setLocationLabel;
    private javax.swing.JLabel storeNameError;
    private javax.swing.JLabel storeNameLabel;
    private javax.swing.JTextField storeNameText;
    private javax.swing.JLabel userNameError;
    private javax.swing.JLabel userNameLabel;
    private javax.swing.JTextField userNameText;
    // End of variables declaration//GEN-END:variables
}
