import java.util.*;

public class Game
{
    private List<Player> players;
    private Board board;
    private boolean running;
    private Player currPlayer;

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
    private boolean bankrupt(Player p){
        if(p.getBalance()<0){
            return true;
        }
        return false;
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
     * @param p
     * @param currPosition
     */
    private void buyProperty(Player p, Property currPosition){
        System.out.println("This square is NOT owned");
        Scanner input = new Scanner(System.in);
        System.out.println("Would you like to buy? Yes : No ");
        String answer = input.next();
        while(true){
            if(answer.equalsIgnoreCase("yes") || answer.equalsIgnoreCase("no")) break;
            System.out.println("Please answer 'Yes' or 'No'");
            answer = input.next();
        }
        if (answer.equalsIgnoreCase("yes")) {
            if (p.getBalance() - currPosition.getPrice() >= 0) {
                p.buyProperty(currPosition.getPrice(), currPosition);
                board.getProperty(p.getPosition().getIndex()).buyProperty(p);
                System.out.println(p.getName() + " now owns: " + currPosition.getName());
            } else {
                System.out.println("You don't have enough money to buy this property");

            }
        } else if (answer.equalsIgnoreCase("no")) {
            System.out.println("The property was not purchased");
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
        System.out.println("Player " + owner.getName() + "" +
                " owns this property. You must pay rent");
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
    public void setup(){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter Number of Players: ");
        int num = in.nextInt();
        while(num < 2)
        {
            System.out.println("Error please enter a higher number");
            num = in.nextInt();
        }
        for (int i = 0; i < num; i++)
        {
            addPlayer("Player " + (i+1));

        }
        running = true;

        for(Player p : players){
            p.setPosition(board.getProperty(0));
        }
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
    public void play() {
        currPlayer = players.get(0);
        Property currPosition;
        while (running) {
            if (players.size() == 1) break;
            currPlayer.takeTurn();
            currPosition = board.getProperty(currPlayer.getPosition().getIndex());
            if (currPosition.getPrice() > 0 && currPosition.getOwner() == null) {
                buyProperty(currPlayer, currPosition);

            } else if (currPosition.getOwner() != null && currPosition.getPrice() > 0) {
                if (currPosition.getOwner().equals(currPlayer)) {
                    System.out.println("This player owns this property");
                } else {
                    payRent(currPosition.getOwner(), currPlayer, currPosition);
                }
            }
            System.out.println("You have no other actions \n Type 'Pass' to pass your turn");
            Scanner input = new Scanner(System.in);
            String answer = input.next();
            while(true) {
                if(answer.equalsIgnoreCase("pass")) break;
                System.out.println("You have no other actions \n Type 'Pass' to pass your turn");
                answer = input.next();
            }
            System.out.println("This player's turn is over");
            if (bankrupt(currPlayer)) {
                System.out.println(currPlayer.getName() + " has gone bankrupt. They are removed from the game");
                Player temp = currPlayer;
                if (players.indexOf(currPlayer) == players.size() - 1) {
                    currPlayer = players.get(0);
                } else {
                    currPlayer = players.get(players.indexOf(currPlayer) + 1);
                }
                players.remove(temp);
            } else if (players.indexOf(currPlayer) == players.size() - 1) {
                currPlayer = players.get(0);
            } else {
                currPlayer = players.get(players.indexOf(currPlayer) + 1);
            }
        }

        for (Player p : players) {
            System.out.println(p.getName() + " has won the game!");
        }
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
     * Main that creates a new game, sets it up, and starts the play loop
     *
     * @param args
     */
    public static void main(String [] args)
    {
        Game game = new Game();
        game.setup();
        game.play();
    }
}