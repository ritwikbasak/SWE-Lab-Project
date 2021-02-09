/**
 *
 * @author Rohit Basu
 */
import java.util.regex.Pattern;

public class Account {
    private String storeName, chemistName, email, mobileNo, password, storeLocation;

    public String getStoreName() {
        return storeName;
    }

    public String getChemistName() {
        return chemistName;
    }

    public String getEmail() {
        return email;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public String getPassword() {
        return password;
    }

    public String getStoreLocation() {
        return storeLocation;
    }
    public Account(String oneLine){
        String[] parts = oneLine.split(";");
        storeName = parts[0].trim();
        chemistName = parts[1].trim();
        email = parts[2].trim();
        mobileNo = parts[3].trim();
        password = parts[4].trim();
        storeLocation = parts[5].trim();
    }
    private boolean isValidEmail(String email){
        return Pattern.compile("^(?=.{1,64}@)[\\p{L}0-9_-]+(\\.[\\p{L}0-9_-]+)*@"
        + "[^-][\\p{L}0-9-]+(\\.[\\p{L}0-9-]+)*(\\.[\\p{L}]{2,})$").matcher(email).find();
    }
    public String toString(){
        return storeName + ";" + chemistName + ";" + email + ";" + mobileNo + ";" + password + ";" + storeLocation;
    }
}
