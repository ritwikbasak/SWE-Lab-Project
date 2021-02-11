/**
 *
 * @author Rohit Basu
 */
import java.util.*;
import java.io.*;
public class AccountManager {
    private ArrayList<Account> accountList;
    public AccountManager(){
        accountList = new ArrayList<>();
        readFile();
    }
    private void readFile(){
        try(FileReader file = new FileReader("account.txt");
            BufferedReader reader = new BufferedReader(file)){
            //ignore first line
            reader.readLine();
            String oneLine;
            while((oneLine = reader.readLine()) != null){
                if(oneLine.equals("")) continue;
                accountList.add(new Account(oneLine));
            }
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
    public void addCustomerAccount(String loginId, String email, String mobileNo, String password, String location) {
        accountList.add(new Account(loginId, email, mobileNo, password, location, false));
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
        AccountManager testManager = new AccountManager();
        
        //test number of records in file
        assert(testManager.accountList.size() == 4);
        
    }
}
