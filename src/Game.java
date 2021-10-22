import java.util.*;

public class Game
{
    private List<Player> players;
    private Board board;
    private Command command;

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

    }

    public static void main(String [] args)
    {
        Game game = new Game();
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
                    game.addPlayer("Player " + i);
                }
            }
        }while(num < 2);
        in.close();
        game.play();
    }
}
