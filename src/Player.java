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

    public void buyProperty(int price, Property prop){
        balance -= price;
        properties.add(prop);
    }

    public void payRent(int price){

    }

    private void checkPassedGo(int roll){

    }

    public void takeTurn(){
        System.out.println("Player: " + name + "'s turn");
        System.out.println("This player is at square " + position.getIndex()+1);
        System.out.println("This player has a balance of: " + balance);
        System.out.println("This player owns the following properties: ");
        for(Property p : properties){
            System.out.print(p.getName() + " ");
        }
        int roll;
        dice[0].roll();
        dice[1].roll();
        roll = dice[0].getValue() + dice[1].getValue();
        System.out.println("They rolled a " + roll);
        int destinationIndex = (position.getIndex() + roll) % 40;
        Property destination = board.getProperty(destinationIndex);
        System.out.println("They landed on " + destination.toString() + "(index: " + destinationIndex + ")");
        setPosition(destination);
    }

}
