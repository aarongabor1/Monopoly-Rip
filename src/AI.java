/**
 * Simple AI that plays the Monopoly Game
 * @Author Cam Sommerville
 */
public class AI extends Player{
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
