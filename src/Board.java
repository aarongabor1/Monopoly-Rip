import java.util.*;

public class Board {
    private List<Square> squares;
    private final int numSquares = 40;

    public Board(){
        squares = new ArrayList<>();
        createBoard();
    }

    private void createBoard() {
        squares.add(new Property("Empty", 0, -1));
        squares.add(new Property("Toronto", 1, 100));
        squares.add(new Property("Quebec City", 2, 100));
        squares.add(new Property("Montreal", 3, 100));
        squares.add(new Property("Calgary", 4, 100));
        squares.add(new Property("Edmonton", 5, 100));
        squares.add(new Property("Winnipeg", 6, 100));
        squares.add(new Property("Vancouver", 7, 100));
        squares.add(new Property("Halifax", 8, 100));
        squares.add(new Property("Gatineau", 9, 100));
    }

    public Square getSquare(int index){
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
