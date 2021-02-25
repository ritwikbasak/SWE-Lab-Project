/**
 *
 * @author Ritwik Basak
 */
import java.util.*;
import java.io.*;
public class StoreManager {
    private SystemManager sysMgr;
    private ArrayList<Store> storeList;
    private String infoLine;
    
    public StoreManager(SystemManager sysMgr){
        storeList = new ArrayList<>();
        this.sysMgr = sysMgr;
    }
    public boolean writeToFile(){
        try(FileWriter writer = new FileWriter(SystemManager.FILE_PATH + "store.txt");){
            writer.write(infoLine + "\n");
            for(Store i : storeList)
                writer.write(i + "\n");
        }
        catch(IOException e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
    public ArrayList<Store> getAllStores(){
        return new ArrayList<>(storeList);
    }
    public boolean init(){
        try(FileReader file = new FileReader(SystemManager.FILE_PATH + "store.txt");
            BufferedReader reader = new BufferedReader(file)){
            //ignore first line
            infoLine = reader.readLine();
            String oneLine;
            while((oneLine = reader.readLine()) != null){
                if(oneLine.equals("")) continue;
                storeList.add(new Store(oneLine));
            }
        }
        catch(IOException e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
    public Store searchById(String storeId){
        for(Store store : storeList){
            if(store.getStoreId().equals(storeId.trim()))
                return store;
        }
        return null;
    }
    public void addStore(String storeId, String storeName, String location){
        storeList.add(new Store(storeId, storeName, location, false));
    }
    
    //Unit Testing
    public static void main(String[] args){
        StoreManager testManager = new StoreManager(new SystemManager());
        testManager.init();
        
        //test number of records in file
        assert(testManager.storeList.size() == 3);
        
        //test if all store ids are unique
        for(int i = 0; i < testManager.storeList.size(); i ++)
            for(int j = i + 1; j < testManager.storeList.size(); j ++)
                assert(!testManager.storeList.get(i).getStoreId().equals(testManager.storeList.get(j).getStoreId()));
        
        //test if all the locations are from the class "LocationManager"
        for(Store store : testManager.storeList){
            boolean locationFlag = false;
            for(int i = 0; i < LocationManager.size(); i ++)
                if(LocationManager.getLocation(i).equals(store.getLocation()))
                    locationFlag = true;
            assert(locationFlag == true);
        }
        
        //test if all the store IDs are from the file "account.txt"
        ArrayList<Account> accList = testManager.sysMgr.getAllAccounts();
        for(Store i : testManager.storeList){
            boolean flag = false;
            for(Account j : accList)
                if(j.isIsChemist() && i.getStoreId().equals(j.getLoginId()))
                    flag = true;
            assert(flag == true);
        }
        
        //test one record
        assert(testManager.storeList.get(0).getStoreName().equals("s1"));
        
    }
}
