import java.util.*;

public class Game
{
    private List<Player> players;
    private Board board;
    private boolean running;

    /**
     *  Constructor for Game class
     *  Initiates: Board object, ArrayList of Player objects
     */
    public Game()
    {
        board = new Board();
        players = new ArrayList<>();
    }


    /**
     * Creates a new Player object on the board using the name parameter as it's name
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
     *
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
    }

    /**
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
     *
     */
    public void setup(){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter Number of Players: ");
        int num = in.nextInt();
        do
        {
            if (num < 2)
            {
                System.out.println("Error please enter a higher number");
                num = in.nextInt();
            }
            else
            {
                for (int i = 0; i < num; i++)
                {
                    addPlayer("Player " + (i+1));
                }
            }
        }while(num < 2);
        running = true;

        for(Player p : players){
            p.setPosition(board.getProperty(0));
        }
    }

    /**
     *
     */
    public void play()
    {
        while(running){
            Property currPosition;
            if(players.size() == 1) break;
            for(Player p : players){
                p.takeTurn();
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

    /**
     * @return
     */
    int getPlayerAmount()
    {
        return players.size();
    }

    /**
     * @param args
     */
    public static void main(String [] args)
    {
        Game game = new Game();
        game.setup();
        game.play();
    }
}