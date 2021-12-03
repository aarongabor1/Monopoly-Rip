import org.junit.*;
import static org.junit.Assert.*;

public class GameTest {

    // Game variable
    Game game;

    @Test
    public void testAddPlayer() throws Exception {
        game = new Game();

        game.addPlayer("Player1");
        game.addPlayer("Player2");

        assertEquals(game.getPlayerAmount(), 2);
    }
}