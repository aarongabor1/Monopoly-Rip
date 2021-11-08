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

            // Update view user options
            v.setButtons();
        }
        else if(o.equals("Buy"))
        {
            v.updateOutput("Buy Selected");
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
        v.updatePlayerName(cp.getName());
        v.updateBalance(String.valueOf(cp.getBalance()));
        v.updateProperties(cp.getProperties());
    }

    private void rollUpdate()
    {
        v.updateOutput("Roll Selected");
        // Players roll number
        v.updateOutput(m.getCurrentPlayer().getName() + " rolled: " + m.getCurrentPlayer().getRoll());

        // Players new position
        String property = m.getCurrentPlayer().getPosition().getName();

        if(m.getCurrentPlayer().getPosition().getName().equals("Empty"))
        {
            v.updateOutput(m.getCurrentPlayer().getName()+" landed on an empty space");
        }
        else
        {
            v.updateOutput(m.getCurrentPlayer().getName()+" landed on: "+m.getCurrentPlayer().getPosition().getName());
        }
    }

    private void buyUpdate()
    {
        if(m.canBuy())
        {
            m.buyProperty();
        }
    }

    private void endTurnUpdate()
    {
        v.updateOutput("--------------------------------------");
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