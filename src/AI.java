import java.util.ArrayList;

/**
 * Simple AI that plays the Monopoly Game
 * @Author Cam Sommerville
 */
public class AI extends Player{
    private String name;
    private ArrayList<Property> properties;
    private int balance;
    private Die die1;
    private Die die2;
    private Board board;
    private Square position;
    private Controller controller;

    /**
     * Constructor for the AI class
     *
     * @param name
     * @param board
     */
    public AI(String name, Board board, Controller controller) {
        super(name, board);
        this.controller = controller;
    }

    public void AITurn(Game model){
        controller.roll();

        if(model.isPropertyEmpty()){
            return;
        }
        else if(model.isRentOwed()){
            controller.payRent();
        }
        else if(model.canBuy()){
            controller.buy();
        }

    }

}
