public class Property implements Square {
    private String name;
    private int index;
    private int price;
    private Player owner;
    private boolean owned;
    private boolean isFullSet;
    private final int set;

    /**
     * Constructor for Property class that creates a Property object
     *  @param name
     * @param index
     * @param price
     * @param set
     */
    public Property(String name, int index, int price, int set){
        this.name = name;
        this.index = index;
        this.price = price;
        this.set = set;
    }

    /**
     * Getter for the name of the Property
     *
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for the index of the Property
     *
     * @return int
     */
    public int getIndex() {
        return index;
    }

    /**
     * Getter for the price of the Property
     *
     * @return int
     */
    public int getPrice() {
        return price;
    }

    /**
     * Calculates and returns the rent of the Property based on the price of the Property multiplied by 0.1
     *
     * @return int
     */
    public int getRent(){
        if(isFullSet) return (int) (0.2 * price);
        else return (int) (0.1 * price);
    }

    /**
     * Sets the owner of the Property to the Player passed as parameter
     *
     * @param p
     */
    public void setOwner(Player p)
    {
        owner = null;
        owner = p;
    }

    /**
     * Getter for the owner of the Property
     *
     * @return Player object
     */
    public Player getOwner(){
        if(owner == null) return null;
        return owner;
    }

    /**
     * toString method
     *
     * @return String
     */
    @Override
    public String toString() {
        return "Property{" +
                "name='" + name + '\'' +
                ", index=" + index +
                ", price=" + price +
                '}';
    }
}