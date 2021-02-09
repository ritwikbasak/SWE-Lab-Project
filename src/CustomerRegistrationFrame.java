
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
public class CustomerRegistrationFrame extends javax.swing.JFrame {

    /**
     * Creates new form CustomerRegistrationFrame
     */
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
    public CustomerRegistrationFrame() {
        lookSettingCode();
        initComponents();
        Password.setToolTipText("Password must contain at least one digit, one lowercase and one uppercase letter and must have at least 8 characters");
        passwordError.setVisible(false);
        cnfPasswordError.setVisible(false);
        
        emailIdError.setVisible(false);
        
        userNameError.setVisible(false);
        
        registerButton.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent me){
                registerClicked(me);
            }
        });
        Password.addKeyListener(new KeyAdapter(){
           public void keyPressed(KeyEvent evt){
               if(evt.getKeyCode() == KeyEvent.VK_ENTER){
                    
                    Password.setFocusable(false);
                    Password.setFocusable(true);
                    
               }
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
        
        emailIdText.addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent evt){
                if(evt.getKeyCode() == KeyEvent.VK_ENTER){
                    
                    emailIdText.setFocusable(false);
                    emailIdText.setFocusable(true);
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
                
                if(userNameText.getText().length() == 0) userNameError.setVisible(true);
                else userNameError.setVisible(false);
                    
              
            }
        });        
        
        emailIdText.addFocusListener(new FocusAdapter(){
            public void focusLost(FocusEvent fe){
                
                if(!Pattern.compile("^(?=.{1,64}@)[\\p{L}0-9_-]+(\\.[\\p{L}0-9_-]+)*@"
            + "[^-][\\p{L}0-9-]+(\\.[\\p{L}0-9-]+)*(\\.[\\p{L}]{2,})$").matcher(emailIdText.getText()).find()) emailIdError.setVisible(true);
                else emailIdError.setVisible(false);
                    
                
            }
        });
             
        Password.addFocusListener(new FocusAdapter(){
           public void focusLost(FocusEvent fe){
               
                    if(Password.getText().length() < 8 || !passwordStrength(Password.getText())) passwordError.setVisible(true);
                    else passwordError.setVisible(false);
                    
                    if(Password.getText().length() != 0 && !cnfPassword.getText().equals(Password.getText())) cnfPasswordError.setVisible(true);
                
           } 
        });
        cnfPassword.addFocusListener(new FocusAdapter(){
           public void focusLost(FocusEvent fe){
               
                   if(Password.getText().length() != 0 && !cnfPassword.getText().equals(Password.getText())) cnfPasswordError.setVisible(true);
                   else cnfPasswordError.setVisible(false);
                   
               
           } 
        });
        //focus
    }

    private boolean passwordStrength(String s){
        return Pattern.compile("[0-9]+").matcher(Password.getText()).find()
                && Pattern.compile("[A-Z]+").matcher(Password.getText()).find()
                && Pattern.compile("[a-z]+").matcher(Password.getText()).find();
    }
    private void registerClicked(MouseEvent me){
        boolean flag = false;
        if(userNameText.getText().length() == 0) {userNameError.setVisible(true); flag = true; }
        
        if(!Pattern.compile("^(?=.{1,64}@)[\\p{L}0-9_-]+(\\.[\\p{L}0-9_-]+)*@"
            + "[^-][\\p{L}0-9-]+(\\.[\\p{L}0-9-]+)*(\\.[\\p{L}]{2,})$").matcher(emailIdText.getText()).find()) {emailIdError.setVisible(true); flag = true; }
        if(Password.getText().length() < 8 || !passwordStrength(Password.getText())) {passwordError.setVisible(true); flag = true; }
        if(cnfPassword.getText().length() != 0 && !cnfPassword.getText().equals(Password.getText())) {cnfPasswordError.setVisible(true); flag = true; }
        if(flag == true) return;
        JOptionPane.showMessageDialog(this, "Customer Account Created");
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        userNameText = new javax.swing.JTextField();
        emailIdText = new javax.swing.JTextField();
        registerButton = new javax.swing.JButton();
        userNameError = new javax.swing.JLabel();
        emailIdError = new javax.swing.JLabel();
        passwordError = new javax.swing.JLabel();
        cnfPasswordError = new javax.swing.JLabel();
        Password = new javax.swing.JPasswordField();
        cnfPassword = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Create Account");
        setResizable(false);

        userNameText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userNameTextActionPerformed(evt);
            }
        });

        registerButton.setText("Register");

        userNameError.setForeground(new java.awt.Color(255, 0, 0));
        userNameError.setText("This field cannot be empty");

        emailIdError.setForeground(new java.awt.Color(255, 0, 51));
        emailIdError.setText("Enter a valid email ID");

        passwordError.setForeground(new java.awt.Color(255, 0, 0));
        passwordError.setText("Not a strong password");

        cnfPasswordError.setForeground(new java.awt.Color(255, 0, 0));
        cnfPasswordError.setText("Entered password doesn't match");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("User Name");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Email ID");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Password");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Confirm Password");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cnfPasswordError, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(passwordError, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(emailIdError, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cnfPassword, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 227, Short.MAX_VALUE)
                        .addComponent(registerButton)
                        .addGap(23, 23, 23))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(userNameError)
                                .addComponent(userNameText, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(emailIdText))
                            .addComponent(Password, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(userNameText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(userNameError)
                .addGap(34, 34, 34)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(emailIdText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(emailIdError)
                .addGap(27, 27, 27)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(passwordError)
                .addGap(34, 34, 34)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cnfPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(registerButton))
                .addGap(7, 7, 7)
                .addComponent(cnfPasswordError, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void userNameTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userNameTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_userNameTextActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CustomerRegistrationFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField Password;
    private javax.swing.JPasswordField cnfPassword;
    private javax.swing.JLabel cnfPasswordError;
    private javax.swing.JLabel emailIdError;
    private javax.swing.JTextField emailIdText;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel passwordError;
    private javax.swing.JButton registerButton;
    private javax.swing.JLabel userNameError;
    private javax.swing.JTextField userNameText;
    // End of variables declaration//GEN-END:variables
}
