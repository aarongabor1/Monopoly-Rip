public class Property implements Square {
    private String name;
    private int index;
    private int price;

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

    @Override
    public String toString() {
        return "Property{" +
                "name='" + name + '\'' +
                ", index=" + index +
                ", price=" + price +
                '}';
    }
}
