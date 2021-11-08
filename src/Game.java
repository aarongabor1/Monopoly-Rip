import java.util.*;

public class Game
{
    private List<Player> players;
    private Board board;

    private boolean running;
    private boolean playerWon;

    private Player currentPlayer;

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
     */
    private void bankrupt(Player p){
        if(p.getBalance()<0){
            System.out.println(p.getName() + " has gone bankrupt. They are removed from the game");
            players.remove(p);
        }
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
        /**
        System.out.println("This square is NOT owned");
        Scanner input = new Scanner(System.in);
        System.out.println("Would you like to buy? Yes : No ");
        String answer = input.next();
        while(true){
            if(answer.equals("Yes") || answer.equals("No")) break;
            System.out.println("Please answer 'Yes' or 'No'");
            answer = input.next();
        }
        if (answer.equals("Yes")) {
            if (p.getBalance() - currPosition.getPrice() >= 0) {
                p.buyProperty(currPosition.getPrice(), currPosition);
                board.getProperty(p.getPosition().getIndex()).buyProperty(p);
                System.out.println(p.getName() + " now owns: " + currPosition.getName());
            } else {
                System.out.println("You don't have enough money to buy this property");

            }
        } else if (answer.equals("No")) {
            System.out.println("The property was not purchased");
        }
         **/

        // Update Player
        currentPlayer.buyProperty(currentPlayer.getPosition().getPrice(),board.getProperty(currentPlayer.getPosition().getIndex()));

        // Update Board
        board.getProperty(currentPlayer.getPosition().getIndex()).buyProperty(currentPlayer);
    }

    public boolean canBuy()
    {
        if(currentPlayer.getBalance() - currentPlayer.getPosition().getPrice() >= 0)
        {
            if(board.getProperty(currentPlayer.getPosition().getIndex()).getOwner().equals(null))
            {
                return true;
            }
            // Can afford it but it's owned
            return false;
        }
        else
        {
            // Player cannot afford property
            return false;
        }
    }

    /**
     * This method processes the pay rent function of the game.
     *
     * When a Player lands on a Property that is owned by another player after a roll, they owe the owner an
     * amount corresponding to the rent shown on that Property
     *
     * This method gets the rent from the Property with the method getRent()
     * Then calls payRent() to adjust the owing Players balance
     * Finally calls acceptRent() to adjust the owed Players balance
     *
     * @param owner
     * @param renter
     * @param p
     */
    private void payRent(Player owner, Player renter, Property p){
        System.out.println("Player " + owner + "" +
                "owns this property. You must pay rent");
        int rent = p.getRent();
        renter.payRent(rent);
        owner.acceptRent(rent);
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
            addPlayer("Player " + (i+1));
            System.out.println("Player " + (i+1) + " added");
        }
        running = true;

        for(Player p : players){
            p.setPosition(board.getProperty(0));
        }

        System.out.println("Total Players: " + players.size());
        currentPlayer = players.get(0);
    }

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
     * Main loop of game
     *
     * Processes:
     * If boolean "running" is true and game is ongoing
     * If there's more than 1 player that isn't eliminated
     * Each players turn
     * Main game functions (buying property, paying rent, current positon, end turn)
     * If there's a winner
     *
     */
    /**
    public void play()
    {
        while(running){
            Property currPosition;
            if(players.size() == 1) break;
            for(Player p : players){
                p.takeTurn();
                currentPlayer = p;
                currPosition = board.getProperty(p.getPosition().getIndex());
                if(currPosition.getPrice()> 0 && currPosition.getOwner() == null){
                    buyProperty(p, currPosition);

                }else if(currPosition.getOwner()!=null && currPosition.getPrice() > 0){
                    if(currPosition.getOwner().equals(p)){
                        System.out.println("This player owns this property");
                    }else{
                        payRent(currPosition.getOwner(),p , currPosition);
                        bankrupt(p);
                    }
                }
                System.out.println("This player's turn is over");
            }
        }
        for(Player p : players){
            System.out.println(p.getName() + " has won the game!");
        }

    }
     **/

    public void roll()
    {
        currentPlayer.takeTurn();
    }

    public int getCurrentRoll()
    {
        return currentPlayer.getRoll();
    }

    public void endTurn()
    {
        int currentTurn = players.indexOf(currentPlayer);
        System.out.println("Current Turn Index: "+players.indexOf(currentPlayer));

        // The last player in the list ends their turn
        if(currentTurn == (players.size()-1))
        {
            // Only one player left
            if(players.size()==1)
            {
                playerWon = true;
            }
            // Turn returns to the first player
            currentPlayer = players.get(0);
        }
        // Switch the currentPlayer to the next player in the list
        else
        {
            currentPlayer = players.get(currentTurn+1);
            System.out.println("Current Player: "+currentPlayer.getName());
        }
        currentPlayer = players.get(currentTurn+1);
    }

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