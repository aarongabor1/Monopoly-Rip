import org.junit.*;
import static org.junit.Assert.*;

public class DieTest {

    Die die;

    @Before
    public void beforeEachTestMethod() {
        System.out.println("Invoked before each test method");
        die = new Die();
    }

    @After
    public void afterEachTestMethod() {
        System.out.println("Invoked after each test method");
    }

    @Test
    public void roll() {
        assertNotEquals(die.getValue(),null);
    }
}