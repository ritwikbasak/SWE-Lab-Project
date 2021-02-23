/**
 *
 * @author Ritwik Basak
 */
import javax.swing.*;
public class DisplayManager {
    private JFrame chemistUI, customerRegistrationUI, homeUI, loginUI, manageStore, registerStore, searchForMedicine, register;
    private SystemManager sysMgr;
    
    public void showRegister(boolean show){
        if(register == null) register = new Register(this);
        register.setVisible(show);
    }
    public void showChemistUI(boolean show){
        if(chemistUI == null) chemistUI = new ChemistUI(sysMgr, this);
        chemistUI.setVisible(show);
    }
    public void showCustomerRegistrationUI(boolean show){
        if(customerRegistrationUI == null) customerRegistrationUI = new CustomerRegistrationUI(sysMgr, this);
        customerRegistrationUI.setVisible(show);
    }
    public void showHomeUI(boolean show){
        if(homeUI == null) homeUI = new HomePageUI(sysMgr, this);
        homeUI.setVisible(show);
    }
    public void showLoginUI(boolean show){
        if(loginUI == null) loginUI = new LoginUI(sysMgr, this);
        loginUI.setVisible(show);
    }
    public void showManageStore(boolean show){
        if(manageStore == null) manageStore = new ManageStore(sysMgr, this);
        manageStore.setVisible(show);
    }
    public void showRegisterStore(boolean show){
        if(registerStore == null) registerStore = new RegisterStore(sysMgr, this);
        registerStore.setVisible(show);
    }
    public void showSearchForMedicine(boolean show){
        if(searchForMedicine == null) searchForMedicine = new SearchForMedicine(sysMgr, this);
        searchForMedicine.setVisible(show);
    }
    public DisplayManager(SystemManager sysMgr) {
        this.sysMgr = sysMgr;
    }
    public void showError(String error){
        JOptionPane.showMessageDialog(null, error, "", JOptionPane.ERROR_MESSAGE);
    }
}
