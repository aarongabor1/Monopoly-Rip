import org.junit.*;
import static org.junit.Assert.*;


public class BoardTest
{
    private Board board;
    private java.util.List emptyList;

    /**
     * Sets up the test fixture.
     * (Called before every test case method.)
     */
    @Before
    public void setUp() {
        System.out.println("Invoked before each test method");
        board = new Board();
    }

    /**
     * Tears down the test fixture.
     * (Called after every test case method.)
     */
    @After
    public void tearDown() {
        System.out.println("Invoked after each test method");
        emptyList = null;
    }

    @Test
    public void testGetProperty() {
        // Get the first property on the board @index = 1
        // Name: "Ottawa U"
        // Index: 1
        // Price: 60

        // Returns an object
        assertNotEquals(board.getProperty(1), null);
        // Returns Property with correct name
        assertEquals(board.getProperty(1).getName(),"Ottawa U");
        // Returns property with correct index
        assertEquals(board.getProperty(1).getIndex(),1);
        //Returns property with correct price
        assertEquals(board.getProperty(1).getPrice(),60);
    }
}