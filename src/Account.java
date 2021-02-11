/**
 *
 * @author Rohit Basu
 */
import java.util.regex.Pattern;

public class Account {
    private String loginId, email, mobileNo, password, location;

    public Account(String loginId, String email, String mobileNo, String password, String location,boolean isChemist) {
        this.loginId = loginId;
        this.email = email;
        this.mobileNo = mobileNo;
        this.password = password;
        this.location = location;
        this.isChemist = isChemist;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLoginId() {
        return loginId;
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

    public String getLocation() {
        return location;
    }

    public boolean isIsChemist() {
        return isChemist;
    }
    private boolean isChemist;

    public Account(String oneLine){
        String[] parts = oneLine.split(";");
        loginId = parts[0].trim();
        isChemist = Boolean.parseBoolean(parts[1].trim().toLowerCase());
        email = parts[2].trim();
        mobileNo = parts[3].trim();
        password = parts[4].trim();
        location = parts[5].trim();
        if(!isValidEmail(email))
            throw new NumberFormatException();
        if(!isValidMobileNumber(mobileNo))
            throw new NumberFormatException();
    }
    private boolean isValidMobileNumber(String number){
        Long.parseLong(number);
        return (number.length() == 10 || (number.length() == 13 && number.substring(0,3).equals("+91")));
    }
    private boolean isValidEmail(String email){
        return Pattern.compile("^(?=.{1,64}@)[\\p{L}0-9_-]+(\\.[\\p{L}0-9_-]+)*@"
        + "[^-][\\p{L}0-9-]+(\\.[\\p{L}0-9-]+)*(\\.[\\p{L}]{2,})$").matcher(email).find();
    }
    public String toString(){
        return loginId + ";" + isChemist + ";" + email + ";" + mobileNo + ";" + password + ";" + location;
    }
}
