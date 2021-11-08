import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener
{

    // Monopoly Model
    private Game m;

    // GUI
    private View v;

    public Controller(Game m, View v)
    {
        // Initiate Model and View
        this.m = m;
        this.v = v;

        // Initiate Actions
        v.monopolyActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        // Get action command
        String o = e.getActionCommand();

        //Process Command
        if(o.equals("Roll"))
        {
            // Tell Model that user selected Roll command
            m.roll();

            // Update view with roll results
            rollUpdate();

            // Update user options for the property they landed on
            propertyOptions();

        }
        else if(o.equals("Buy"))
        {
            // Test
            v.updateOutput("Buy Selected");

            // Update Model and View
            buyUpdate();
        }
        else if(o.equals("Pay Rent"))
        {
            v.updateOutput("Pay Rent Selected");
        }
        else if(o.equals("Submit"))
        {
            System.out.println("Players Selected: " + v.getPlayerAmount());

            // Check if input is valid
            if(m.checkPlayerAmount(v.getPlayerAmount()))
            {
                // Test
                System.out.println("Player number selected is valid");

                // Setup the Model
                startGame();

                // Setup View
                updatePlayer(m.getCurrentPlayer());
            }
            else
            {
                System.out.println("Player number selected is invalid");
                v.inputFailed();
            }
        }
        else
        {
            v.updateOutput("End Turn Selected");
            m.endTurn();
            endTurnUpdate();
        }

    }

    /**
     * Tells the Model to setup and start the game with the number of players selected in menu
     */
    private void startGame()
    {
        // Setup the Model
        m.setup(Integer.parseInt(v.getPlayerAmount()));

        // Setup the View
        v.startGame();
        v.setRoll();

        // Test
        System.out.println("Current Player: "+m.getCurrentPlayer().getName());
    }

    // Update View for current player stats
    private void updatePlayer(Player cp)
    {
        // Update View Player Data
        v.updatePlayerName(cp.getName());
        v.updateBalance(String.valueOf(cp.getBalance()));
        v.updateProperties(cp.getProperties());

        // Update View Buttons
        v.setRoll();
    }

    private void rollUpdate()
    {
        v.updateOutput("Roll Selected");

        // Players roll number
        v.updateOutput(m.getCurrentPlayer().getName() + " rolled: " + m.getCurrentPlayer().getRoll());

        // Players New Position on board
        v.updateOutput("Position on board: " + m.getCurrentPlayer().getPosition().getIndex());

        // Players new position
        String property = m.getCurrentPlayer().getPosition().getName();

        if(m.getCurrentPlayer().getPosition().getName().equals("Empty"))
        {
            v.updateOutput(m.getCurrentPlayer().getName()+" landed on an empty space");
        }
        else if(m.isRentOwed())
        {
            v.updateOutput(m.getCurrentPlayer().getName()+" owes: "+m.getCurrentPlayer().getPosition().getPrice());
        }
        else
        {
            v.updateOutput(m.getCurrentPlayer().getName()+" landed on: "+m.getCurrentPlayer().getPosition().getName());
        }
    }

    private void propertyOptions()
    {
        if(m.canBuy())
        {
            System.out.println("Player can buy property");
            v.setButtons();
            v.setBuyable();
        }
        else if(m.isPropertyEmpty())
        {
            System.out.println("Player cannot buy Empty Property");
            v.setButtons();
        }
        else if(m.isRentOwed())
        {
            v.setRentable();
        }
        else
        {
            System.out.println("Player must pay rent");
            v.setRentable();
        }
    }

    private void buyUpdate()
    {
        // Tell model that Player wants to buy property
        m.buyProperty();

        // Update View Display
        v.updateProperties(m.getCurrentPlayer().getProperties());

        // Update View Buttons
        v.setEndTurn();
    }

    private void endTurnUpdate()
    {
        // Enter a divider between player turns in the console
        v.updateOutput("--------------------------------------");

        // Update the View for the next Player
        updatePlayer(m.getCurrentPlayer());
    }

}

// ------------------------------------------------------------------------------------

class PlayerController implements ActionListener
{
    //Variables

    // Player (Model)
    private Player p;

    // View

    public PlayerController()
    {

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent)
    {

    }
}