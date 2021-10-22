import java.util.*;

public class Game
{
    private List<Player> players;
    private Board board;
    private Command command;
    private boolean running;

    public Game()
    {
        board = new Board();
        players = new ArrayList<>();
        command = new Command();
    }

    public void addPlayer(String name)
    {
        Player p = new Player(name, board);
        players.add(p);
    }

    public void play()
    {
        while(running){
            Property currPosition;
            for(Player p : players){
                p.takeTurn();
                currPosition = board.getProperty(p.getPosition().getIndex());
                if(currPosition.getPrice()> 0 && currPosition.getOwner() == null){
                   while(true) {
                       System.out.println("This square is NOT owned");
                       System.out.println("Would you like to buy? Yes : No ");
                       Scanner in = new Scanner(System.in);
                       //CHECK INPUT!! Change following
                       String answer = in.toString();
                       if (answer.equals("Yes")){
                           if(p.getBalance() - currPosition.getPrice()>= 0) {
                               p.buyProperty(currPosition.getPrice(), currPosition);
                               board.getProperty(p.getPosition().getIndex()).buyProperty(p);
                           }else{
                               System.out.println("You don't have enough money to buy this property");
                               break;
                           }
                       }else if(answer.equals("No")){
                           break;
                       }else{
                           System.out.println("Please enter 'Yes' or 'No'");
                       }
                   }
                }else if(currPosition.getOwner()!=null){
                    if(currPosition.getOwner().equals(p)){
                        System.out.println("This player owns this property");
                    }else{
                        System.out.println("Player " +currPosition.getOwner().getName() + "" +
                                "owns this property. You must pay rent");

                    }
                }
            }
        }
    }

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
                    this.addPlayer("Player " + i);
                }
            }
        }while(num < 2);
        in.close();
        running = true;
    }

    public static void main(String [] args)
    {
        Game game = new Game();
        game.setup();
        game.play();
    }
}
