/**
 *
 * @author Ritwik Basak
 */
public class Store {
    private String storeId, name, location;
    private boolean verified;

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setIsVerified(boolean isVerified) {
        this.verified = isVerified;
    }

    public boolean isVerified() {
        return verified;
    }

    public String getStoreId() {
        return storeId;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }
    public Store(String oneRecord){
        String parts[] = oneRecord.split(";");
        name = parts[0].trim();
        location = parts[1].trim();
        storeId = parts[2].trim();
        verified = Boolean.parseBoolean(parts[3].trim().toLowerCase());
    }
    public String toString(){
        return name + ";" + location + ";" + storeId + verified;
    }
}
