import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Aaron Gabor
 * @version 4.0.0
 */
public class View implements Serializable
{
    //Set fonts for all text in the GUI
    private static final Font font75B = new Font("Times New Roman", Font.BOLD, 75);
    private static final Font font25P = new Font("Times New Roman", Font.PLAIN, 25);
    private static final Font font15P = new Font("Times New Roman", Font.PLAIN, 15);
    private static final Font font85B = new Font("Times New Roman", Font.BOLD, 85);
    private static final Font font50B = new Font("Times New Roman", Font.BOLD, 50);
    private static final Font font25B = new Font("Times New Roman", Font.BOLD, 25);

    private JFrame frame;
    private JPanel mainPanel;
    private JTextArea output;
    private JPanel updatePanel;
    private JMenuBar menuBar;
    private JMenu game;
    private JMenuItem saveGame;
    private JMenuItem loadGame;

    private JTextField balance;
    private JTextArea properties;
    private JPanel rollPanel;
    private JPanel buttonPanel;
    private JButton rollButton;
    private JButton buyButton;
    private JButton endTurnButton;
    private JButton rentButton;
    private JTextField playerSelection;
    private JTextField aISelection;
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

    // Default List Model Variables
    private DefaultListModel propertyModel;

    private JComboBox cb;

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

        //Creates the menu bar
        menuBar = new JMenuBar();
        game = new JMenu("Game");
        menuBar.add(game);
        saveGame = new JMenuItem("Save Game");
        loadGame = new JMenuItem("Load Game");
        game.add(saveGame);
        game.add(loadGame);
        frame.setJMenuBar(menuBar);

        //Creates the player selection panel
        playerSelectionPanel = new JPanel(new GridLayout(3, 1));
        playerSelectionPanel.setBackground(Color.white);
        playerSelectionPanel.setVisible(true);
        frame.add(playerSelectionPanel);

        //Creates the player selection label
        selectionText = setupLabel(selectionText, font25B, "Please Select The Amount of Players (2+):");

        //Creates the AI selection label
        aISelectionText = setupLabel(aISelectionText, font25B, "Please Select The Amount of AI Players:");

        //Creates the Player selection entering area
        playerSelection = setupTextField(playerSelection, font50B, true, false);
        playerSelection.setActionCommand("Player Number");

        //Creates the AI selection entering area
        aISelection = setupTextField(aISelection, font50B, true, false);
        aISelection.setActionCommand("AI Number");

        //Creates a button to allow users to submit their number of players
        submit = setupButton(submit, font75B, Color.CYAN, true, "Submit");

        //Creates a blank space to place the submit button better
        JLabel temp = new JLabel();

        //Adds all elements to the player selection panel
        playerSelectionPanel.add(selectionText);
        playerSelectionPanel.add(aISelectionText);
        playerSelectionPanel.add(playerSelection);
        playerSelectionPanel.add(aISelection);
        playerSelectionPanel.add(temp);
        playerSelectionPanel.add(submit);

        //Creates the output of game information
        output = setupTextArea(output, font25P, false);
        JScrollPane scrollPane = new JScrollPane(output, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        mainPanel.add(scrollPane);

        //Creates the panel that gives player information
        updatePanel = new JPanel(new GridLayout(1, 3));

        //Creates the display for the player's money balance
        balance = setupTextField(balance, font75B, false, true);

        //Creates the display for the player's name
        playerName = setupLabel(playerName, font75B, "");
        playerName.setBackground(Color.white);
        playerName.setOpaque(true);

        //Creates the display for the properties that the player own
        properties = setupTextArea(properties, font15P, false);
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
        rollButton = setupButton(rollButton, font85B, Color.CYAN, true, "Roll");
        rollPanel.add(rollButton);
        rollPanel.setVisible(false);
        rollPanel.setBackground(Color.white);
        rollPanel.setOpaque(true);
        bottomPanel.add(rollPanel);

        //Creates a button panel for the turn buttons
        buttonPanel = new JPanel(new GridLayout(2, 2));
        buttonPanel.setBackground(Color.white);

        //Creates the buy button and adds it to the button panel
        buyButton = setupButton(buyButton, font85B, new Color(124, 252, 0), false, "Buy");
        buttonPanel.add(buyButton);

        //Creates the rent button and adds it to the button panel
        rentButton = setupButton(rentButton, font85B, Color.orange, false, "Pay Rent");
        buttonPanel.add(rentButton);

        //Creates the house/hotel button and adds it to the button panel
        houseHotelButton = setupButton(houseHotelButton, font85B, new Color(98, 252, 224), false,"House/Hotel");
        buttonPanel.add(houseHotelButton);

        //Creates the end turn button and adds it to the button panel
        endTurnButton = setupButton(endTurnButton, font85B, Color.pink, true, "End Turn");
        buttonPanel.add(endTurnButton);
        buttonPanel.setVisible(false);
        bottomPanel.add(buttonPanel);
        //frame.setVisible(true);
      
        //House Frame
        houseFrame = new JFrame("Buy Houses and Hotels");
        houseFrame.setLayout(new GridLayout(3,1));
        houseFrame.setSize(1000,650);
        houseFrame.setVisible(false);

        //Creates output for house buying
        JPanel panel = new JPanel(new GridLayout(1,2));
        panel.setVisible(true);
        houseFrame.add(panel);
        outputHouse = setupTextField(outputHouse, font25P, false, true);
        balance2 = setupTextField(balance2, font75B, false, true);
        panel.add(balance2);
        panel.add(outputHouse);

        //Creates panel for dropdown bar
        dropdownPanel = new JPanel();
        dropdownPanel.setBackground(Color.white);
        dropdownPanel.setVisible(true);
        houseFrame.add(dropdownPanel);

        //Creates button panel
        houseButtonPanel = new JPanel(new GridLayout(1,3));
        houseButtonPanel.setVisible(true);
        houseFrame.add(houseButtonPanel);

        //Creates button for buying houses and hotels
        buyHouseButton = setupButton(buyHouseButton, font50B, new Color(240, 255, 77), false, "Buy House");
        houseButtonPanel.add(buyHouseButton);
        buyHotelButton = setupButton(buyHotelButton, font50B, new Color(246, 77, 255), false, "Buy Hotel");
        houseButtonPanel.add(buyHotelButton);
        closeButton = setupButton(closeButton, font50B, new Color(237, 24, 24), true, "Close");
        houseButtonPanel.add(closeButton);
    }

