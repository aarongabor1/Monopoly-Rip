import java.util.*;

public class Player {
    private String name;
    private ArrayList<Property> properties;
    private int balance;
    private Die die1;
    private Die die2;
    private Board board;
    private Square position;

    public Player(String name, Board board){
        this.name = name;
        this.board = board;
        balance = 2000;
        die1 = new Die();
        die2 = new Die();
        properties = new ArrayList<>();
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

    public void setPosition(Property position) {
        this.position = position;
    }

    public void buyProperty(int price, Property prop){
        balance -= price;
        properties.add(prop);
    }

    public void payRent(int payment){
        balance -= payment;
    }

    public void acceptRent(int payment){
        balance += payment;
    }

    private void printPlayerInfo(){
        System.out.println("Player: " + name + "'s turn");
        System.out.println("This player is at square " + position.getIndex());
        System.out.println("This player has a balance of: " + balance);
        System.out.println("This player owns the following properties: ");
        if(properties.size()>0) {
            for (Property p : properties) {
                System.out.print("  " + p.getName() + " ");
            }
            System.out.println("");
        }else{
            System.out.println("    No properties owned");
        }
    }
    public boolean checkPassedGo(int roll){
        if(roll + position.getIndex() < position.getIndex()) return true;
        return false;
    }

    private void rollDice(){
        int roll;
        die1.roll();
        die2.roll();
        roll = die1.getValue() + die2.getValue();
        System.out.println("They rolled a " + roll);
        if(checkPassedGo(roll)) balance += 200;
        int destinationIndex = (position.getIndex() + roll) % 40;
        Property destination = board.getProperty(destinationIndex);
        System.out.println("They landed on " + destination.toString() + "(index: " + destinationIndex + ")");
        setPosition(destination);
    }

    public void takeTurn(){
        printPlayerInfo();
        rollDice();
    }

}