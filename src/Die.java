public class Die {
    private final int max = 6;
    private int value;

    public Die(){
        roll();
    }

    public void roll(){
        value = (int) (Math.random() * max + 1);
    }

    public int getValue() {
        return value;
    }

}
