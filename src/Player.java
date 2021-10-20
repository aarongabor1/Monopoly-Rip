import java.util.*;

public class Player {
    private String name;
    private Piece piece;
    private List<Property> properties;
    private int balance;
    private Die[] dice;
    private Board board;

    public Player(String name, Piece piece, Board board){
        this.name = name;
        this.piece = piece;
        this.board = board;
        balance = 2000;
        dice[0] = new Die();
        dice[1] = new Die();
    }

    public String getName() {
        return name;
    }

    public Piece getPiece() {
        return piece;
    }

    public int getBalance() {
        return balance;
    }

    public void takeTurn(){
        System.out.println("Player: " + name + "turns");
        int roll;
        dice[0].roll();
        dice[1].roll();
        roll = dice[0].getValue() + dice[1].getValue();
        System.out.println("They rolled a " + roll);
        int destinationIndex = piece.getPosition().getIndex() + roll;
        Square destination = board.getSquare(destinationIndex);
        System.out.println("They landed on " + destination.toString() + "(index: " + destinationIndex + ")");
        piece.setPosition(destination);
    }

}
