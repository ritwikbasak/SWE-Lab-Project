/**
 *
 * @author Ritam Ghosh
 */
import java.util.*;
import java.io.*;
public class MedicineManager {
    private SystemManager sysMgr;
    private ArrayList<Medicine> medicineList;
    private String infoLine;
    public MedicineManager(SystemManager sysMgr){
        this.sysMgr = sysMgr;
        medicineList = new ArrayList<>();
    }
    public boolean writeToFile(){
        try(FileWriter writer = new FileWriter("medicine.txt");){
            writer.write(infoLine + "\n");
            for(Medicine i : medicineList)
                writer.write(i + "\n");
        }
        catch(IOException e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
    public ArrayList<Medicine> searchByMedicineName(String medName){
        ArrayList<Medicine> res = new ArrayList<>();
        for(Medicine i : medicineList)
            if(i.getName().equalsIgnoreCase(medName.trim()))
                res.add(i);
        return res;
    }
    public boolean init(){
        try(FileReader file = new FileReader("medicine.txt");
            BufferedReader reader = new BufferedReader(file)){
            //ignore first line
            infoLine = reader.readLine();
            String oneLine;
            while((oneLine = reader.readLine()) != null){
                if(oneLine.equals("")) continue;
                medicineList.add(new Medicine(oneLine));
            }
        }
        catch(IOException e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
    
    public Medicine searchById(String id){
        for(Medicine med : medicineList){
            if(med.getId().equals(id.trim()))
                return med;
        }
        return null;
    }
    
    public static void main(String[] args){
        MedicineManager testManager = new MedicineManager(new SystemManager());
        
        //test number of records in file
        assert(testManager.medicineList.size() == 132);
        
        //check if all medicine ids are unique
        for(Medicine med : testManager.medicineList){
            System.out.println(med);
            assert(testManager.searchById(med.getId()) == med);
        }
    }
}