    private JButton setupButton(JButton button, Font font, Color color, boolean enabled, String Name)
    {
        button = new JButton(Name);
        button.setFont(font);
        button.setBackground(color);
        button.setEnabled(enabled);
        return button;
    }

    private JLabel setupLabel(JLabel label, Font font, String text)
    {
        label = new JLabel(text);
        label.setFont(font);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        return label;
    }

    private JTextField setupTextField(JTextField textField, Font font, boolean editable, boolean isCenter)
    {
        textField = new JTextField();
        textField.setFont(font);
        textField.setEditable(editable);
        textField.setBackground(Color.white);
        if(isCenter)
            textField.setHorizontalAlignment(JTextField.CENTER);
        return textField;
    }

    private JTextArea setupTextArea(JTextArea textArea, Font font, boolean editable)
    {
        textArea = new JTextArea();
        textArea.setFont(font);
        textArea.setEditable(editable);
        return textArea;
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
        selectionText.setText("Please Enter a Valid Number of Players:");
        aISelectionText.setText("Please Enter a Valid Number of AI Players:");
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
            if (p instanceof Utility || p instanceof Railroad){
                properties.append(p.getName() + "\n");
            }
            else {
                properties.append(p.getName() + " Set: " + p.getSet() + " Houses: " + p.getHouses() + " Hotel: " + p.hasHotel() + "\n");
            }
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

    /**
     * This is a method that will get the number of players that the user entered
     * and return a string with the number.
     * @return A String that contains the number of players.
     */
    public String getPlayerAmount()
    {
        return playerSelection.getText();
    }

    /**
     * This is a method that will get the number of AI players that the user
     * entered and return a string with the number.
     * @return A String that contains the number of AI players.
     */
    public String getAIAmount()
    {
        return aISelection.getText();
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
        aISelection.addActionListener(o);
        submit.addActionListener(o);
        rentButton.addActionListener(o);
        houseHotelButton.addActionListener(o);
        saveGame.addActionListener(o);
        loadGame.addActionListener(o);
    }


    /**
     * This is a method that will update the player name that is being displayed.
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
        rentButton.setVisible(true);
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
        buyButton.setVisible(true);
    }

    /**
     * This method will enable the Rent button
     */
    public void setRentable()
    {
        rentButton.setText("Pay Rent");
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


    public void disableHouseHotelBuyable()
    {
        houseHotelButton.setEnabled(false);
    }


    /**
     * This method will update the output text area in the house and hotel
     * buying frame.
     * @param str A String containing the information to be displayed
     */
    public void updateOutputHouse(String str)
    {
        outputHouse.setText(str);
    }

    /**
     * This method will return what the user has selected from the
     * dropdown menu.
     * @return A String that contains the selection.
     */
    public String getSelection()
    {
        return cb.getSelectedItem().toString();
    }

    /**
     * This method is used to allow the information of what happened
     * in an AI's turn to be displayed to a user it will allow
     * the user to only activate the end turn button to tell the
     * game when it can continue.
     */
    public void aITurn()
    {
        rentButton.setEnabled(false);
        buyButton.setEnabled(false);
        houseHotelButton.setEnabled(false);
        endTurnButton.setEnabled(true);
    }

    /**
     * This method allows a user to pay to get out of Jail
     * @param hasToPay a boolean that when true the user has to pay to get out
     *                 of jail before they can end their turn.
     */
    public void setJailed(boolean hasToPay)
    {
        rentButton.setText("Pay 50");
        rentButton.setEnabled(true);
        endTurnButton.setEnabled(!hasToPay);
    }

    public void displayGUI(){
        frame.setVisible(true);
    }

    public void disableGUI() {
        frame.setVisible(false);
    }
}

/**
 ===========================================================================
 **/

class BuildPropertyView extends JFrame
{
    // Variables

    // Fonts
    private static final Font font50B = new Font("Times New Roman", Font.BOLD, 50);
    private static final Font font25P = new Font("Times New Roman", Font.PLAIN, 25);
    private static final Font font75B = new Font("Times New Roman", Font.BOLD, 75);

    // Buttons
    private JButton buyHouseButton;
    private JButton buyHotelButton;
    private JButton closeButton;

    // Default List Model
    private DefaultListModel propertyModel;

    // Combo Box
    private JComboBox cb;

    // Text Fields
    private JTextField balance;
    private JTextField outputHouse;


    public BuildPropertyView(ArrayList<Property> arrayList, Player cp)
    {
        // Initialize Frame
        setTitle("Buy Houses and Hotels");
        setPreferredSize(new Dimension(1000,650));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // Add Components
        initializeOptions();
        setUpDropdown(arrayList);
        add(buildPropertyPanel());

        balance.setText(""+ cp.getBalance());
        outputHouse.setText(cp.getName());

        // Finish Initialization
        pack();
        setVisible(true);
        toFront();
    }

    private JPanel buildPropertyPanel()
    {
        // Initialize Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3,1));

        // Top Panel
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(1,2));
        topPanel.add(balance);
        topPanel.add(outputHouse);
        topPanel.setVisible(true);

        // Middle Panel
        JPanel midPanel = new JPanel();
        midPanel.setBackground(Color.white);
        midPanel.add(cb);
        midPanel.setVisible(true);

        // Bottom Panel
        JPanel botPanel = new JPanel();
        botPanel.setLayout(new GridLayout(1,3));
        botPanel.add(buyHouseButton);
        botPanel.add(buyHotelButton);
        botPanel.add(closeButton);
        botPanel.setVisible(true);

        // Finish initialization
        mainPanel.add(topPanel);
        mainPanel.add(midPanel);
        mainPanel.add(botPanel);
        mainPanel.setVisible(true);

        // Return Panel
        return mainPanel;
    }

    private void initializeOptions()
    {
        // Buttons
        buyHouseButton = setupButton(buyHouseButton, font50B, new Color(240, 255, 77), false, "Buy House");
        buyHotelButton = setupButton(buyHotelButton, font50B, new Color(246, 77, 255), false, "Buy Hotel");
        closeButton = setupButton(closeButton, font50B, new Color(237, 24, 24), true, "Close");

        // Text Fields
        outputHouse = setupTextField(outputHouse, font25P, false, true);
        balance = setupTextField(balance, font75B, false, true);
    }

    private JButton setupButton(JButton button, Font font, Color color, boolean enabled, String Name)
    {
        button = new JButton(Name);
        button.setFont(font);
        button.setBackground(color);
        button.setEnabled(enabled);
        return button;
    }

    private JTextField setupTextField(JTextField textField, Font font, boolean editable, boolean isCenter)
    {
        textField = new JTextField();
        textField.setFont(font);
        textField.setEditable(editable);
        textField.setBackground(Color.white);
        if(isCenter)
            textField.setHorizontalAlignment(JTextField.CENTER);
        return textField;
    }

    /**
     * This is a method that will set up the dropdown menu of the properties
     * that the user can buy houses and hotels on.
     * @param arrayList A ArrayList of Property that can have houses or hotels.
     */
    public void setUpDropdown(ArrayList<Property> arrayList)
    {
        // Set up property list
        propertyModel = new DefaultListModel<>();

        for(int i = 0; i < arrayList.size(); i++)
        {
            // Populate Model
            propertyModel.addElement(arrayList.get(i).getName());
        }

        // Get property names
        String[] p = new String[propertyModel.getSize()];
        propertyModel.copyInto(p);

        // Set up default combo box model
        DefaultComboBoxModel cbm = new DefaultComboBoxModel(p);
        cb = new JComboBox();
        cb.setModel(cbm);
        cb.insertItemAt("",0);
        cb.setSelectedIndex(0);
        cb.setActionCommand("Selected Property");

        // Add to panel
        cb.setVisible(true);
        cb.setFont(font25P);
    }

    public void buildPropertyActionListeners(ActionListener o)
    {
        buyHouseButton.addActionListener(o);
        buyHotelButton.addActionListener(o);
        closeButton.addActionListener(o);
        cb.addActionListener(o);
    }

    /**
     * This method will return what the user has selected from the
     * dropdown menu.
     * @return A String that contains the selection.
     */
    public String getSelection()
    {
        return cb.getSelectedItem().toString();
    }

    /**
     * This method is used to toggle the buy house button.
     */
    public void setBuyHouseButton()
    {
        buyHouseButton.setEnabled(true);
    }

    /**
     * This method is used to toggle the buy hotel button.
     */
    public void setBuyHotelButton()
    {
        buyHotelButton.setEnabled(true);
    }

    public void disableHotelAndHouse()
    {
        buyHotelButton.setEnabled(false);
        buyHouseButton.setEnabled(false);
    }

}