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
            v.updateOutput("Roll Selected");

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
                System.out.println("Player number selected is valid");

                // Setup the Model
                m.setup(Integer.parseInt(v.getPlayerAmount()));
                m.play();

                // Setup the View
                v.startGame();
                v.setRoll();
            }
            else
            {
                System.out.println("Player number selected is invalid");
            }
        }
        else
        {
            v.updateOutput("End Turn Selected");
        }

    }
}
