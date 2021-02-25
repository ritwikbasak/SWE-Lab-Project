/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rohit Basu
 */
import java.awt.GridLayout;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;
public class ManageStore extends javax.swing.JFrame {

    /**
     * Creates new form ManageStore
     */
    private SystemManager sysMgr;
    private DisplayManager dispMgr;
    private ArrayList<Store> storeList;
    private ArrayList<JCheckBox> checkboxList;
    private ArrayList<Store> newStores;
    private boolean windowFlag;
    private void toggleWindowFlag(){
        windowFlag = !windowFlag;
    }
    private boolean getWindowFlag(){
        return windowFlag;
    }
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
    public ManageStore(SystemManager sysMgr, DisplayManager dispMgr) {
        this.sysMgr = sysMgr;
        this.dispMgr = dispMgr;
        windowFlag = false;
        addWindowListener(new WindowAdapter(){
                public void windowClosing(WindowEvent e){
                    toggleWindowFlag();
                    setVisible(false);
                    dispMgr.showHomeUI(true);
                }
                public void windowActivated(WindowEvent e){
                    if(getWindowFlag())
                        return;
                    toggleWindowFlag();
                    storeList = sysMgr.getAllStores();
                    showExistingStores();
                    showUnverifiedStores();
                }
            }
        );
        
        lookSettingCode();
        initComponents();
        
    }

    private String getSpaces(int i){
        String s = "";
        for(int j = 1; j <= i * 2; j++) s += " ";
        return s;
    }
    private void showExistingStores(){
        existingStoresPanel.setVisible(false);
        existingStoresPanel.removeAll();
        ArrayList<Store> oldStores = new ArrayList<>();
        for(Store i : storeList)
            if(i.isVerified())
                oldStores.add(i);
        
        int count = 0;
        ArrayList<JLabel> storesLabel = new ArrayList<>();
        for(Store i : oldStores){
            count ++;
            storesLabel.add(new JLabel(getSpaces((oldStores.size() + "").length() - (count + "").length()) + count + ". Store Name : " + i.getStoreName() + "       Store ID : " + i.getStoreId()));
        }
        existingStoresPanel.setLayout(new GridLayout(storesLabel.size(), 1, 0, 0));
        for(JLabel i : storesLabel){
            i.setFont(new java.awt.Font("Tahoma", 1, 12));
            existingStoresPanel.add(i);
        }
        existingStoresPanel.setVisible(true);
    }
    private void verifyClicked(){
        if(!verify.isEnabled())
            return;
        boolean flag = false;
        for(int i = 0; i < checkboxList.size(); i ++){
            if(checkboxList.get(i).isSelected()){
                flag = true;
                newStores.get(i).setIsVerified(true);
            }
        }
        if(flag == false){
            JOptionPane.showMessageDialog(this, "No Store Selected For Verification");
            return;
        }
        showExistingStores();
        showUnverifiedStores();
    }
    private void showUnverifiedStores(){
        newStoresPanel.setVisible(false);
        newStoresPanel.removeAll();
        newStores = new ArrayList<>();
        for(Store i : storeList)
            if(!i.isVerified())
                newStores.add(i);
        if(newStores.size() == 0){
            clearSelection.setEnabled(false);
            verify.setEnabled(false);
            selectAll.setEnabled(false);
        }
        else{
            clearSelection.setEnabled(true);
            verify.setEnabled(true);
            selectAll.setEnabled(true);
        }
        checkboxList = new ArrayList<>();
        for(Store i : newStores)
            checkboxList.add(new JCheckBox(i.getStoreName() + "      ID : " + i.getStoreId()));
        for(int i = 0; i < newStores.size(); i ++){
            final int useI = i;
            checkboxList.get(i).addItemListener(new ItemListener(){
                public void itemStateChanged(ItemEvent e){
                    newStores.get(useI).setIsVerified(!newStores.get(useI).isVerified());
                }
            });
        }
        newStoresPanel.setLayout(new GridLayout(newStores.size(), 1, 0, 0));
        for(JCheckBox i : checkboxList){
            i.setFont(new java.awt.Font("Tahoma", 1, 12));
            newStoresPanel.add(i);
        }
        newStoresPanel.setVisible(true);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        existingStoresPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        clearSelection = new javax.swing.JButton();
        selectAll = new javax.swing.JButton();
        verify = new javax.swing.JButton();
        newStoresPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        javax.swing.GroupLayout existingStoresPanelLayout = new javax.swing.GroupLayout(existingStoresPanel);
        existingStoresPanel.setLayout(existingStoresPanelLayout);
        existingStoresPanelLayout.setHorizontalGroup(
            existingStoresPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 455, Short.MAX_VALUE)
        );
        existingStoresPanelLayout.setVerticalGroup(
            existingStoresPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 274, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Store List");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(existingStoresPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(existingStoresPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("VIEW EXISTING STORES", jPanel1);

        clearSelection.setText("CLEAR SELECTION");
        clearSelection.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clearSelectionMouseClicked(evt);
            }
        });

        selectAll.setText("SELECT ALL");
        selectAll.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectAllMouseClicked(evt);
            }
        });

        verify.setText("VERIFY");
        verify.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                verifyMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout newStoresPanelLayout = new javax.swing.GroupLayout(newStoresPanel);
        newStoresPanel.setLayout(newStoresPanelLayout);
        newStoresPanelLayout.setHorizontalGroup(
            newStoresPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        newStoresPanelLayout.setVerticalGroup(
            newStoresPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 242, Short.MAX_VALUE)
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Store List");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(160, 160, 160)
                .addComponent(clearSelection)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(selectAll)
                .addGap(30, 30, 30)
                .addComponent(verify)
                .addGap(18, 18, 18))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(newStoresPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(newStoresPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(clearSelection)
                    .addComponent(selectAll)
                    .addComponent(verify))
                .addContainerGap())
        );

        jTabbedPane1.addTab("VERIFY STORES", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void clearSelectionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clearSelectionMouseClicked
        // TODO add your handling code here:
        for(JCheckBox i : checkboxList)
            i.setSelected(false);
    }//GEN-LAST:event_clearSelectionMouseClicked

    private void selectAllMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectAllMouseClicked
        // TODO add your handling code here:
        for(JCheckBox i : checkboxList)
            i.setSelected(true);
    }//GEN-LAST:event_selectAllMouseClicked

    private void verifyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_verifyMouseClicked
        // TODO add your handling code here:
        verifyClicked();
    }//GEN-LAST:event_verifyMouseClicked

    /**
     * @param args the command line arguments
     */
    /*
    public static void main(String args[]) {
        
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManageStore().setVisible(true);
            }
        });
    }*/

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton clearSelection;
    private javax.swing.JPanel existingStoresPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel newStoresPanel;
    private javax.swing.JButton selectAll;
    private javax.swing.JButton verify;
    // End of variables declaration//GEN-END:variables
}
