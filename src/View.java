import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * @author Aaron Gabor
 * @version 3.1.2
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

    private ArrayList<String> propertyNames;

    private JList pList;

    private JScrollPane sp;

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

        //Creates the player selection panel
        playerSelectionPanel = new JPanel();
        playerSelectionPanel.setLayout(new GridLayout(3, 1));
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
        aISelection = new JTextField();
        aISelection.setEditable(true);
        aISelection.setActionCommand("AI Number");
        aISelection.setFont(font5);

        //Creates a button to allow users to submit their number of players
        submit = new JButton("Submit");
        submit.setFont(font1);
        submit.setBackground(Color.cyan);

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
        playerName.setHorizontalAlignment(SwingConstants.CENTER);
        playerName.setVisible(true);
        playerName.setFont(font1);
        playerName.setBackground(Color.white);
        playerName.setOpaque(true);

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
        rollPanel.setOpaque(true);
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
        houseHotelButton.setBackground(new Color(98, 252, 224));
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
        dropdownPanel.setBackground(Color.white);
        dropdownPanel.setVisible(true);
        houseFrame.add(dropdownPanel);

        //Creates button panel
        houseButtonPanel = new JPanel(new GridLayout(1,3));
        houseButtonPanel.setVisible(true);
        houseFrame.add(houseButtonPanel);

        //Creates button for buying houses and hotels
        buyHouseButton = new JButton("Buy House");
        buyHouseButton.setFont(font5);
        buyHouseButton.setBackground(new Color(240, 255, 77));
        houseButtonPanel.add(buyHouseButton);
        buyHotelButton = new JButton("Buy Hotel");
        buyHotelButton.setFont(font5);
        buyHotelButton.setBackground(new Color(246, 77, 255));
        houseButtonPanel.add(buyHotelButton);
        closeButton = new JButton("Close");
        closeButton.setFont(font5);
        closeButton.setBackground(new Color(237, 24, 24));
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

    /**
     * This method will show the house frame
     */
    public void enableBuyHouseButton()
    {
        buyHouseButton.setEnabled(true);
        buyHotelButton.setEnabled(false);
    }

    public void enableBuyHotelButton()
    {
       buyHotelButton.setEnabled(true);
       buyHouseButton.setEnabled(false);
    }

    public void disableHouseHotelBuyable()
    {
        houseHotelButton.setEnabled(false);
    }

    public void disableHotelAndHouse()
    {
        buyHotelButton.setEnabled(false);
        buyHouseButton.setEnabled(false);
    }

    public void openHouseBuy()
    {
        houseFrame.setVisible(true);
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
     * This is a method that will set up the dropdown menu of the properties
     * that the user can buy houses and hotels on.
     * @param arrayList A ArrayList of Property that can have houses or hotels.
     */
    public void setUpDropdown(ArrayList<Property> arrayList)
    {
        /**
        // Set up property list
        propertyModel = new DefaultListModel<>();

        String[] names = new String[arrayList.size()-1];
        for(int i = 0; i < arrayList.size(); i++)
        {
            // Populate Model
            propertyModel.addElement(arrayList.get(i).getName());

            names[i] = arrayList.get(i).getName();
        }
        //dropdown = new JComboBox<String>(names);
        //dropdown.setActionCommand("dropdown");
        //dropdown.setVisible(true);
        //dropdown.setFont(font2);
        //dropdownPanel.add(dropdown);

        // JList
        pList = new JList<String>(propertyModel);

        // Scroll pane
        //sp = new JScrollPane(pList);

        // Default Combo Box
        DefaultComboBoxModel cbm = new DefaultComboBoxModel();

        // Add to panel
        //dropdownPanel.add(sp);
         **/
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
        cb.insertItemAt(" ",0);
        cb.setSelectedIndex(0);
        cb.setActionCommand("Selected Property");

        // Add to panel
        cb.setVisible(true);
        cb.setFont(font2);
        dropdownPanel.add(cb);
    }

    /**
     * This is a method that will add ActionListener to everything that needs one
     * on the house and hotel buying frame.
     * @param al An ActionListener object to be added.
     */
    public void setHouseActionListener(ActionListener al)
    {
        buyHouseButton.addActionListener(al);
        buyHotelButton.addActionListener(al);
        closeButton.addActionListener(al);
        cb.addActionListener(al);
    }

    /**
     * This method will return what the user has selected from the
     * dropdown menu.
     * @return A String that contains the selection.
     */
    public String getSelection()
    {
        return cb.getSelectedItem().toString();
        //return pList.getSelectedValue().toString();
    }

    /**
     * This method will close the house and hotel buying frame.
     */
    public void closeHouseFrame()
    {
        houseFrame.setVisible(false);
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
}