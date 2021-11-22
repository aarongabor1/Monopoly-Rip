import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * @author Aaron Gabor
 * @version 3.1.0
 */
public class View
{
    //Set fonts for all text in the GUI
    private static final Font font1 = new Font("Times New Roman", Font.BOLD, 75);
    private static final Font font2 = new Font("Times New Roman", Font.PLAIN, 25);
    private static final Font font3 = new Font("Times New Roman", Font.PLAIN, 15);
    private static final Font font4 = new Font("Times New Roman", Font.BOLD, 85);
    private static final Font font5 = new Font("Times New Roman", Font.BOLD, 50);
    private static final Font font6 = new Font("Times New Roman", Font.BOLD, 25);

    private JFrame frame;
    private JPanel mainPanel;
    private JTextArea output;
    private JPanel updatePanel;
    private JTextField balance;
    private JTextArea properties;
    private JPanel rollPanel;
    private JPanel buttonPanel;
    private JButton rollButton;
    private JButton buyButton;
    private JButton endTurnButton;
    private JButton rentButton;
    private JTextField playerSelection;
    private JTextField aiSelection;
    private JLabel playerName;
    private JPanel playerSelectionPanel;
    private JButton submit;
    private JLabel selectionText;
    private JLabel aISelectionText;
    private JButton houseHotelButton;

    private JFrame houseFrame;
    private JButton buyHouseButton;
    private JButton buyHotelButton;
    private JButton closeButton;
    private JComboBox<String> dropdown;
    private JPanel dropdownPanel;
    private JPanel houseButtonPanel;
    private JTextField outputHouse;
    private JTextField balance2;

