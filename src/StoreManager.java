/**
 *
 * @author Ritwik Basak
 */
import java.util.*;
import java.io.*;
public class StoreManager {
    private ArrayList<Store> storeList;
    private String infoLine;
    public StoreManager(){
        storeList = new ArrayList<>();
        readFile();
    }
    public boolean writeToFile(){
        try(FileWriter writer = new FileWriter("store.txt");){
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
    private boolean readFile(){
        try(FileReader file = new FileReader("store.txt");
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
        for(Store store : storeList)
            if(store.getStoreId().equals(storeId.trim()))
                return store;
        return null;
    }
    public static void main(String[] args){
        StoreManager testManager = new StoreManager();
        
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
        
        //test one record
        assert(testManager.storeList.get(0).getName().equals("store 1"));
        
        //display chemist UI
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChemistUI().setVisible(true);
            }
        });
    }
}
