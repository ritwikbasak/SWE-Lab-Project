/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ritam Ghosh
 */
import java.awt.event.*;
import java.io.File;
import java.util.*;
import javax.swing.*;
public class SearchForMedicine extends javax.swing.JFrame {

    /**
     * Creates new form SearchForMedicine
     */
    private final int LOW_AVAILABILITY = 20, MEDIUM_AVAILABILITY = 50, HIGH_AVAILABILITY = 100;
    private SystemManager sysMgr;
    private DisplayManager dispMgr;
    private void displayResults(){
        ArrayList<String[]> searchDisplayList = new ArrayList<>();
        String t = medicineField.getText();
        ArrayList<Medicine> t2 = sysMgr.searchByMedicineName(t);
        HashMap<String, Integer> t6 = new HashMap<>();
        for(Medicine t3 : t2){
            ArrayList<Stock> t4 = sysMgr.searchByMedicineId(t3.getId());
            for(Stock t5 : t4){
                String key = t5.getStoreId();
                int num = t5.getQuantity();
                if(!t6.containsKey(key))
                    t6.put(key, num);
                else
                    t6.put(key, t6.get(key) + num);
            }
        }
        String loc = selectLocationComboBox.getItemAt(selectLocationComboBox.getSelectedIndex());
        for(Map.Entry<String, Integer> entry : t6.entrySet()){
            Store a1 = sysMgr.searchByStoreId(entry.getKey());
            double dist = LocationManager.getDistance(loc, a1.getLocation());
            if(dist > Long.parseLong(radiusField.getText().trim()))
                continue;
            //code to display list of results in pane
            String[] arr = new String[4];
            arr[0] = t;
            arr[1] = a1.getStoreName();
            arr[2] = dist + "";
            arr[3] = calculateAvailability(entry.getValue());
            searchDisplayList.add(arr);
        }
        String[][] tableList = new String[searchDisplayList.size()][4];
        for(int i = 0; i < searchDisplayList.size(); i++)
            for(int j = 0; j < 4; j++)
                tableList[i][j] = searchDisplayList.get(i)[j];
        jTable1.setModel(new javax.swing.table.DefaultTableModel(tableList, new String [] {"MEDICINE NAME", "STORE NAME", "DISTANCE", "AVAILABILITY"}));
    }
    private String calculateAvailability(int qty){
        String res;
        //code to display the last field
        if(qty >= HIGH_AVAILABILITY)
            res = "HIGH";
        else if(qty >= MEDIUM_AVAILABILITY)
            res = "MEDIUM";
        else
            res = "LOW";
        return res;
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
    public SearchForMedicine(SystemManager sysMgr, DisplayManager dispMgr) {
        this.sysMgr = sysMgr;
        this.dispMgr = dispMgr;
        lookSettingCode();
        initComponents();
        
        addWindowListener(new WindowAdapter(){
                public void windowClosing(WindowEvent e){
                    
                    setVisible(false);
                    dispMgr.showHomeUI(true);
                }
            }
        );
        
        String locationList[] = new String[LocationManager.size() + 1];
        locationList[0] = "NO LOCATION SELECTED";
        for(int i = 0; i < LocationManager.size(); i ++)
            locationList[i + 1] = LocationManager.getLocation(i);
        selectLocationComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(locationList));
        selectLocationComboBox.setSelectedIndex(LocationManager.getIndex(sysMgr.searchByLoginId(sysMgr.getLoginId()).getLocation()) + 1);
        
        radiusField.setDocument(new LongRangeDocument(0, Long.MAX_VALUE));
        radiusField.setText("0");
        SerachRadiusError.setVisible(false);
        MedicineNameError.setVisible(false);
        InvalidLocationError.setVisible(false);
        jButton1.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent me){
                searchClicked();
            }
        });
        medicineField.addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent evt){
                if(evt.getKeyCode() == KeyEvent.VK_ENTER){
                    
                    medicineField.setFocusable(false);
                    medicineField.setFocusable(true);
                }
            }
        });        
        medicineField.addFocusListener(new FocusAdapter(){
            public void focusLost(FocusEvent fe){
                
                medicineError();
                    
            }
        });        
        radiusField.addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent evt){
                if(evt.getKeyCode() == KeyEvent.VK_ENTER){
                    
                    radiusField.setFocusable(false);
                    radiusField.setFocusable(true);
                }
            }
        });     
        radiusField.addFocusListener(new FocusAdapter(){
            public void focusLost(FocusEvent fe){
                if(radiusField.getText().length() == 0){
                    radiusField.setText("0");
                }
                long temp = Long.parseLong(radiusField.getText());
                if(temp == 0)
                    SerachRadiusError.setVisible(true);
                else
                    SerachRadiusError.setVisible(false);
            }
        });
    }

    private boolean medicineError(){
        if(medicineField.getText().length() == 0){
            MedicineNameError.setText("This field cannot be empty");
            MedicineNameError.setVisible(true);
            return true;
        }
        if(sysMgr.searchByMedicineName(medicineField.getText()).size() == 0){
            MedicineNameError.setText("Medicine Not Present");
            MedicineNameError.setVisible(true);
            return true;
        }
        MedicineNameError.setVisible(false);
        return false;
    }
    private void searchClicked(){
        long temp2 = Long.parseLong(radiusField.getText());
        boolean flag = false;
        if(temp2 == 0){
            SerachRadiusError.setVisible(true);
            flag = true;
        }
        flag = flag | medicineError();
        String temp = selectLocationComboBox.getItemAt(selectLocationComboBox.getSelectedIndex());
        if(temp.equals("NO LOCATION SELECTED")){
            InvalidLocationError.setVisible(true);
            flag = true;
        }

        if(flag){
            jTable1.setModel(new javax.swing.table.DefaultTableModel(null, new String [] {"MEDICINE NAME", "STORE NAME", "DISTANCE", "AVAILABILITY"}));
            return;
        }
        displayResults();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        MedicineNameLabel = new javax.swing.JLabel();
        medicineField = new javax.swing.JTextField();
        selectLocationComboBox = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        radiusField = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        InvalidLocationLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        MedicineNameError = new javax.swing.JLabel();
        SerachRadiusError = new javax.swing.JLabel();
        InvalidLocationError = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setBackground(new java.awt.Color(0, 204, 204));

        MedicineNameLabel.setText("MEDICINE NAME");

        selectLocationComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectLocationComboBoxActionPerformed(evt);
            }
        });
        selectLocationComboBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                selectLocationComboBoxKeyTyped(evt);
            }
        });

        jLabel2.setText("SEARCH RADIUS ( KM.)");

        radiusField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radiusFieldActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton1.setText("SEARCH");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setText("SEARCH RESULTS");

        InvalidLocationLabel.setText("SELECT LOCATION");

        jScrollPane1.setToolTipText("");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MEDICINE NAME", "STORE NAME", "DISTANCE", "AVAILABILITY"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        MedicineNameError.setForeground(new java.awt.Color(255, 0, 0));
        MedicineNameError.setText("This field can not be left empty.");

        SerachRadiusError.setForeground(new java.awt.Color(255, 0, 0));
        SerachRadiusError.setText("Distance can not be zero");

        InvalidLocationError.setForeground(new java.awt.Color(255, 0, 0));
        InvalidLocationError.setText("Invalid Location");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 609, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 21, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(MedicineNameLabel)
                                    .addComponent(InvalidLocationLabel))
                                .addGap(88, 88, 88))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(70, 70, 70)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(selectLocationComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(77, 77, 77))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(MedicineNameError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(medicineField, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(InvalidLocationError, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(SerachRadiusError, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(radiusField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(medicineField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MedicineNameLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(MedicineNameError, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(InvalidLocationLabel)
                    .addComponent(selectLocationComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(InvalidLocationError, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(radiusField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(SerachRadiusError, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(jLabel3)
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                .addGap(6, 6, 6))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void radiusFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radiusFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radiusFieldActionPerformed

    private void selectLocationComboBoxKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_selectLocationComboBoxKeyTyped
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            selectLocationComboBox.setFocusable(false);
            selectLocationComboBox.setFocusable(true);
        }
    }//GEN-LAST:event_selectLocationComboBoxKeyTyped

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
                new SearchForMedicine().setVisible(true);
            }
        });
    }*/

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel InvalidLocationError;
    private javax.swing.JLabel InvalidLocationLabel;
    private javax.swing.JLabel MedicineNameError;
    private javax.swing.JLabel MedicineNameLabel;
    private javax.swing.JLabel SerachRadiusError;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField medicineField;
    private javax.swing.JTextField radiusField;
    private javax.swing.JComboBox<String> selectLocationComboBox;
    // End of variables declaration//GEN-END:variables
}
