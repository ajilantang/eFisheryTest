package ajilantang.wificonnect.Model;

/**
 * Created by ajilantang on 05/09/17.
 */

/**
 * connection model
 */
public class Connection {
    private String name;
    private int strength;
    private boolean isWPA;
    private boolean isConnected;

    public Connection(String name, int strength, boolean isWPA,boolean isConnected) {
        this.name = name;
        this.strength = strength;
        this.isWPA = isWPA;
        this.isConnected = isConnected;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public boolean getIsWPA() {
        return isWPA;
    }

    public void setIsWPA(boolean isWPA) {
        this.isWPA = isWPA;
    }

    public boolean getIsConnected() {
        return isConnected;
    }

    public void setIsConnected(boolean isConnected) {
        this.isConnected = isConnected;
    }
}
