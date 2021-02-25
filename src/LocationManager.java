/**
 *
 * @author Ritwik Basak
 */
import java.io.*;
public class LocationManager {
    private static final String[] LOCATIONS = {"SALT LAKE", "NEW TOWN", "HOWRAH", "ULTADANGA"};
    private static double distances[][];
    static{
        distances = new double[LocationManager.size()][LocationManager.size()];
        try(FileReader file = new FileReader(SystemManager.FILE_PATH + "location.txt");
            BufferedReader reader = new BufferedReader(file)){
            String oneLine;
            int i = 0;
            while((oneLine = reader.readLine()) != null){
                if(oneLine.equals("")) continue;
                String[] parts = oneLine.split("\t");
                for(int j = 0; j < distances.length; j ++)
                    distances[i][j] = ((int)(Double.parseDouble(parts[j]) * 1000))/1000.0;
                i ++;
            }
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
        
        for(int i = 0; i < LocationManager.size(); i ++){
            distances[i][i] = 0;
            for(int j = i + 1; j < LocationManager.size(); j ++)
                distances[j][i] = distances[i][j];
        }
    }
    public static int size(){
        return LOCATIONS.length;
    }
    public static String getLocation(int index){
        return LOCATIONS[index];
    }
    public static int getIndex(String location){
        for(int i = 0; i < LocationManager.size(); i ++)
            if(LOCATIONS[i].equalsIgnoreCase(location.trim()))
                return i;
        return -1;
    }
    public static double getDistance(String locationOne, String locationTwo){
        int index1 = -1, index2 = -1;
        for(int i = 0; i < LocationManager.size(); i ++)
            if(LocationManager.getLocation(i).equalsIgnoreCase(locationOne.trim())){
                index1 = i;
                break;
            }
        for(int i = 0; i < LocationManager.size(); i ++)
            if(LocationManager.getLocation(i).equalsIgnoreCase(locationTwo.trim())){
                index2 = i;
                break;
            }
        if(index1 == -1 || index2 == -1)
            return -1;
        return distances[index1][index2];
    }
    public static void main(String[] args){
        for(double[] i : distances){
            for(double j : i)
                System.out.print(j + "\t");
            System.out.println();
        }
    }
}
