/**
 *
 * @author Ritam Ghosh
 */
import java.util.*;
import java.io.*;
public class MedicineManager {
    private ArrayList<Medicine> medicineList;
    private String infoLine;
    public MedicineManager(){
        medicineList = new ArrayList<>();
        readFile();
    }
    public void writeToFile(){
        try(FileWriter writer = new FileWriter("medicine.txt");){
            writer.write(infoLine + "\n");
            for(Medicine i : medicineList)
                writer.write(i + "\n");
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
    public ArrayList<Medicine> searchByMedicineName(String medName){
        ArrayList<Medicine> res = new ArrayList<>();
        for(Medicine i : medicineList)
            if(i.getName().equalsIgnoreCase(medName))
                res.add(i);
        return res;
    }
    private void readFile(){
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
        }
    }
    public Medicine searchById(String id){
        for(Medicine med : medicineList){
            if(med.getId().equals(id.trim()))
                return med;
        }
        return null;
    }
    public static void main(String[] args){
        MedicineManager testManager = new MedicineManager();
        
        //test number of records in file
        assert(testManager.medicineList.size() == 11);
        
        //check if all medicine ids are unique
        for(Medicine med : testManager.medicineList){
            System.out.println(med);
            assert(testManager.searchById(med.getId()) == med);
        }
    }
}
