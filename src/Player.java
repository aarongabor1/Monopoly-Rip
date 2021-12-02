import java.util.*;

/**
 * Simulates a Monopoly Player
 * @author Cam Sommerville
 * @author Brady Norton
 * @author Braxton Martin
 * @author Aaron Gabor
 */
public class Player {
    private String name;
    private ArrayList<Property> properties;
    private int balance;
    private Die die1;
    private Die die2;
    private Board board;
    private Square position;
    private Property landedOnProperty;
    private boolean inJail = false;
    private int jailedTurns;
    //private boolean roll1Double = false, roll2Double = false, roll3Double = false;
    private int numDoubles;

    /**
     * Constructor for the Player class
     *
     * @param name
     * @param board
     */
    public Player(String name, Board board){
        this.name = name;
        this.board = board;
        balance = 2000;
        die1 = new Die();
        die2 = new Die();
        properties = new ArrayList<>();
        jailedTurns = 0;
    }

    /**
     * Getter for the Player name
     *
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Check whether Player is in jail
     * @return boolean
     */
    public boolean isInJail(){
        return inJail;
    }

    /**
     * Getter for the Player balance
     *
     * @return int
     */
    public int getBalance() {
        return balance;
    }

    /**
     * Getter for the Player position on the board
     *
     * @return Square
     */
    public Square getPosition() {
        return position;
    }

    public ArrayList<Property> getProperties()
    {
        return properties;
    }

    /**
     * Setter for the Player position
     *
     * @param position
     */
    public void setPosition(Square position) {
        this.position = position;
    }

    /**
     * When a user purchases a property this will remove the amount of "price" from their total balance
     * and will add the property "Prop" to an ArrayList that holds all their owned properties
     *
     * @param price
     * @param prop
     */
    public void buyProperty(int price, Property prop){
        balance -= price;
        properties.add(prop);
    }

    /**
     * Removes the amount "payment" passed as param from the Player's total balance
     *
     * @param payment
    **/

    public int payRent(int payment){
        if(balance - payment >= 0) {
            balance -= payment;
            return payment;
        }
        else
        {
            int num = payment - balance;
            balance = 0;
            return num;
        }
    }

    /**
     * Adds another players property to their own collection of properties
     *
     * @param property
     */
    public void takeProperty(Property property)
    {

        property.setOwner(this);
        this.properties.add(property);
    }

    /**
     * Adds the amount "payment" passed as param to the Player's total balance
     *
     * @param payment
     */
    public void acceptRent(int payment){
        balance += payment;
    }

    /**
     * Prints the state of the player:
     * Position on board
     * Balance
     * Owned properties
     */
    private void printPlayerInfo(){
        System.out.println("\n");
        System.out.println("Player: " + name + "'s turn");
        System.out.println("This player is at square " + position.getIndex());
        System.out.println("This player has a balance of: " + balance);
        System.out.println("This player owns the following properties: ");
        if(properties.size()>0) {
            for (Property p : properties) {
                System.out.print("  " + p.getName() + "(Set " + p.getSet() + ") ");
            }
            System.out.println("");
        }else{
            System.out.println("    No properties owned");
        }
    }

    /**
     * Returns True if the Player has rolled and their position index rolls over the max index of
     * Returns False if the Player has rolled and their position index does not roll over the max index
     * Once a Player reaches the index 39, the next Square on the Board is "Go"
     * Thus, their position value changes back to "0" instead of increasing
     *
     * @param roll
     * @return
     */
    public boolean checkPassedGo(int roll){
        if(roll + position.getIndex() < position.getIndex()) return true;
        return false;
    }

    /**
     * Rolls the Die and increases the Player's position index the amount shown on the dice
     * Prints the amount shown on the Die
     * Prints the Property they landed on
     */
    private void rollDice(){
        if(!inJail) {
            jailedTurns = 0;
            die1.roll();
            die2.roll();
            if(die1.getValue() == die2.getValue()) {
                numDoubles++;
                if (numDoubles == 3) {
                    inJail = true;
                    jailedTurns++;
                    Square jail = board.getProperty(10);
                    setPosition(jail);
                    numDoubles = 0;
                }
            }
            else {numDoubles = 0;}
            int lastIndex = position.getIndex();
            int destinationIndex = (position.getIndex() + die1.getValue() + die2.getValue()) % 40;
            Square destination = board.getProperty(destinationIndex);
            setPosition(destination);
            if(lastIndex > position.getIndex()){
                balance += 200;
            }
            if(position.getIndex() == 30){
                inJail = true;
            }
        }
        else {
            jailedTurns++;
            System.out.println("This Player is in jail");
            setPosition(board.getProperty(10));
            die1.roll();
            die2.roll();
            if(die1.getValue() == die2.getValue()) {
                System.out.println("This Player got out of jail!");
                inJail = false;
            }
        }
    }

    /**
     * Starts the Players turn by showing the Player their state and then rolling the dice
     *
     */
    public void takeTurn(){
        printPlayerInfo();
        rollDice();
    }

    /**
     * returns the roll of one of the players dice
     * @param whichDie
     * @return
     */
    public int getRoll(int whichDie)
    {
       if (whichDie ==1) return die1.getValue();
       if (whichDie == 2) return die2.getValue();
       return -1;
    }

    /**
     * Buys a house for the player
     * @param cost
     */
    public void buyHouse(int cost)
    {
        balance = balance - cost;
    }

    /**
     * Pay the Jailor to get out of Jail
     */
    public void payJailor(){
        balance -= 50;
        inJail = false;
    }

    /**
     * Get number of turn the player has been jailed
     * @return
     */
    public int getJailedTurns(){
        return jailedTurns;
    }

    /**
     * Get number doubles rolled in a row
     * @return
     */
    public int getNumDoubles(){
        return numDoubles;
    }
}