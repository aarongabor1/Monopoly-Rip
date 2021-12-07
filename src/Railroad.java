import java.io.Serializable;

/**
 * Represents a Railroad
 * @author Braxton Martin
 */
public class Railroad extends Property implements Serializable {
    /**
     * Constructor for Property class that creates a Property object
     *
     * @param name
     * @param index
     * @param price
     * @param set
     * @param numInSet
     * @param housePrice
     */
    public Railroad(String name, int index, int price, int set, int numInSet, int housePrice) {
        super(name, index, price, set, numInSet, housePrice);
    }

    public String getName() {
        return super.getName();
    }

    /**
     * Getter for the index of the Property
     *
     * @return int
     */
    public int getIndex() {
        return super.getIndex();
    }

    /**
     * Getter for the price of the Property
     *
     * @return int
     */
    public int getPrice() {
        return super.getPrice();
    }

    public int getRent(int numOwnedInSet){
        return (25*numOwnedInSet);
    }

    public void setOwner(Player p)
    {
        super.setOwner(null);
        super.setOwner(p);
    }

    /**
     * Getter for the owner of the Property
     *
     * @return Player object
     */
    public Player getOwner(){
        if(super.getOwner() == null) return null;
        return super.getOwner();
    }

    @Override
    public String toString() {
        return "Railroad{" +
                "name='" + super.getName() + '\'' +
                ", index=" + super.getIndex() +
                ", price=" + super.getPrice() +
                '}';
    }

    public int getSet(){
        return super.getSet();
    }

    /**
     * Getter for numInSet
     *
     * @return int
     */
    public int getNumInSet(){
        return super.getNumInSet();
    }
}
