import java.io.Serializable;

/**
 * Die class representing a normal D6
 * @author Cam Sommerville
 */
public class Die implements Serializable {
    private final int max = 6;
    private int value;

    /**
     * Constructor for the Die class
     */
    public Die(){
        roll();
    }

    /**
     * Sets the variable "value" to a number between 1 and 6
     *
     * Math.random() generates pseudorandom double type number between 0 and 1
     * Multiplying this number by the "max" (6) will give us a range of 0-5 when cast to int
     * Adding + 1 to the calculation will change the range to 1-6
     *
     */
    public void roll(){
        value = (int) (Math.random() * max + 1);
    }

    /**
     * Getter for the value shown on the Die when rolled
     *
     * @return int
     */
    public int getValue() {
        return value;
    }

}

