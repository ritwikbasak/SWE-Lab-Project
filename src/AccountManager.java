/**
 *
 * @author Rohit Basu
 */
import java.util.*;
import java.io.*;
public class AccountManager {
    private ArrayList<Account> accountList;
    private String infoLine;
    private SystemManager sysMgr;
    public AccountManager(SystemManager sysMgr){
        this.sysMgr = sysMgr;
        accountList = new ArrayList<>();
    }
    public boolean writeToFile(){
        try(FileWriter writer = new FileWriter(SystemManager.FILE_PATH + "account.txt");){
            writer.write(infoLine + "\n");
            for(Account i : accountList)
                writer.write(i + "\n");
        }
        catch(IOException e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
    public boolean init(){
        try(FileReader file = new FileReader(SystemManager.FILE_PATH + "account.txt");
            BufferedReader reader = new BufferedReader(file)){
            //ignore first line
            infoLine = reader.readLine();
            String oneLine;
            while((oneLine = reader.readLine()) != null){
                if(oneLine.equals("")) continue;
                accountList.add(new Account(oneLine));
            }
        }
        catch(IOException e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
    public void addCustomerAccount(String loginId, String email, String mobileNo, String password, String location) {
        accountList.add(new Account(loginId, email, mobileNo, password, location, false));
    }
    public void addChemistAccount(String loginId, String email, String mobileNo, String password, String location) {
        accountList.add(new Account(loginId, email, mobileNo, password, location, true));
    }
    public Account searchByLoginId(String id){
        for(Account acc : accountList)
            if(acc.getLoginId().equals(id))
                return acc;
        return null;
    }
    public ArrayList<Account> getAllAccounts(){
        return new ArrayList<>(accountList);
    }
    public static void main(String[] args){
        AccountManager testManager = new AccountManager(new SystemManager());
        
        //test number of records in file
        assert(testManager.accountList.size() == 4);
        
    }
}
