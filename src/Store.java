/**
 *
 * @author Ritwik Basak
 */
public class Store {
    private String storeId, storeName, location;

    public Store(String storeId, String storeName, String location, boolean verified) {
        this.storeId = storeId;
        this.storeName = storeName;
        this.location = location;
        this.verified = verified;
    }
    private boolean verified;

    public void setStoreName(String name) {
        this.storeName = name;
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

    public String getStoreName() {
        return storeName;
    }

    public String getLocation() {
        return location;
    }
    public Store(String oneRecord){
        String parts[] = oneRecord.split(";");
        storeName = parts[0].trim();
        location = parts[1].trim();
        storeId = parts[2].trim();
        verified = Boolean.parseBoolean(parts[3].trim().toLowerCase());
    }
    public String toString(){
        return storeName + ";" + location + ";" + storeId + ";" + verified;
    }
}
