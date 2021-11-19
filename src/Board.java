import java.util.*;

public class Board {
    private List<Square> squares;
    private final int numSquares = 40;

    /**
     * Constructor for Board class
     */
    public Board(){
        squares = new ArrayList<>();
        createBoard();
    }

    /**
     * Creates the game board with 40 Square objects in an ArrayList
     *
     */
    private void createBoard() {
        squares.add(new Property("Go", 0, -1,-1,0,-1));
        squares.add(new Property("Ottawa U", 1, 60,1,2,50));
        squares.add(new Property("Empty", 2, -1,-1,0,-1));
        squares.add(new Property("Carleton U", 3, 60,1,2,50));
        squares.add(new Property("Empty", 4, -1,-1,0,-1));
        squares.add(new Property("Empty", 5, -1,-1,0,-1));
        squares.add(new Property("Chevrolet", 6, 100,2,3,50));
        squares.add(new Property("Empty", 7, -1,-1,0,3-1));
        squares.add(new Property("Honda", 8, 100,2,3,50));
        squares.add(new Property("Toyota", 9, 120,2,3, 50));
        squares.add(new Jail(10));
        squares.add(new Property("Home Depot", 11, 140,3,3,100));
        squares.add(new Property("Empty", 12, -1,-1,0,-1));
        squares.add(new Property("CostCo", 13, 140,3,3,100));
        squares.add(new Property("Walmart", 14, 160,3,3,100));
        squares.add(new Property("Empty", 15, -1,-1,0,-1));
        squares.add(new Property("Skip the Dishes", 16, 180,4,3,100));
        squares.add(new Property("Empty", 17, -1,-1,0,-1));
        squares.add(new Property("Lyft", 18, 180,4,3,100));
        squares.add(new Property("Uber", 19, 200,4,3,100));
        squares.add(new Property("Free Parking", 20, -1,-1,0,-1));
        squares.add(new Property("Ebay", 21, 220,5,3,150));
        squares.add(new Property("Empty", 22, -1,-1,0,-1));
        squares.add(new Property("Shopify", 23, 220,5,3,150));
        squares.add(new Property("Amazon", 24, 240,5,3,150));
        squares.add(new Property("Empty", 25, -1,-1,0,150));
        squares.add(new Property("Facebook", 26, 260,6,3,150));
        squares.add(new Property("LinkedIn", 27, 260,6,3,150));
        squares.add(new Property("Empty", 28, -1,-1,0,-1));
        squares.add(new Property("Twitter", 29, 280,6,3,150));
        squares.add(new Property("Go to Jail", 30, -1,-1,0,-1));
        squares.add(new Property("Disney Plus", 31, 300,7,3,200));
        squares.add(new Property("Crave", 32, 300,7,3,200));
        squares.add(new Property("Empty", 33, -1,-1,0,-1));
        squares.add(new Property("Netflix", 34, 320,7,3,200));
        squares.add(new Property("Empty", 35, -1,-1,0,-1));
        squares.add(new Property("Empty", 36, -1,-1,0,-1));
        squares.add(new Property("Samsung", 37, 350,8,2,-1));
        squares.add(new Property("Empty", 38, -1,-1,0,-1));
        squares.add(new Property("Apple", 39, 400,8,2,-1));
    }

    /**
     * Getter method that returns the Property at desired index in squares ArrayList
     * @param index
     * @return Property object
     */
    public Square getProperty(int index){
        return squares.get(index);
    }

    /**
     *  toString method
     *
     * @return String
     */
    public String toString(){
        String str = "";
        for(Square square: squares){
            str += square.toString();
            str += "\n";
        }
        return str;
    }
}