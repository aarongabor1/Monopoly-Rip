import java.util.*;

public class Board {
    private List<Property> squares;
    private final int numSquares = 40;

    public Board(){
        squares = new ArrayList<>();
        createBoard();
    }

    private void createBoard() {
        squares.add(new Property("Empty", 0, -1));
        squares.add(new Property("Ottawa U", 1, 60));
        squares.add(new Property("Empty", 2, -1));
        squares.add(new Property("Carleton U", 3, 60));
        squares.add(new Property("Empty", 4, -1));
        squares.add(new Property("Empty", 5, -1));
        squares.add(new Property("Chevrolet", 6, 100));
        squares.add(new Property("Empty", 7, -1));
        squares.add(new Property("Honda", 8, 100));
        squares.add(new Property("Toyota", 9, 120));
        squares.add(new Property("Empty", 10, -1));
        squares.add(new Property("Home Depot", 11, 140));
        squares.add(new Property("Empty", 12, -1));
        squares.add(new Property("CostCo", 13, 140));
        squares.add(new Property("Walmart", 14, 160));
        squares.add(new Property("Empty", 15, -1));
        squares.add(new Property("Skip the Dishes", 16, 180));
        squares.add(new Property("Empty", 17, -1));
        squares.add(new Property("Lyft", 18, 180));
        squares.add(new Property("Uber", 19, 200));
        squares.add(new Property("Empty", 20, -1));
        squares.add(new Property("Ebay", 21, 220));
        squares.add(new Property("Empty", 22, -1));
        squares.add(new Property("Shopify", 23, 220));
        squares.add(new Property("Amazon", 24, 240));
        squares.add(new Property("Empty", 25, -1));
        squares.add(new Property("Facebook", 26, 260));
        squares.add(new Property("LinkedIn", 27, 260));
        squares.add(new Property("Empty", 28, -1));
        squares.add(new Property("Twitter", 29, 280));
        squares.add(new Property("Empty", 30, -1));
        squares.add(new Property("Disney Plus", 31, 300));
        squares.add(new Property("Crave", 32, 300));
        squares.add(new Property("Empty", 33, -1));
        squares.add(new Property("Netflix", 34, 320));
        squares.add(new Property("Empty", 35, -1));
        squares.add(new Property("Empty", 36, -1));
        squares.add(new Property("Samsung", 37, 350));
        squares.add(new Property("Empty", 38, -1));
        squares.add(new Property("Apple", 39, 400));
    }

    public Property getProperty(int index){
        return squares.get(index);
    }

    public String toString(){
        String str = "";
        for(Square square: squares){
            str += square.toString();
            str += "\n";
        }
        return str;
    }

    public static void main(String[] args) {
        Board b = new Board();
        System.out.println(b.toString());
    }
}
