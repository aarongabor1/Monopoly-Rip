public class Property implements Square {
    private String name;
    private int index;
    private int price;
    private Player owner;

    public Property(String name, int index, int price){
        this.name = name;
        this.index = index;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getIndex() {
        return index;
    }

    public int getPrice() {
        return price;
    }

    public int getRent(){
        return (int) (0.1 * price);
    }

    public void buyProperty(Player p){
        owner = p;
    }

    public Player getOwner(){
        if(owner == null) return null;
        return owner;
    }

    @Override
    public String toString() {
        return "Property{" +
                "name='" + name + '\'' +
                ", index=" + index +
                ", price=" + price +
                '}';
    }
}
