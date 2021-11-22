public class Property implements Square {
    private String name;
    private int index;
    private int price;
    private Player owner;
    private boolean owned;
    private boolean isFullSet;
    private final int set;
    private int numOfhouses;
    private boolean hotel;
    private int housePrice;
    private int numInSet;

    /**
     * Constructor for Property class that creates a Property object
     *  @param name
     * @param index
     * @param price
     * @param set
     */
    public Property(String name, int index, int price, int set, int numInSet, int housePrice){
        this.name = name;
        this.index = index;
        this.price = price;
        this.set = set;
        this.numInSet = numInSet;
        this.housePrice = housePrice;
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
    public int getRent(int numOwnedInSet, boolean hotel){
        int tempPrice = price;
        if(isFullSet) tempPrice *=2;
        if(hotel) return (int) (0.5 * tempPrice);
        else return (int) ((0.1 * tempPrice) + (numOfhouses * tempPrice * 0.5));
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

    /**
     * Getter for house
     *
     * @return boolean
     */
    public int getHouses(){
        return numOfhouses;
    }

    /**
     * Setter for house
     *
     * @param houseBuilt
     */
    public void setHouse(boolean houseBuilt){
        numOfhouses++;
    }

    /**
     * Getter for hotel
     *
     * @return boolean
     */
    public boolean hasHotel(){
        return hotel;
    }

    /**
     * Setter for hotel
     *
     * @param hotelBuilt
     */
    public void setHotel(boolean hotelBuilt){
        hotel = hotelBuilt;
    }

    /**
     * Getter for set
     *
     * @return int
     */
    public int getSet(){
        return set;
    }

    /**
     * Getter for numInSet
     *
     * @return int
     */
    public int getNumInSet(){
        return numInSet;
    }

    /**
     * Getter for housePrice
     *
     * @return int
     */
    public int getHousePrice(){
        return housePrice;
    }

    public void setFullSetTrue(){
        isFullSet = true;
    }

}