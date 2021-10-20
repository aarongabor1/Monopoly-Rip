import java.util.*;

public class Player {
    private String name;
    private List<Property> properties;
    private int balance;
    private Die[] dice;
    private Board board;
    private Square position;

    public Player(String name, Board board){
        this.name = name;
        this.board = board;
        balance = 2000;
        dice[0] = new Die();
        dice[1] = new Die();
    }

    public String getName() {
        return name;
    }

    public int getBalance() {
        return balance;
    }

    public Square getPosition() {
        return position;
    }

    public void setPosition(Square position) {
        this.position = position;
    }

    public void takeTurn(){
        System.out.println("Player: " + name + "turns");
        int roll;
        dice[0].roll();
        dice[1].roll();
        roll = dice[0].getValue() + dice[1].getValue();
        System.out.println("They rolled a " + roll);
        int destinationIndex = (position.getIndex() + roll) % 40;
        Square destination = board.getSquare(destinationIndex);
        System.out.println("They landed on " + destination.toString() + "(index: " + destinationIndex + ")");
        setPosition(destination);
    }

}
