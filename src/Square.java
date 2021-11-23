/**
 * Interface for all squares on the board
 * @author Cam Sommerville
 */
public interface Square {
    int getIndex();
    int getPrice();
    String getName();
    String toString();
    Player getOwner();

    int getRent(int numOwnedInSet, boolean hotel);

    int getSet();
}