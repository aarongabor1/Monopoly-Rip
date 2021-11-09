import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class View
{
    private static Font font1 = new Font("Times New Roman", Font.BOLD, 75);
    private static Font font2 = new Font("Times New Roman", Font.PLAIN, 25);
    private static Font font3 = new Font("Times New Roman", Font.PLAIN, 15);
    private static Font font4 = new Font("Times New Roman", Font.BOLD, 100);
    private static Font font5 = new Font("Times New Roman", Font.BOLD, 50);


    private JFrame frame;
    private JPanel mainPanel;
    private JTextArea output;
    private JPanel updatePanel;
    private JPanel bottomPanel;
    private JTextField balance;
    private JTextArea properties;
    private JPanel rollPanel;
    private JPanel buttonPanel;
    private JButton rollButton;
    private JButton buyButton;
    private JButton endTurnButton;
    private JButton rentButton;
    private JTextField playerSelection;
    private JLabel playerName;
    private JPanel playerSelectionPanel;
    private JButton submit;
    private JLabel selectionText;

    public View()
    {
        //Creating the frame and the main panel
        frame = new JFrame("Monopoly");
        bottomPanel = new JPanel();
        frame.setSize(1000,650);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainPanel = new JPanel(new GridLayout(3,1));
        mainPanel.setVisible(true);

        //Creates the player selection panel
        playerSelectionPanel = new JPanel();
        playerSelectionPanel.setLayout(new GridLayout(3, 1));
        playerSelectionPanel.setBackground(Color.white);
        playerSelectionPanel.setVisible(true);
        frame.add(playerSelectionPanel);

        //Creates the player selection label
        selectionText = new JLabel("Please Select The Amount of Players (2+):");
        selectionText.setHorizontalAlignment(JLabel.CENTER);
        selectionText.setFont(font5);

        //Creates the Player selection entering area
        playerSelection = new JTextField();
        playerSelection.setEditable(true);
        playerSelection.setActionCommand("Player Number");
        playerSelection.setFont(font5);

        //Creates a button to allow users to submit their number of players
        submit = new JButton("Submit");
        submit.setFont(font1);
        submit.setBackground(Color.cyan);

        //Adds all elements to the player selection panel
        playerSelectionPanel.add(selectionText);
        playerSelectionPanel.add(playerSelection);
        playerSelectionPanel.add(submit);

        //Creates the output of game information
        output = new JTextArea();
        output.setFont(font2);
        output.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(output, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        mainPanel.add(scrollPane);

        //Creates the panel that gives player information
        updatePanel = new JPanel(new GridLayout(1,3));

        //Creates the display for the player's money balance
        balance = new JTextField();
        balance.setFont(font1);
        balance.setEditable(false);
        balance.setBackground(Color.white);
        balance.setHorizontalAlignment(JTextField.CENTER);

        //Creates the display for the player's name
        playerName = new JLabel();
        playerName.setVisible(true);
        playerName.setFont(font1);
        playerName.setBackground(Color.white);

        //Creates the display for the properties that the player own
        properties = new JTextArea();
        properties.setFont(font3);
        properties.setEditable(false);
        JScrollPane scrollPane1 = new JScrollPane(properties, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        //Adds the three items above to the update panel
        updatePanel.add(balance);
        updatePanel.add(playerName);
        updatePanel.add(scrollPane1);
        mainPanel.add(updatePanel);

        //Adds the bottom panel to the main panel
        mainPanel.add(bottomPanel);
        bottomPanel.setVisible(true);

        //Creates the roll panel and the roll button
        rollPanel = new JPanel(new GridLayout(1, 3));
        rollButton = new JButton("Roll");
        rollButton.setEnabled(true);
        rollButton.setFont(font4);
        rollButton.setBackground(Color.CYAN);
        rollPanel.add(rollButton);
        rollPanel.setVisible(false);
        rollPanel.setBackground(Color.white);
        bottomPanel.add(rollPanel);

        //Creates a button panel for the turn buttons
        buttonPanel = new JPanel(new GridLayout(1,3));
        buttonPanel.setBackground(Color.white);

        //Creates the rent button and adds it to the button panel
        rentButton = new JButton("Pay Rent");
        rentButton.setEnabled(false);
        rentButton.setFont(font4);
        rentButton.setBackground(Color.orange);
        buttonPanel.add(rentButton);

        //Creates the buy button and adds it to the button panel
        buyButton = new JButton("Buy");
        buyButton.setEnabled(false);
        buyButton.setFont(font4);
        buyButton.setBackground(new Color(124,252,0));
        buttonPanel.add(buyButton);

        //Creates the end turn button and adds it to the button panel
        endTurnButton = new JButton("End Turn");
        endTurnButton.setEnabled(true);
        endTurnButton.setFont(font4);
        endTurnButton.setBackground(Color.pink);
        buttonPanel.add(endTurnButton);
        buttonPanel.setVisible(false);
        bottomPanel.add(buttonPanel);
        frame.setVisible(true);
    }

    /**
     * This method will update the output display by adding a String that it is passed
     * and then will bring the panel to the end so that the play may see the most recent information.
     * @param str The String that is to be added to the output display
     */
    public void updateOutput(String str)
    {
        output.append(str + "\r\n");
        output.setCaretPosition(output.getDocument().getLength());
    }

    /**
     * This method will update the player's balance and will display the amount of money that the
     * player currently has.
     * @param str The String that contains the balance to be displayed that doesn't
     *            have a dollar sign in it.
     */
    public void updateBalance(String str)
    {
        balance.setText(null);
        balance.setText("$" + str + "\r\n");
    }

    /**
     * This method will notify a player that has entered an invalid number in the player number
     * window to reenter a valid number.
     */
    public void inputFailed()
    {
        selectionText.setText("Please Enter a Valid Number of Players (2+):");
    }

    /**
     * This method will display all the properties that a player currently owns.
     * @param arrayList The ArrayList of property objects of the property that are to be displayed.
     */
    public void updateProperties(ArrayList<Property> arrayList)
    {
        properties.setText(null);
        for(Property p: arrayList)
        {
            properties.append(p.getName()+"\n");

        }
    }

    /**
     * This is a method that will update the player name that is being displayed.
     * @param str A String that contains the player name to be displayed.
     */
    public void updatePlayerName(String str)
    {
        playerName.setText(str);
    }

    public String getPlayerAmount()
    {
        return playerSelection.getText();
    }


    /**
     * A method that will add the action listener that has been passed to the elements of the
     * GUI that needs an action listener.
     * @param o The ActionListener object that contains the action listener to be used.
     */
    public void monopolyActionListener(ActionListener o)
    {
        rollButton.addActionListener(o);
        buyButton.addActionListener(o);
        endTurnButton.addActionListener(o);
        rentButton.addActionListener(o);
        playerSelection.addActionListener(o);
        submit.addActionListener(o);
    }


    /**
     * This is a method that will update the player name that is being displayed.
     * @param str A String that contains the player name to be displayed.
     */
    public int getPlayerNumber()
    {
        String pn = playerSelection.getText();
        pn.substring(23);
        pn.replaceAll("\\s+", "");
        return Integer.valueOf(pn);
    }

    /**
     * This is a method that will switch between the player selection part of the program and the
     * main game part of the program and will allow the game to start.
     */
    public void startGame()
    {
        frame.remove(playerSelectionPanel);
        frame.add(mainPanel);
        frame.pack();
        frame.setSize(1000,650);
    }

    /**
     * This method will change the bottom panel of the GUI to the roll panel and will disable the rent
     * and buy buttons for the next player.
     */
    public void setRoll()
    {
        buttonPanel.setVisible(false);
        buyButton.setVisible(false);
        rentButton.setVisible(false);
        rollPanel.setVisible(true);
    }

    /**
     * This method will switch the bottom panel from the roll panel to the button panel.
     */
    public void setButtons()
    {
        rollPanel.setVisible(false);
        buttonPanel.setVisible(true);
        bottomPanel.repaint();
    }

    /**
     * This method will enable the buy button
     */
    public void setBuyable()
    {
        buyButton.setEnabled(true);
        buyButton.setVisible(true);
    }

    /**
     * This method will enable the Rent button
     */
    public void setRentable()
    {
        rentButton.setEnabled(true);
        rentButton.setVisible(true);
        endTurnButton.setVisible(false);
    }

    /**
     * This method will enable the End Turn Button
     */
    public void setEndTurn()
    {
        buyButton.setVisible(false);
        rentButton.setVisible(false);
        endTurnButton.setVisible(true);
    }

    public void gameOver()
    {
        endTurnButton.setVisible(false);
    }
}