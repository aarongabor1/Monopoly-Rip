import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener
{

    // Monopoly Model
    private Game m;

    // GUI
    private View v;

    /**
     * constructor for controller class
     *
     * @param m
     * @param v
     */
    public Controller(Game m, View v)
    {
        // Initiate Model and View
        this.m = m;
        this.v = v;

        // Initiate Actions
        v.monopolyActionListener(this);
    }

    /**
     * process action commands
     *
     * @param e
     */
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
            // Test
            v.updateOutput("Pay Rent Selected");
            v.updateOutput(m.getCurrentPlayer().getName() + " paid " + ((Property)m.getLandedOnProperty()).getOwner().getName() + ": $" + ((Property)m.getLandedOnProperty()).getRent());

            // Update Model
            m.payRent();


            // Update view
            v.updateBalance(String.valueOf(m.getCurrentPlayer().getBalance()));
            v.setEndTurn();

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


    /**
     * update view for current player
     *
     * @param cp
     */
    private void updatePlayer(Player cp)
    {
        // Update View Player Data
        v.updatePlayerName(cp.getName());
        v.updateBalance(String.valueOf(cp.getBalance()));
        v.updateProperties(cp.getProperties());

        // Update View Buttons
        v.setRoll();
    }

    /**
     * update output in view with roll data
     */
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
        else
        {
            v.updateOutput(m.getCurrentPlayer().getName()+" landed on: "+m.getCurrentPlayer().getPosition().getName());
        }
    }

    /**
     * set the appropriate button options in view
     */
    private void propertyOptions()
    {
        // Check if the property is empty
        if(m.isPropertyEmpty())
        {
            v.setButtons();
            v.setEndTurn();
        }
        else if(m.canBuy())
        {
            v.setButtons();
            v.setBuyable();
        }
        else if(m.isRentOwed())
        {
            v.setButtons();
            v.setRentable();
        }
        else
        {
            v.setButtons();
            v.setEndTurn();
        }
    }

    /**
     * Notify Model and View of user buying property
     */
    private void buyUpdate()
    {
        // Tell model that Player wants to buy property
        m.buyProperty();

        // Update View Display
        v.updateProperties(m.getCurrentPlayer().getProperties());
        v.updateBalance(String.valueOf(m.getCurrentPlayer().getBalance()));

        // Update View Buttons
        v.setEndTurn();
    }

    /**
     * Updates view at the end of a players turn for the next players data
     * also checks for winner
     */
    private void endTurnUpdate()
    {
        // Enter a divider between player turns in the console
        v.updateOutput("--------------------------------------");

        // Check if player has won
        if(m.hasPlayerWon())
        {
            v.updateOutput("GAME OVER:");
            v.updateOutput(m.getCurrentPlayer().getName() + " has won!");
            v.gameOver();
        }

        // Update the View for the next Player
        updatePlayer(m.getCurrentPlayer());
    }
}