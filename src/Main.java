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

        /**
        Board board = new Board();

        Player player1 = new Player("Player 1", board);
        Player player2 = new Player("Player 2", board);

        Property p1 = new Property("Property1", 0, 50, 0, 1, 10);
        Property p2 = new Property("Property2", 1, 60, 0, 2, 10);
        Property p3 = new Property("Property3", 2, 100, 1, 1, 20);
        Property p4 = new Property("Property4", 3, 110, 1, 2, 20);

        ArrayList<Property> list = new ArrayList<>();
        list.add(p1);
        list.add(p2);
        list.add(p3);
        list.add(p4);
         **/
        //System.out.println(board.getProperty(1).getClass());
        //player1.buyProperty(board.getProperty(1).getPrice(),board.getProperty(1));
    }
}
