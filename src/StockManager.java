/**
 *
 * @author Rishabh Sharma
 */
import java.util.*;
import java.io.*;
import java.time.LocalDate;
public class StockManager {
    private ArrayList<Stock> stockList;
    private String infoLine;
    private SystemManager sysMgr;
    public ArrayList<Stock> getParticularStock(String storeId, String medId){
        ArrayList<Stock> medStock = new ArrayList<>();
        for(Stock i : stockList)
            if(i.getMedicineId().equals(medId.trim()) && i.getStoreId().equals(storeId.trim()))
                medStock.add(i);
        return medStock;
    }
    public boolean writeToFile(){
        try(FileWriter writer = new FileWriter(SystemManager.FILE_PATH + "stock.txt");){
            writer.write(infoLine + "\n");
            for(Stock i : stockList)
                if(i.getQuantity() != 0)
                    writer.write(i + "\n");
        }
        catch(IOException e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
    public ArrayList<Stock> searchByMedicineId(String medId){
        ArrayList<Stock> medStock = new ArrayList<>();
        for(Stock i : stockList)
            if(i.getMedicineId().equals(medId.trim()))
                medStock.add(i);
        return medStock;
    }
    public boolean init(){
        try(FileReader file = new FileReader(SystemManager.FILE_PATH + "stock.txt");
            BufferedReader reader = new BufferedReader(file)){
            //ignore first line
            infoLine = reader.readLine();
            String oneLine;
            while((oneLine = reader.readLine()) != null){
                if(oneLine.equals("")) continue;
                stockList.add(new Stock(oneLine));
            }
        }
        catch(IOException e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
    public void addStock(String medicineId, String storeId, int quantity, LocalDate date){
        stockList.add(new Stock(medicineId, storeId, quantity, date));
    }
    public StockManager(SystemManager sysMgr){
        this.sysMgr = sysMgr;
        stockList = new ArrayList<>();
    }
    public ArrayList<Stock> getStock(String storeId){
        ArrayList<Stock> storeStock = new ArrayList<>();
        for(Stock stock : stockList){
            if(stock.getStoreId().equals(storeId.trim()))
                storeStock.add(stock);
        }
        return storeStock;
    }
    public static void main(String[] args){
        StockManager testManager = new StockManager(new SystemManager());
        
        //test number of records in file
        assert(testManager.stockList.size() == 6);
        
        //check if all the medicine IDs in the stock are from the medicine list
        for(Stock stock : testManager.stockList)
            assert(testManager.sysMgr.searchMedicineInFile(stock.getMedicineId()) != null);
        
        //check if all the store IDs are from the store list
        for(Stock stock : testManager.stockList)
            assert(testManager.sysMgr.searchByStoreId(stock.getStoreId()) != null);
    }
}
