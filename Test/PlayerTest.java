import org.junit.*;
import static org.junit.Assert.*;

public class PlayerTest {

    // Player variable
    Player player;

    Board board;

    @Before
    public void beforeEachTestMethod() throws Exception {
        System.out.println("Invoked before each test method");
        board = new Board();
        player = new Player("Player1", board);
    }

    @After
    public void afterEachTestMethod() {
        System.out.println("Invoked after each test method");
        board = null;
        player = null;
    }

    @Test
    public void getName() {
        assertEquals("Player1",player.getName());
    }

    @Test
    public void getBalance() {
        assertEquals(2000, player.getBalance());
    }

    @Test
    public void buyProperty() {
        player.buyProperty(60, (Property) board.getProperty(1));
        assertEquals(1940,player.getBalance());
    }

    @Test
    public void payRent() {
        player.payRent(50);
        assertEquals(1950,player.getBalance());
    }

    @Test
    public void acceptRent() {
        player.acceptRent(50);
        assertEquals(2050,player.getBalance());
    }


    @Test
    public void takeTurn() {
        player.setPosition(board.getProperty(1));
        player.takeTurn();
        assertNotEquals(player.getPosition().getIndex(),1);
    }
}