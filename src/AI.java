import java.io.Serializable;
import java.util.ArrayList;

/**
 * Simple AI that plays the Monopoly Game
 * @Author Cam Sommerville
 */
public class AI extends Player implements Serializable {
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

    /**
     * Method simulates an AI taking its turn
     * @param model
     */
    public void AITurn(Game model){
        controller.roll();

        if(this.isInJail()){
            if(this.getJailedTurns() == 4){
                this.payJailor();
                controller.updateGUIOutput("AI paid to get out of jail");
            }
            else
                return;
        }
        else if(model.isPropertyEmpty()){
            return;
        }
        else if(model.isRentOwed()){
            controller.payRent();
        }
        else if(model.canBuy()){
            controller.buy();
        }
        for(Property p : this.getProperties()){
            if (model.canBuyHouseAI(p) && p.getHousePrice() < this.getBalance()){
                model.buyHouse(p.toString());
                controller.houseOutput(p.getName());
            }
            if (model.canBuyHotelAI() && p.getHousePrice() < this.getBalance()){
                model.buyHotel(p.toString());
                controller.hotelOutput(p.getName());
            }
        }
    }

}
