public class Jail implements Square {

    private int index;

    public Jail(int index){
        index = this.index;
    }
    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public int getPrice() {
        return 0;
    }

    @Override
    public String getName() {
        return "Just Visiting";
    }

    @Override
    public String toString() {
        return null;
    }

    @Override
    public Player getOwner() {
        return null;
    }

    @Override
    public int getRent(int numOwnedInSet, boolean hotel) {
        return 0;
    }

    @Override
    public int getSet() {
        return -1;
    }
}
