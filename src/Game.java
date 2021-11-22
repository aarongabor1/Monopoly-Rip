import java.util.*;

public class Game
{
    private List<Player> players;
    private Board board;

    private boolean running;
    private boolean playerWon;

    private Player currentPlayer;
    private int currentTurn;
    private int startingPlayerAmount;

    /**
     *  Constructor for Game class
     *
     *  Initiates: Board object, ArrayList of Player objects
     */
    public Game()
    {
        board = new Board();
        players = new ArrayList<>();
    }


    /**
     * Creates a new Player object on the board using the name parameter as it's name
     *
     * The new Player object is then added to the players ArrayList
     *
     * @param name
     */
    public void addPlayer(String name)
    {
        Player p = new Player(name, board);
        players.add(p);
    }

    /**
     * Checks if Player p balance is less than 0
     *
     * If the players balance is less than 0, they are removed from the players ArrayList
     * This eliminates them from the game
     *
     * @param p
     * @param p2
     */
    private void bankrupt(Player p, Player p2){
        if(p.getBalance() == 0){
            System.out.println(p.getName() + " has gone bankrupt. They are removed from the game");
            for(int i = 0; i < p.getProperties().size()-1; i++)
            {
                p2.takeProperty(p.getProperties().get(i));
                p.getProperties().remove(i);
            }
            players.remove(p);
        }
        startingPlayerAmount--;
        if(startingPlayerAmount == 1){playerWon = true;}
    }

    /**
     * Pay rent from one user to another
     */
    public void payRent(){
        int numInSet;
        int rent;
        if(isSquareRailroad()){
            numInSet = getNumInSetOwned(((Railroad) currentPlayer.getPosition()).getOwner(), (Railroad) currentPlayer.getPosition());
            rent = ((Railroad) getLandedOnProperty()).getRent(numInSet);
            int rentPayed = players.get(currentTurn).payRent(rent);
            ((Railroad)getLandedOnProperty()).getOwner().acceptRent(rentPayed);
            if(rent != rentPayed)
                bankrupt(players.get(currentTurn), ((Railroad)getLandedOnProperty()).getOwner());
        }else if(isSquareProperty()){
            numInSet = getNumInSetOwned(((Property) currentPlayer.getPosition()).getOwner(), (Property) currentPlayer.getPosition());
            boolean hotel = ((Property) currentPlayer.getPosition()).hasHotel();
            rent = ((Property) getLandedOnProperty()).getRent(numInSet, hotel);
            int rentPayed = players.get(currentTurn).payRent(rent);
            ((Property)getLandedOnProperty()).getOwner().acceptRent(rentPayed);
            if(rent != rentPayed)
                bankrupt(players.get(currentTurn), ((Property)getLandedOnProperty()).getOwner());
        }else if(isSquareUtility()){
            numInSet = getNumInSetOwned(((Utility) currentPlayer.getPosition()).getOwner(), (Railroad) currentPlayer.getPosition());
            rent = ((Utility) getLandedOnProperty()).getRent(numInSet,getCurrentRoll());
            int rentPayed = players.get(currentTurn).payRent(rent);
            ((Utility)getLandedOnProperty()).getOwner().acceptRent(rentPayed);
            if(rent != rentPayed)
                bankrupt(players.get(currentTurn), ((Utility)getLandedOnProperty()).getOwner());
        }
    }

    public int getNumInSetOwned(Player play, Property prop){
        int num = 0;
        for(Property p : play.getProperties()){
            if(p.getSet()==prop.getSet()) num++;
        }
        return num;
    }

    /**
     * This method processes the buy property function of the game using a Scanner to get user input
     * from the terminal
     *
     * If a Player lands on a Property that is unowned, they are given a choice to pay a fixed amount
     * and become the owner of that Property
     *
     * If the Player chooses to buy the Property, they will answer "Yes".
     * The amount of the Property is deducted from their balance and they're set as that Property's owner
     *
     * If the Player chooses to buy the Property but that Player doesnt have enough money,
     * they will be told they have insufficient funds, the Property will remain unowned, and their
     * balance will remain the same
     *
     * If the Player chooses not to guy the Property, he Property will remain unowned, and
     * their balance will remain the same
     *
     */
    void buyProperty(){
        // Current Position
        Property p = (Property)getLandedOnProperty();
        Square s = p;
        int set = p.getSet();

        // Update Player
        players.get(currentTurn).buyProperty(p.getPrice(), (Property) board.getProperty(s.getIndex()));

        // Update Board
        ((Property) board.getProperty(currentPlayer.getPosition().getIndex())).setOwner(players.get(currentTurn));

        if(fullSet(currentPlayer,p)){
            for(Property prop : currentPlayer.getProperties()){
                if(prop.getSet()==set) prop.setFullSetTrue();
            }
        }
    }