    /**
     * A constructor for the View class that will create a GUI and will configure the settings
     * of all the frames, panels, text areas, text fields, labels, and buttons.
     */
    public View()
    {
        //Creating the frame and the main panel
        frame = new JFrame("Monopoly");
        JPanel bottomPanel = new JPanel();
        frame.setSize(1000, 650);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainPanel = new JPanel(new GridLayout(3, 1));
        mainPanel.setVisible(true);

        //Creates the player selection panel
        playerSelectionPanel = new JPanel();
        playerSelectionPanel.setLayout(new GridLayout(3, 2));
        playerSelectionPanel.setBackground(Color.white);
        playerSelectionPanel.setVisible(true);
        frame.add(playerSelectionPanel);

        //Creates the player selection label
        selectionText = new JLabel("Please Select The Amount of Players (2+):");
        selectionText.setHorizontalAlignment(JLabel.CENTER);
        selectionText.setFont(font6);

        //Creates the AI selection label
        aISelectionText = new JLabel("Please Select The Amount of AI Players:");
        aISelectionText.setHorizontalAlignment(JLabel.CENTER);
        aISelectionText.setFont(font6);

        //Creates the Player selection entering area
        playerSelection = new JTextField();
        playerSelection.setEditable(true);
        playerSelection.setActionCommand("Player Number");
        playerSelection.setFont(font5);

        //Creates the AI selection entering area
        aiSelection = new JTextField();
        aiSelection.setEditable(true);
        aiSelection.setActionCommand("AI Number");
        aiSelection.setFont(font5);

        //Creates a button to allow users to submit their number of players
        submit = new JButton("Submit");
        submit.setFont(font1);
        submit.setBackground(Color.cyan);

        //Adds all elements to the player selection panel
        playerSelectionPanel.add(selectionText);
        playerSelectionPanel.add(aISelectionText);
        playerSelectionPanel.add(playerSelection);
        playerSelectionPanel.add(aiSelection);
        playerSelectionPanel.add(submit);

        //Creates the output of game information
        output = new JTextArea();
        output.setFont(font2);
        output.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(output, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        mainPanel.add(scrollPane);

        //Creates the panel that gives player information
        updatePanel = new JPanel(new GridLayout(1, 3));

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
        rollPanel = new JPanel(new GridLayout(1, 2));
        rollButton = new JButton("Roll");
        rollButton.setEnabled(true);
        rollButton.setFont(font4);
        rollButton.setBackground(Color.CYAN);
        rollPanel.add(rollButton);
        rollPanel.setVisible(false);
        rollPanel.setBackground(Color.white);
        bottomPanel.add(rollPanel);

        //Creates a button panel for the turn buttons
        buttonPanel = new JPanel(new GridLayout(2, 2));
        buttonPanel.setBackground(Color.white);

        //Creates the buy button and adds it to the button panel
        buyButton = new JButton("Buy");
        buyButton.setEnabled(false);
        buyButton.setFont(font4);
        buyButton.setBackground(new Color(124, 252, 0));
        buttonPanel.add(buyButton);

        //Creates the rent button and adds it to the button panel
        rentButton = new JButton("Pay Rent");
        rentButton.setEnabled(false);
        rentButton.setFont(font4);
        rentButton.setBackground(Color.ORANGE);
        buttonPanel.add(rentButton);

        //Creates the house/hotel button and adds it to the button panel
        houseHotelButton = new JButton("House/Hotel");
        houseHotelButton.setEnabled(false);
        houseHotelButton.setFont(font4);
        buttonPanel.add(houseHotelButton);

        //Creates the end turn button and adds it to the button panel
        endTurnButton = new JButton("End Turn");
        endTurnButton.setEnabled(true);
        endTurnButton.setFont(font4);
        endTurnButton.setBackground(Color.pink);
        buttonPanel.add(endTurnButton);
        buttonPanel.setVisible(false);
        bottomPanel.add(buttonPanel);
        frame.setVisible(true);

        //House Frame
        houseFrame = new JFrame("Buy Houses and Hotels");
        houseFrame.setLayout(new GridLayout(3,1));
        houseFrame.setSize(1000,650);
        houseFrame.setVisible(false);

        //Creates output for house buying
        JPanel panel = new JPanel(new GridLayout(1,2));
        panel.setVisible(true);
        houseFrame.add(panel);
        outputHouse = new JTextField();
        outputHouse.setVisible(true);
        outputHouse.setEditable(false);
        outputHouse.setFont(font2);
        outputHouse.setBackground(Color.white);
        outputHouse.setHorizontalAlignment(JTextField.CENTER);
        balance2 = new JTextField();
        balance2.setVisible(true);
        balance2.setEditable(false);
        balance2.setFont(font1);
        balance2.setBackground(Color.white);
        balance2.setHorizontalAlignment(JTextField.CENTER);
        panel.add(balance2);
        panel.add(outputHouse);

        //Creates panel for dropdown bar
        dropdownPanel = new JPanel();
        dropdownPanel.setVisible(true);
        houseFrame.add(dropdownPanel);

        //Creates button panel
        houseButtonPanel = new JPanel(new GridLayout(1,3));
        houseButtonPanel.setVisible(true);
        houseFrame.add(houseButtonPanel);

        //Creates buttons for buying houses and hotels
        buyHouseButton = new JButton("Buy House");
        buyHouseButton.setFont(font5);
        houseButtonPanel.add(buyHouseButton);
        buyHotelButton = new JButton("Buy Hotel");
        buyHotelButton.setFont(font5);
        houseButtonPanel.add(buyHotelButton);
        closeButton = new JButton("Close");
        closeButton.setFont(font5);
        houseButtonPanel.add(closeButton);
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
        balance2.setText(null);
        balance2.setText("$" + str + "\r\n");
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
        for (Property p : arrayList)
        {
            properties.append(p.getName() + " Set: " + p.getSet() + "\n");
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

    public String getAIAmount()
    {
        return aiSelection.getText();
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
        playerSelection.addActionListener(o);
        aiSelection.addActionListener(o);
        submit.addActionListener(o);
        rentButton.addActionListener(o);
        houseHotelButton.addActionListener(o);
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
        frame.setSize(1000, 650);
    }

    /**
     * This method will change the bottom panel of the GUI to the roll panel and will disable the rent
     * and buy buttons for the next player.
     */
    public void setRoll()
    {
        buttonPanel.setVisible(false);
        buyButton.setEnabled(false);
        buyButton.setVisible(true);
        rentButton.setEnabled(false);
        rollButton.setVisible(true);
        houseHotelButton.setEnabled(false);
        houseHotelButton.setVisible(true);
        rollPanel.setVisible(true);
    }

    /**
     * This method will switch the bottom panel from the roll panel to the button panel.
     */
    public void setButtons()
    {
        rollPanel.setVisible(false);
        buttonPanel.setVisible(true);
    }

    /**
     * This method will enable the buy button
     */
    public void setBuyable()
    {
        buyButton.setEnabled(true);
    }

    /**
     * This method will enable the Rent button
     */
    public void setRentable()
    {
        rentButton.setEnabled(true);
        endTurnButton.setEnabled(false);
    }

    /**
     * This method will enable the End Turn button
     */
    public void setEndTurn()
    {
        endTurnButton.setEnabled(true);
    }

    /**
     * This method will disable the Rent and Buy button.
     */
    public void turnOffButtons()
    {
        rentButton.setEnabled(false);
        buyButton.setEnabled(false);
    }

    /**
     * This method will enable the House/Hotel button
     */
    public void setHouseHotelBuyable()
    {
        houseHotelButton.setEnabled(true);
    }

    public void openHouseBuy()
    {
        houseFrame.setVisible(true);
    }

    public void updateOutputHouse(String str)
    {
        outputHouse.setText(str);
    }

    public void setUpDropdown(ArrayList<Property> arrayList)
    {
        String[] names = new String[arrayList.size()-1];
        for(int i = 0; i < arrayList.size(); i++)
        {
            names[i] = arrayList.get(i).getName();
        }
        dropdown = new JComboBox<String>(names);
        dropdown.setVisible(true);
        dropdown.setFont(font2);
        dropdownPanel.add(dropdown);
    }

    public void setHouseActionListener(ActionListener al)
    {
        buyHouseButton.addActionListener(al);
        buyHotelButton.addActionListener(al);
        closeButton.addActionListener(al);
    }

    public String getSelection()
    {
        return dropdown.getSelectedItem().toString();
    }

    public void closeHouseFrame()
    {
        houseFrame.setVisible(false);
    }

    public void aITurn()
    {
        rentButton.setVisible(false);
        buyButton.setVisible(false);
        houseHotelButton.setVisible(false);
        endTurnButton.setEnabled(true);
    }

    public static void main(String[] args)
    {
        View n = new View();
        n.updateOutput("Test1");
        n.updateOutput("Test2");
        n.updateOutput("Test3");
        n.updateOutput("Test4");
        n.updateOutput("Test5");
        n.updateOutput("Test6");
        n.updateOutput("Test7");
        n.updateOutput("Test8");
        n.updateOutput("Test9");
        n.updateOutput("Test10");
        n.updateOutput("Test11");
        n.updateOutput("Test12");
        n.updateOutput("Test13");
        n.updateOutput("Test14");
        n.updateOutput("Test15");
        n.updateOutput("Test16");
        n.updateBalance("10000");
        n.updateBalance("20000");
       // n.startGame();
        //n.setRoll();
        //n.setButtons();
        //n.setBuyable();
        //n.setHouseHotelBuyable();
/*
        String[] str = {"C1", "C2", "C3", "C4"};
        n.dropdown = new JComboBox<String>(str);
        n.dropdown.setVisible(true);
        n.dropdown.setFont(font2);
        n.dropdownPanel.add(n.dropdown);
        n.openHouseBuy();
        while(true) {
            System.out.println(n.getSelection());
        }

*/
    }
}
