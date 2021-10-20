import java.util.*;

public class Game {
    private List<Player> players;
    private Board board;

    public Game(){
        board = new Board();
        players = new ArrayList<>();
    }

    public void addPlayer(String name, String piece){
        Player p = new Player(name, board);
        players.add(p);
    }

    public void play(){
    }
}
