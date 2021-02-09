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
    
    public void writeToFile(){
        try(FileWriter writer = new FileWriter("stock.txt");){
            writer.write(infoLine + "\n");
            for(Stock i : stockList)
                if(i.getQuantity() != 0)
                    writer.write(i + "\n");
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
    public ArrayList<Stock> searchByMedicineId(String medId){
        ArrayList<Stock> medStock = new ArrayList<>();
        for(Stock i : stockList)
            if(i.getMedicineId().equals(medId))
                medStock.add(i);
        return medStock;
    }
    private void readFile(){
        try(FileReader file = new FileReader("stock.txt");
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
        }
    }
    public void addStock(String medicineId, String storeId, int quantity, LocalDate date){
        stockList.add(new Stock(medicineId, storeId, quantity, date));
    }
    public StockManager(){
        stockList = new ArrayList<>();
        readFile();
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
        StockManager testManager = new StockManager();
        
        //test number of records in file
        assert(testManager.stockList.size() == 6);
        
        //check if all the medicine IDs in the stock are from the medicine list
        MedicineManager medManager = new MedicineManager();
        for(Stock stock : testManager.stockList)
            assert(medManager.searchById(stock.getMedicineId()) != null);
        
        //check if all the store IDs are from the store list
        StoreManager storeMgr = new StoreManager();
        for(Stock stock : testManager.stockList)
            assert(storeMgr.searchById(stock.getStoreId()) != null);
    }
}
