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
    }

    /**
     * Pay rent from one user to another
     */
    public void payRent(){
        int rent = getLandedOnProperty().getRent();
        int rentPayed = players.get(currentTurn).payRent(rent);
        getLandedOnProperty().getOwner().acceptRent(rentPayed);
        if(rent != rentPayed)
            bankrupt(players.get(currentTurn), getLandedOnProperty().getOwner());
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
     * If the Player chooses not to buy the Property, he Property will remain unowned, and
     * their balance will remain the same
     *
     */
    void buyProperty(){
        // Current Position
        Property p = getLandedOnProperty();

        // Update Player
        players.get(currentTurn).buyProperty(p.getPrice(),board.getProperty(p.getIndex()));

        // Update Board
        board.getProperty(currentPlayer.getPosition().getIndex()).buyProperty(players.get(currentTurn));
    }

    /**
     * Checks if user can buy property
     *
     * @return
     */
    public boolean canBuy()
    {
        if(getLandedOnProperty().getOwner()==null)
        {
            if(players.get(currentTurn).getBalance()-getLandedOnProperty().getPrice() >= 0)
            {
                return !isPropertyEmpty();
            }
        }
        return false;
    }

    /**
     * checks if property is an empty square
     * @return
     */
    public boolean isPropertyEmpty()
    {
        return players.get(currentTurn).getPosition().getName().equals("Empty");
    }

    /**
     * checks if property is owned by another player
     * @return
     */
    public boolean isRentOwed()
    {
        if(players.get(currentTurn)!=getLandedOnProperty().getOwner() && getLandedOnProperty().getOwner()!=null)
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
    public void setup(int playerAmount){

        for (int i = 0; i < playerAmount; i++)
        {
            addPlayer("Player" + (i+1));
            System.out.println("Player " + (i+1) + " added");
        }
        running = true;

        for(Player p : players){
            p.setPosition(board.getProperty(0));
        }

        for(int i=0; i<players.size();++i)
        {
            System.out.println(players.get(i).getName());
        }

        startingPlayerAmount = playerAmount;
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
            if(i>1)
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
        return currentPlayer.getRoll();
    }

    /**
     * returns the property the current player landed on
     * @return
     */
    public Property getLandedOnProperty()
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