/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ritwik Basak
 */
import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.time.*;

public class ChemistUI extends javax.swing.JFrame {
    
    private SystemManager sysMgr;
    private DisplayManager dispMgr;
    private ArrayList<Stock> storeStock ;
    private int current = 0;
    
    /**
     * Creates new form ChemistUI
     */
    private void refreshStock(){
        storeStock = sysMgr.getStock(sysMgr.getLoginId());
        ArrayList<Stock> nonEmpty = new ArrayList<>();
        for(Stock i : storeStock)
            if(i.getQuantity() != 0)
                nonEmpty.add(i);
        storeStock = nonEmpty;
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
    private void clearFields(){
        medicineNameField.setText("");
        medicineIdField.setText("");
        quantityField.setText("");
        DDField.setText("");
        MMField.setText("");
        YYYYField.setText("");
    }
    private void showFields(boolean show){
        medicineNameLabel.setVisible(show);
        medicineLabel.setVisible(show);
        quantityLabel.setVisible(show);
        expiryLabel.setVisible(show);
        medicineNameField.setVisible(show);
        medicineIdField.setVisible(show);
        quantityField.setVisible(show);
        DDField.setVisible(show);
        MMField.setVisible(show);
        YYYYField.setVisible(show);
        DDLabel.setVisible(show);
        MMLabel.setVisible(show);
        YYYYLabel.setVisible(show);
    }
    private void hideAll(){
        showFields(false);
        medicineNameError.setVisible(false);
        medicineError.setVisible(false);
        quantityError.setVisible(false);
        expiryError.setVisible(false);
        previousButton.setVisible(false);
        nextButton.setVisible(false);
        updateInventorySaveButton.setVisible(false);
    }
    
    private boolean expiryError(){
        LocalDate date = null;
        try{
            date = LocalDate.of(Integer.parseInt(YYYYField.getText()), Integer.parseInt(MMField.getText()), Integer.parseInt(DDField.getText()));
        }
        catch(Exception e){
            expiryError.setVisible(true);
            return true;
        }
        if(date.getYear() >= LocalDate.now().getYear())
        {
            expiryError.setVisible(false);
            return false;
        }
        expiryError.setVisible(true);
        return true;
    }
    private boolean medicineError(){
        if(medicineIdField.getText().length() != 0 && sysMgr.searchMedicineInFile(medicineIdField.getText()) != null)
        {
            medicineError.setVisible(false);
            medicineNameError.setVisible(false);
            return false;
        }
        medicineError.setVisible(true);
        medicineNameError.setVisible(true);
        return true;
    }
    private boolean quantityError(){
        int temp = 0;
        if(quantityField.getText().length() != 0)
            temp = Integer.parseInt(quantityField.getText());
        else
            quantityField.setText("0");
        if(quantityField.getText().length() != 0 && (temp > 0 || (updateInventorySaveButton.getText().equals("Done Editing") && temp >= 0)))
        {
            quantityError.setVisible(false);
            return false;
        }
        quantityError.setVisible(true);
        return true;
    }
    private boolean checkUpdateInventoryError(){
        return medicineError() | quantityError() | expiryError();
    }
    private void initializeUpdateInventory(){
        hideAll();
        nextButton.setEnabled(false);
        previousButton.setEnabled(false);
        DDField.setDocument(new IntegerRangeDocument(0, Integer.MAX_VALUE));
        MMField.setDocument(new IntegerRangeDocument(0, Integer.MAX_VALUE));
        YYYYField.setDocument(new IntegerRangeDocument(0, Integer.MAX_VALUE));
        quantityField.setDocument(new IntegerRangeDocument(0, Integer.MAX_VALUE));
    }
    public ChemistUI(SystemManager sysMgr, DisplayManager dispMgr) {
        
        lookSettingCode();
        this.sysMgr = sysMgr;
        this.dispMgr = dispMgr;
        
        addWindowListener(new WindowAdapter(){
                public void windowClosing(WindowEvent e){
                    
                    setVisible(false);
                    dispMgr.showHomeUI(true);
                }
            }
        );
        
        initComponents();
        setResizable(false);
        
        
        refreshStock();
        initializeUpdateInventory();
        
        locationErrorLabel.setVisible(false);
        
        storeNameField.addFocusListener(new FocusAdapter(){
            public void focusLost(FocusEvent fe){
                storeNameError();
            }
        });
        
        assert(sysMgr.searchByStoreId(sysMgr.getLoginId()) != null);
        storeNameField.setText(sysMgr.searchByStoreId(sysMgr.getLoginId()).getStoreName());
        storeIdField.setText(sysMgr.getLoginId());
        
        storeNameError.setVisible(false);
        
        String locationList[] = new String[LocationManager.size() + 1];
        locationList[0] = "NO LOCATION SELECTED";
        for(int i = 0; i < LocationManager.size(); i ++)
            locationList[i + 1] = LocationManager.getLocation(i);
        locationDropdown.setModel(new javax.swing.DefaultComboBoxModel<>(locationList));
        locationDropdown.setSelectedIndex(LocationManager.getIndex(sysMgr.searchByStoreId(sysMgr.getLoginId()).getLocation()) + 1);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabbedPane1 = new javax.swing.JTabbedPane();
        modifyDetailsPane = new javax.swing.JPanel();
        storeNameField = new javax.swing.JTextField();
        locationDropdown = new javax.swing.JComboBox<>();
        storeNameError = new javax.swing.JLabel();
        saveButton = new javax.swing.JButton();
        locationErrorLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        storeIdField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        updateInventoryPane = new javax.swing.JPanel();
        previousButton = new javax.swing.JButton();
        nextButton = new javax.swing.JButton();
        medicineNameField = new javax.swing.JTextField();
        medicineIdField = new javax.swing.JTextField();
        quantityField = new javax.swing.JTextField();
        medicineNameLabel = new javax.swing.JLabel();
        medicineLabel = new javax.swing.JLabel();
        quantityLabel = new javax.swing.JLabel();
        expiryLabel = new javax.swing.JLabel();
        DDField = new javax.swing.JTextField();
        MMField = new javax.swing.JTextField();
        YYYYField = new javax.swing.JTextField();
        DDLabel = new javax.swing.JLabel();
        MMLabel = new javax.swing.JLabel();
        YYYYLabel = new javax.swing.JLabel();
        quantityError = new javax.swing.JLabel();
        expiryError = new javax.swing.JLabel();
        medicineError = new javax.swing.JLabel();
        medicineNameError = new javax.swing.JLabel();
        editButton = new javax.swing.JButton();
        addButton = new javax.swing.JButton();
        updateInventorySaveButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        storeNameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                storeNameFieldActionPerformed(evt);
            }
        });
        storeNameField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                storeNameFieldKeyPressed(evt);
            }
        });

        locationDropdown.setToolTipText("");
        locationDropdown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                locationDropdownActionPerformed(evt);
            }
        });

        storeNameError.setForeground(new java.awt.Color(255, 0, 51));
        storeNameError.setText("This field cannot be empty");

        saveButton.setText("Save Changes");
        saveButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                saveButtonMouseClicked(evt);
            }
        });
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        locationErrorLabel.setForeground(new java.awt.Color(255, 0, 51));
        locationErrorLabel.setText(" Invalid Location");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Store Name");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Store ID");

        storeIdField.setEditable(false);
        storeIdField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                storeIdFieldActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Store Location");

        javax.swing.GroupLayout modifyDetailsPaneLayout = new javax.swing.GroupLayout(modifyDetailsPane);
        modifyDetailsPane.setLayout(modifyDetailsPaneLayout);
        modifyDetailsPaneLayout.setHorizontalGroup(
            modifyDetailsPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(modifyDetailsPaneLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(modifyDetailsPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(modifyDetailsPaneLayout.createSequentialGroup()
                        .addGroup(modifyDetailsPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(modifyDetailsPaneLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(saveButton))
                            .addGroup(modifyDetailsPaneLayout.createSequentialGroup()
                                .addGroup(modifyDetailsPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(storeNameError, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1)
                                    .addComponent(storeNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(69, 341, Short.MAX_VALUE)))
                        .addGap(39, 39, 39))
                    .addGroup(modifyDetailsPaneLayout.createSequentialGroup()
                        .addGroup(modifyDetailsPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(locationErrorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(modifyDetailsPaneLayout.createSequentialGroup()
                        .addGroup(modifyDetailsPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(locationDropdown, javax.swing.GroupLayout.Alignment.LEADING, 0, 202, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(storeIdField, javax.swing.GroupLayout.Alignment.LEADING))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        modifyDetailsPaneLayout.setVerticalGroup(
            modifyDetailsPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(modifyDetailsPaneLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(storeNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(storeNameError, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(storeIdField, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(locationDropdown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(locationErrorLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 184, Short.MAX_VALUE)
                .addComponent(saveButton)
                .addGap(23, 23, 23))
        );

        tabbedPane1.addTab("MODIFY STORE DETAILS", modifyDetailsPane);

        previousButton.setText("<< Previous");
        previousButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                previousButtonMouseClicked(evt);
            }
        });
        previousButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previousButtonActionPerformed(evt);
            }
        });

        nextButton.setText("Next >>");
        nextButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nextButtonMouseClicked(evt);
            }
        });

        medicineNameField.setEditable(false);
        medicineNameField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                medicineNameFieldFocusLost(evt);
            }
        });
        medicineNameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                medicineNameFieldActionPerformed(evt);
            }
        });
        medicineNameField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                medicineNameFieldKeyPressed(evt);
            }
        });

        medicineIdField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                medicineIdFieldFocusLost(evt);
            }
        });
        medicineIdField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                medicineIdFieldActionPerformed(evt);
            }
        });
        medicineIdField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                medicineIdFieldKeyPressed(evt);
            }
        });

        quantityField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                quantityFieldFocusLost(evt);
            }
        });
        quantityField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                quantityFieldKeyPressed(evt);
            }
        });

        medicineNameLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        medicineNameLabel.setText("Medicine Name");

        medicineLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        medicineLabel.setText("Medicine ID");

        quantityLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        quantityLabel.setText("Quantity");

        expiryLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        expiryLabel.setText("Expiry Date");

        DDField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                DDFieldFocusLost(evt);
            }
        });
        DDField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DDFieldActionPerformed(evt);
            }
        });
        DDField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DDFieldKeyPressed(evt);
            }
        });

        MMField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                MMFieldFocusLost(evt);
            }
        });
        MMField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MMFieldKeyPressed(evt);
            }
        });

        YYYYField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                YYYYFieldFocusLost(evt);
            }
        });
        YYYYField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                YYYYFieldKeyPressed(evt);
            }
        });

        DDLabel.setText(" DD");

        MMLabel.setText(" MM");

        YYYYLabel.setText(" YYYY");

        quantityError.setForeground(new java.awt.Color(255, 0, 51));
        quantityError.setText("Invalid Quantity");

        expiryError.setForeground(new java.awt.Color(255, 0, 51));
        expiryError.setText("Invalid Date");

        medicineError.setForeground(new java.awt.Color(255, 0, 51));
        medicineError.setText("Invalid Medicine ID");

        medicineNameError.setForeground(new java.awt.Color(255, 0, 51));
        medicineNameError.setText("Medicine Not Found");

        editButton.setText(" Edit Existing Stock ");
        editButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editButtonMouseClicked(evt);
            }
        });

        addButton.setText("    Add New Stock    ");
        addButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addButtonMouseClicked(evt);
            }
        });

        updateInventorySaveButton.setText("Save");
        updateInventorySaveButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                updateInventorySaveButtonMouseClicked(evt);
            }
        });
        updateInventorySaveButton.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                updateInventorySaveButtonKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout updateInventoryPaneLayout = new javax.swing.GroupLayout(updateInventoryPane);
        updateInventoryPane.setLayout(updateInventoryPaneLayout);
        updateInventoryPaneLayout.setHorizontalGroup(
            updateInventoryPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(updateInventoryPaneLayout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addComponent(previousButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(nextButton)
                .addGap(67, 67, 67))
            .addGroup(updateInventoryPaneLayout.createSequentialGroup()
                .addGroup(updateInventoryPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(updateInventoryPaneLayout.createSequentialGroup()
                        .addGap(184, 184, 184)
                        .addGroup(updateInventoryPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(medicineNameError)
                            .addComponent(medicineLabel)
                            .addComponent(medicineError)
                            .addComponent(quantityLabel)
                            .addComponent(expiryError)
                            .addComponent(quantityError)
                            .addComponent(medicineNameLabel)
                            .addComponent(expiryLabel)
                            .addGroup(updateInventoryPaneLayout.createSequentialGroup()
                                .addGroup(updateInventoryPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(DDField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(DDLabel))
                                .addGap(41, 41, 41)
                                .addGroup(updateInventoryPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(MMField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(MMLabel))
                                .addGap(39, 39, 39)
                                .addGroup(updateInventoryPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(YYYYLabel)
                                    .addComponent(YYYYField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(updateInventoryPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(quantityField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                                .addComponent(medicineIdField, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(medicineNameField, javax.swing.GroupLayout.Alignment.LEADING))))
                    .addGroup(updateInventoryPaneLayout.createSequentialGroup()
                        .addGap(273, 273, 273)
                        .addGroup(updateInventoryPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(editButton)
                            .addGroup(updateInventoryPaneLayout.createSequentialGroup()
                                .addComponent(addButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 115, Short.MAX_VALUE)
                                .addComponent(updateInventorySaveButton)))))
                .addGap(50, 50, 50))
        );
        updateInventoryPaneLayout.setVerticalGroup(
            updateInventoryPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, updateInventoryPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(medicineNameLabel)
                .addGap(3, 3, 3)
                .addComponent(medicineNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(medicineNameError)
                .addGap(20, 20, 20)
                .addComponent(medicineLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(medicineIdField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(medicineError)
                .addGap(23, 23, 23)
                .addComponent(quantityLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(quantityField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(quantityError)
                .addGap(23, 23, 23)
                .addComponent(expiryLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(updateInventoryPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(updateInventoryPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(MMLabel)
                        .addComponent(YYYYLabel))
                    .addComponent(DDLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(updateInventoryPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(YYYYField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(updateInventoryPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(DDField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(MMField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(expiryError)
                .addGap(20, 20, 20)
                .addComponent(editButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(updateInventoryPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addButton)
                    .addComponent(updateInventorySaveButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addGroup(updateInventoryPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(previousButton)
                    .addComponent(nextButton))
                .addGap(80, 80, 80))
        );

        tabbedPane1.addTab("UPDATE INVENTORY", updateInventoryPane);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        tabbedPane1.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void storeNameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_storeNameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_storeNameFieldActionPerformed

    private void storeNameFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_storeNameFieldKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            storeNameField.setFocusable(false);
            storeNameField.setFocusable(true);
        }
    }//GEN-LAST:event_storeNameFieldKeyPressed

    private void locationDropdownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_locationDropdownActionPerformed
        // TODO add your handling code here:
        locationError();
    }//GEN-LAST:event_locationDropdownActionPerformed

    private void saveButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveButtonMouseClicked
        // TODO add your handling code here:
        if(storeNameError() | locationError()) return;
        Store tempStore = sysMgr.searchByStoreId(sysMgr.getLoginId());
        tempStore.setIsVerified(false);
        tempStore.setStoreName(storeNameField.getText());
        tempStore.setLocation(locationDropdown.getItemAt(locationDropdown.getSelectedIndex()));
        JOptionPane.showMessageDialog(this, "Changes have been saved");
        tabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_saveButtonMouseClicked

    private boolean storeNameError(){
        if(storeNameField.getText().length() == 0){
            storeNameError.setVisible(true);
            return true;
        }
        storeNameError.setVisible(false);
        return false;
    }
    private boolean locationError(){
        String temp = locationDropdown.getItemAt(locationDropdown.getSelectedIndex());
        if(temp.equals("NO LOCATION SELECTED")){
            locationErrorLabel.setVisible(true);
            return true;
        }
        locationErrorLabel.setVisible(false);
        return false;
    }
    
    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_saveButtonActionPerformed

    private void previousButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previousButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_previousButtonActionPerformed

    private void medicineNameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_medicineNameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_medicineNameFieldActionPerformed

    private void DDFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DDFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DDFieldActionPerformed

    private void medicineIdFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_medicineIdFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_medicineIdFieldActionPerformed

    private void medicineNameFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_medicineNameFieldKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            medicineNameField.setFocusable(false);
            medicineNameField.setFocusable(true);
        }
    }//GEN-LAST:event_medicineNameFieldKeyPressed

    private void medicineIdFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_medicineIdFieldKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            medicineIdField.setFocusable(false);
            medicineIdField.setFocusable(true);
        }
    }//GEN-LAST:event_medicineIdFieldKeyPressed

    private void quantityFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_quantityFieldKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            quantityField.setFocusable(false);
            quantityField.setFocusable(true);
        }
    }//GEN-LAST:event_quantityFieldKeyPressed

    private void DDFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DDFieldKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            DDField.setFocusable(false);
            DDField.setFocusable(true);
        }
    }//GEN-LAST:event_DDFieldKeyPressed

    private void MMFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MMFieldKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            MMField.setFocusable(false);
            MMField.setFocusable(true);
        }
    }//GEN-LAST:event_MMFieldKeyPressed

    private void YYYYFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_YYYYFieldKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            YYYYField.setFocusable(false);
            YYYYField.setFocusable(true);
        }
    }//GEN-LAST:event_YYYYFieldKeyPressed

    private void medicineNameFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_medicineNameFieldFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_medicineNameFieldFocusLost

    private void medicineIdFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_medicineIdFieldFocusLost
        // TODO add your handling code here:
        boolean flag = medicineError();
        if(flag == false)
            medicineNameField.setText(sysMgr.searchMedicineInFile(medicineIdField.getText()).getName());
    }//GEN-LAST:event_medicineIdFieldFocusLost

    private void quantityFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_quantityFieldFocusLost
        // TODO add your handling code here:
        quantityError();
    }//GEN-LAST:event_quantityFieldFocusLost

    private void DDFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_DDFieldFocusLost
        // TODO add your handling code here:
        expiryError();
    }//GEN-LAST:event_DDFieldFocusLost

    private void MMFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_MMFieldFocusLost
        // TODO add your handling code here:
        expiryError();
    }//GEN-LAST:event_MMFieldFocusLost

    private void YYYYFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_YYYYFieldFocusLost
        // TODO add your handling code here:
        expiryError();
    }//GEN-LAST:event_YYYYFieldFocusLost

    private void addButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addButtonMouseClicked
        // TODO add your handling code here:
        showFields(true);
        updateInventorySaveButton.setVisible(true);
        addButton.setVisible(false);
        editButton.setVisible(false);
    }//GEN-LAST:event_addButtonMouseClicked

    private void updateInventorySaveButtonKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_updateInventorySaveButtonKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_updateInventorySaveButtonKeyPressed

    private void updateInventorySaveButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateInventorySaveButtonMouseClicked
        // TODO add your handling code here:
        boolean flag = checkUpdateInventoryError();
        if(flag == true) 
            return;
        String temp = updateInventorySaveButton.getText();
        if(temp.equals("Done Editing")){
            refreshStock();
            medicineIdField.setEditable(true);
            updateInventorySaveButton.setText("Save");
        }
        else{
            sysMgr.addStock(medicineIdField.getText(), sysMgr.getLoginId(), Integer.parseInt(quantityField.getText()), LocalDate.of(Integer.parseInt(YYYYField.getText()), Integer.parseInt(MMField.getText()), Integer.parseInt(DDField.getText())));
            refreshStock();
        }
        hideAll();
        clearFields();
        addButton.setVisible(true);
        editButton.setVisible(true);
    }//GEN-LAST:event_updateInventorySaveButtonMouseClicked

    private void displayCurrentStock(){
        Stock currentStock = storeStock.get(current);
        medicineNameField.setText(sysMgr.searchMedicineInFile(currentStock.getMedicineId()).getName());
        medicineIdField.setText(currentStock.getMedicineId());
        quantityField.setText(currentStock.getQuantity() + "");
        LocalDate currentDate = currentStock.getDate();
        DDField.setText(currentDate.getDayOfMonth() + "");
        MMField.setText(currentDate.getMonthValue() + "");
        YYYYField.setText(currentDate.getYear() + "");
        if(current > 0)
            previousButton.setEnabled(true);
        else
            previousButton.setEnabled(false);
        if(current < storeStock.size() - 1)
            nextButton.setEnabled(true);
        else
            nextButton.setEnabled(false);
    }
    private void editButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editButtonMouseClicked
        // TODO add your handling code here:
        //Bug Fix : Nothing Shown In "EDIT EXISTING STOCK" If Store Does Not Have Any Stock
        //Test Case Number : 2.3.2
        /**
         * If the store stock is empty:
         * No action would be performed on clicking the "Edit Existing Stock" button.
         */
        if(storeStock.size() == 0){
            JOptionPane.showMessageDialog(this, "No Stock Present To Edit", "", JOptionPane.ERROR_MESSAGE);
            return;
        }
        //End Bug Fix : Nothing Shown In "EDIT EXISTING STOCK" If Store Does Not Have Any Stock
        current = 0;
        medicineIdField.setEditable(false);
        addButton.setVisible(false);
        editButton.setVisible(false);
        displayCurrentStock();
        showFields(true);
        updateInventorySaveButton.setText("Done Editing");
        updateInventorySaveButton.setVisible(true);
        previousButton.setVisible(true);
        nextButton.setVisible(true);
    }//GEN-LAST:event_editButtonMouseClicked

    private void recordChanged(){
        Stock currentStock = storeStock.get(current);
        currentStock.setQuantity(Integer.parseInt(quantityField.getText()));
        currentStock.setDate(LocalDate.of(Integer.parseInt(YYYYField.getText()), Integer.parseInt(MMField.getText()), Integer.parseInt(DDField.getText())));
    }
    private void previousButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_previousButtonMouseClicked
        // TODO add your handling code here:
        if(previousButton.isEnabled() == false || checkUpdateInventoryError() == true) return;
        recordChanged();
        current --;
        displayCurrentStock();
    }//GEN-LAST:event_previousButtonMouseClicked

    private void nextButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nextButtonMouseClicked
        // TODO add your handling code here:
        if(nextButton.isEnabled() == false || checkUpdateInventoryError() == true) return;
        recordChanged();
        current ++;
        displayCurrentStock();
    }//GEN-LAST:event_nextButtonMouseClicked

    private void storeIdFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_storeIdFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_storeIdFieldActionPerformed

    /**
     * @param args the command line arguments
     */
    /*
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChemistUI().setVisible(true);
            }
        });
    }
    */
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField DDField;
    private javax.swing.JLabel DDLabel;
    private javax.swing.JTextField MMField;
    private javax.swing.JLabel MMLabel;
    private javax.swing.JTextField YYYYField;
    private javax.swing.JLabel YYYYLabel;
    private javax.swing.JButton addButton;
    private javax.swing.JButton editButton;
    private javax.swing.JLabel expiryError;
    private javax.swing.JLabel expiryLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JComboBox<String> locationDropdown;
    private javax.swing.JLabel locationErrorLabel;
    private javax.swing.JLabel medicineError;
    private javax.swing.JTextField medicineIdField;
    private javax.swing.JLabel medicineLabel;
    private javax.swing.JLabel medicineNameError;
    private javax.swing.JTextField medicineNameField;
    private javax.swing.JLabel medicineNameLabel;
    private javax.swing.JPanel modifyDetailsPane;
    private javax.swing.JButton nextButton;
    private javax.swing.JButton previousButton;
    private javax.swing.JLabel quantityError;
    private javax.swing.JTextField quantityField;
    private javax.swing.JLabel quantityLabel;
    private javax.swing.JButton saveButton;
    private javax.swing.JTextField storeIdField;
    private javax.swing.JLabel storeNameError;
    private javax.swing.JTextField storeNameField;
    private javax.swing.JTabbedPane tabbedPane1;
    private javax.swing.JPanel updateInventoryPane;
    private javax.swing.JButton updateInventorySaveButton;
    // End of variables declaration//GEN-END:variables
}