    /**
     * Checks if user can buy property
     *
     * @return
     */
    public boolean canBuy()
    {
        if(isSquareProperty()){
            if (((Property) getLandedOnProperty()).getOwner() == null) {
                if (players.get(currentTurn).getBalance() - getLandedOnProperty().getPrice() >= 0) {
                    return true;
                }
            }
        }else if(isSquareRailroad()) {
            if (((Railroad) getLandedOnProperty()).getOwner() == null) {
                if (players.get(currentTurn).getBalance() - getLandedOnProperty().getPrice() >= 0) {
                    return true;
                }
            }
        }else if(isSquareUtility()){
            if (((Utility) getLandedOnProperty()).getOwner() == null) {
                if (players.get(currentTurn).getBalance() - getLandedOnProperty().getPrice() >= 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean canBuyHouse(){
        if(isSquareProperty()) {
            if (((Property) getLandedOnProperty()).getOwner().equals(currentPlayer)) {
                if (players.get(currentTurn).getBalance() - ((Property) getLandedOnProperty()).getHousePrice() >= 0) {
                    if(((Property)currentPlayer.getPosition()).getHouses() < 4) return true;
                }
            }
        }
        return false;
    }

    public boolean canBuyHotel(){
        if(isSquareProperty()) {
            if (((Property) getLandedOnProperty()).getOwner().equals(currentPlayer)) {
                if (players.get(currentTurn).getBalance() - ((Property) getLandedOnProperty()).getHousePrice() >= 0)
                    if(((Property)currentPlayer.getPosition()).getHouses() == 4) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean fullSet(Player play, Property prop){
        int count =0;
        for(Property p : play.getProperties()){
            if(p.getSet()==prop.getSet()) count++;
        }
        if(count == prop.getNumInSet()) return true;
        return false;
    }

    /**
     * checks if property is an empty square
     * @return
     */
    public boolean isPropertyEmpty()
    {
        return players.get(currentTurn).getPosition().getPrice() < 0;
    }
    public boolean isSquareProperty(){
        if(currentPlayer.getPosition() instanceof Property ) return true;
        return false;
    }

    public boolean isSquareRailroad(){
        if(currentPlayer.getPosition() instanceof Railroad ) return true;
        return false;
    }

    public boolean isSquareUtility(){
        if(currentPlayer.getPosition() instanceof Utility ) return true;
        return false;
    }

    /**
     * checks if property is owned by another player
     * @return
     */
    public boolean isRentOwed()
    {
        if(players.get(currentTurn)!= (getLandedOnProperty()).getOwner() && (getLandedOnProperty()).getOwner()!=null)
        {
            //System.out.println(getLandedOnProperty().getOwner().getName() + " owns this property");
            return true;
        }
        //System.out.println(getLandedOnProperty().getOwner().getName() + " owns this property");
        return false;
    }

    /**
     *  Requests the number of players from the user using a scanner
     *  Value is accepted If the entered int is equal to or greater than 2
     *  Corresponding amount of players are created using a loop then added to the game
     *
     *  Once all players are created and added, boolean running is set to true and every players position is set to 0
     *
     */
    public void setup(int playerAmount, int AIAmount, Controller controller){

        if(playerAmount > 0) {
            for (int i = 0; i < playerAmount; i++) {
                addPlayer("Player " + (i + 1));
                System.out.println("Player " + (i + 1) + " added");
            }
        }

        if(AIAmount > 0) {
            for (int i = 0; i < AIAmount; i++) {
                players.add(new AI("AI " + (i + 1), board, controller));
                System.out.println("AI " + (i + 1) + " added");
            }
        }

        /*//AI test
        players.add(new AI("AI1", board, controller));
        players.add(new AI("AI2", board, controller));
        players.add(new AI("AI3", board, controller));
        players.add(new AI("AI4", board, controller));
        playerAmount += 4;*/

        running = true;

        for(Player p : players){
            p.setPosition(board.getProperty(0));
        }

        for(int i=0; i<players.size();++i)
        {
            System.out.println(players.get(i).getName());
        }

        startingPlayerAmount = playerAmount + AIAmount;
        currentPlayer = players.get(0);
        currentTurn = 0;
    }

    /**
     * Checks if the inputted player amount is valid (not a letter & a number over 1)
     *
     * @param playerAmount
     * @return
     */
    public boolean checkPlayerAmount(String playerAmount)
    {
        try
        {
            // Parse the input from user
            int i = Integer.parseInt(playerAmount);
            if(i >= 0)
            {
                return true;
            }
        }
        // The input from user is not an int
        catch(NumberFormatException e)
        {
            return false;
        }

        // Input is an Int but not greater than 2
        return false;
    }

    /**
     * Rolls the dice of the current player
     */
    public void roll()
    {
        players.get(currentTurn).takeTurn();
    }

    public int getCurrentRoll()
    {
        return currentPlayer.getRoll(1) + currentPlayer.getRoll(2);
    }

    /**
     * returns the property the current player landed on
     * @return
     */
    public Square getLandedOnProperty()
    {
        return board.getProperty(currentPlayer.getPosition().getIndex());
    }

    /**
     * switches the turn to the next player in list players
     */
    public void endTurn()
    {
        // The last player in the list ends their turn
        if(currentTurn >= (players.size()-1))
        {
            if(players.size()!=startingPlayerAmount)
            {
                startingPlayerAmount=players.size();
            }
            // Only one player left
            if(players.size()==1)
            {
                playerWon = true;
            }
            // Turn returns to the first player
            currentPlayer = players.get(0);
            currentTurn = 0;
            // Test
            //System.out.println("Current Player: "+currentPlayer.getName());
        }
        else if(players.size()!=startingPlayerAmount)
        {
            startingPlayerAmount=players.size();
        }
        // Switch the currentPlayer to the next player in the list
        else
        {
            //currentPlayer = players.get(currentTurn);
            currentTurn++;
            System.out.println("Current Player: "+currentPlayer.getName());
        }
        currentPlayer = players.get(currentTurn);

        System.out.println("\nCurrent Turn: " + currentTurn);
        System.out.println("Current Player: " + players.get(currentTurn).getName());
    }

    /**
     * Checks win condition boolean
     * @return
     */
    boolean hasPlayerWon()
    {
        return playerWon;
    }


    /**
     * Getter used for test cases
     *
     * @return amount of players initially selected to play
     */
    int getPlayerAmount()
    {
        return players.size();
    }

    /**
     * Getter for the Player object whos turn it is
     *
     * @return
     */
    Player getCurrentPlayer()
    {
        return currentPlayer;
    }

}