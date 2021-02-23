/**
 *
 * @author Rishabh Sharma
 */
import java.time.*;
public class Stock {

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Stock(String medicineId, String storeId, int quantity, LocalDate date) {
        this.medicineId = medicineId;
        this.storeId = storeId;
        this.quantity = quantity;
        this.date = date;
    }
    
    private String medicineId, storeId;
    private int quantity;
    private LocalDate date;

    public String getStoreId() {
        return storeId;
    }
    
    public String getMedicineId() {
        return medicineId;
    }

    public int getQuantity() {
        return quantity;
    }

    public LocalDate getDate() {
        return date;
    }
    
    public Stock(String oneLine){
        String parts[] = oneLine.split(";");
        medicineId = parts[0].trim();
        quantity = Integer.parseInt(parts[1].trim());
        String[] date = parts[2].trim().split("\\.");
        this.date = LocalDate.of(Integer.parseInt(date[2].trim()),Integer.parseInt(date[1].trim()),Integer.parseInt(date[0].trim()));
        storeId = parts[3].trim();
    }
    
    public String toString(){
        return medicineId + ";" + quantity + ";" + date.getDayOfMonth() + "." + date.getMonthValue() + "." + date.getYear() + ";" + storeId;
    }
}
