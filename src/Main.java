import java.util.ArrayList;

public class Main {

    private Player player1;
    private Player player2;
    private Board board;

    public static void main(String[] args)
    {

        Game game = new Game();
        View view = new View();
        Controller controller = new Controller(game, view);
        view.displayGUI();
    }
}
