import org.junit.*;
import static org.junit.Assert.*;

public class PropertyTest {
    Property p;

    @Before
    public void beforeEachTestMethod() {
        System.out.println("Invoked before each test method");
        p = new Property("Test",1,50, 1,1,50);
    }

    @After
    public void afterEachTestMethod() {
        System.out.println("Invoked after each test method");
        p = null;
    }
    @Test
    public void getName() {
        assertEquals("Test",p.getName());
    }

    @Test
    public void getIndex() {
        assertEquals(1,p.getIndex());
    }

    @Test
    public void getPrice() {
        assertEquals(50,p.getPrice());
    }

    @Test
    public void getRent() {
        assertEquals(5,p.getRent(0,false));
    }

    @Test
    public void buyProperty() {
        Board b = new Board();
        Player p1 = new Player("P1",b);

        p.buyProperty(p1);
        assertEquals(p1, p.getOwner());
    }
}