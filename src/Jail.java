/**
 * Represents the Jail Square
 * @author Braxton Martin
 */
public class Jail implements Square {

    public Jail(int index){
    }

    @Override
    public int getIndex() {
        return 10;
    }

    @Override
    public int getPrice() {
        return 0;
    }

    @Override
    public String getName() {
        return "Jail";
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
