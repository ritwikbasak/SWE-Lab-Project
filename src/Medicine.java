/**
 *
 * @author Ritam Ghosh
 */
public class Medicine {
    private String name, id;
    private float weight;

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public float getWeight() {
        return weight;
    }
    public Medicine(String oneRecord){
        String parts[] = oneRecord.split(";");
        name = parts[0].trim();
        id = parts[1].trim();
        weight = Float.parseFloat(parts[2].trim());
    }
    public String toString(){
        return name + ";" + id + ";" + weight;
    }
}
