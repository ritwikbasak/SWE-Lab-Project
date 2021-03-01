/**
 *
 * @author Ritwik Basak
 */
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class SystemManager {
    private AccountManager accMgr;
    private MedicineManager medMgr;
    private StoreManager storeMgr;
    private StockManager stockMgr;
    private DisplayManager dispMgr;
    public static final String FILE_PATH = "data/";
    
    private String loginId = "INVALID";//set "INVALID"

    public String getLoginId() {
        return loginId;
    }
    public void setStoreId(String storeId) {
        this.loginId = storeId;
    }
    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }
    public ArrayList<Account> getAllAccounts(){
        return accMgr.getAllAccounts();
    }
    public ArrayList<Store> getAllStores(){
        return storeMgr.getAllStores();
    }
    public ArrayList<Stock> searchByMedicineId(String medId){
        return stockMgr.searchByMedicineId(medId);
    }
    public ArrayList<Stock> getParticularStock(String storeId, String medId){
        return stockMgr.getParticularStock(storeId, medId);
    }
    public Store searchByStoreId(String storeId){
        return storeMgr.searchById(storeId);
    }
    public Medicine searchMedicineInFile(String medId){
        return medMgr.searchById(medId);
    }
    public void addStock(String medicineId, String storeId, int quantity, LocalDate date){
        stockMgr.addStock(medicineId, storeId, quantity, date);
    }
    public ArrayList<Stock> getStock(String storeId){
        return stockMgr.getStock(storeId);
    }
    public ArrayList<Medicine> searchByMedicineName(String medName){
        return medMgr.searchByMedicineName(medName);
    }
    public void addCustomerAccount(String loginId, String email, String mobileNo, String password, String location) {
        accMgr.addCustomerAccount(loginId, email, mobileNo, password, location);
    }
    public void addChemistAccount(String loginId, String email, String mobileNo, String password, String location) {
        accMgr.addChemistAccount(loginId, email, mobileNo, password, location);
    }
    public Account searchByLoginId(String id){
        return accMgr.searchByLoginId(id);
    }
    public void addStore(String storeId, String storeName, String location){
        storeMgr.addStore(storeId, storeName, location);
    }
    
    private boolean initAll(){
        accMgr = new AccountManager(this);
        medMgr = new MedicineManager(this);
        storeMgr = new StoreManager(this);
        stockMgr = new StockManager(this);
        boolean flag = accMgr.init() & medMgr.init() & storeMgr.init() & stockMgr.init();
        if(flag == false){
            dispMgr.showError("Initialization Failed");
            System.exit(0);
            return false;
        }
        dispMgr = new DisplayManager(this);
        
        return true;
    }
    public boolean flushData(){
        boolean flag = accMgr.writeToFile() & medMgr.writeToFile() & storeMgr.writeToFile() & stockMgr.writeToFile();
        if(flag == false){
            dispMgr.showError("Failed To Save Data");
        }
        return flag;
    }
    public void closeProgram(){
        flushData();
        System.exit(0);
    }
    public static void main(String[] args){
        try{
            SystemManager ob = new SystemManager();
            ob.initAll();
            ob.dispMgr.showHomeUI(true);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Program Terminated : " + e.getMessage(), "", JOptionPane.ERROR_MESSAGE);
        }
    }
}
