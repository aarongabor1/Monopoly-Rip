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
    private JTextField balance;
    private JTextArea properties;
    private JPanel rollPanel;
    private JPanel buttonPanel;
    private JButton rollButton;
    private JButton buyButton;
    private JButton endTurnButton;
    private JTextField playerSelection;
    private JLabel playerName;
    private JPanel playerSelectionPanel;
    private JButton submit;
    private JLabel selectionText;

    public View()
    {
        frame = new JFrame("Monopoly");
        JPanel bottomPanel = new JPanel();
        frame.setSize(1000,650);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainPanel = new JPanel(new GridLayout(3,1));
        mainPanel.setVisible(true);

        playerSelectionPanel = new JPanel();
        playerSelectionPanel.setLayout(new GridLayout(3,1));
        playerSelectionPanel.setVisible(true);
        frame.add(playerSelectionPanel);

        selectionText = new JLabel("Please Select The Amount of Players (2+):");
        selectionText.setHorizontalAlignment(JLabel.CENTER);

        playerSelection = new JTextField();
        playerSelection.setEditable(true);
        playerSelection.setActionCommand("Player Number");

        submit = new JButton("Submit");
        submit.setActionCommand("Submit");


        playerSelection.setFont(font5);

        playerSelectionPanel.add(selectionText);
        playerSelectionPanel.add(playerSelection);
        playerSelectionPanel.add(submit);

        output = new JTextArea();
        output.setFont(font2);
        output.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(output, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        mainPanel.add(scrollPane);
        updatePanel = new JPanel(new GridLayout(1,3));
        balance = new JTextField();
        balance.setFont(font1);
        balance.setEditable(false);
        balance.setBackground(Color.white);
        balance.setHorizontalAlignment(JTextField.CENTER);
        playerName = new JLabel();
        playerName.setVisible(true);
        playerName.setFont(font1);
        playerName.setBackground(Color.white);
        properties = new JTextArea();
        properties.setFont(font3);
        properties.setEditable(false);
        JScrollPane scrollPane1 = new JScrollPane(properties, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        updatePanel.add(balance);
        updatePanel.add(playerName);
        updatePanel.add(scrollPane1);
        mainPanel.add(updatePanel);
        mainPanel.add(bottomPanel);
        bottomPanel.setVisible(true);
        rollPanel = new JPanel(new GridLayout(1, 2));
        rollButton = new JButton("Roll");
        rollButton.setEnabled(true);
        rollButton.setFont(font4);
        rollButton.setBackground(Color.CYAN);
        rollPanel.add(rollButton);
        rollPanel.setVisible(false);
        rollPanel.setBackground(Color.white);
        bottomPanel.add(rollPanel);
        buttonPanel = new JPanel(new GridLayout(1,2));
        buttonPanel.setBackground(Color.white);
        buyButton = new JButton("Buy");
        buyButton.setEnabled(false);
        buyButton.setFont(font4);
        buyButton.setBackground(new Color(124,252,0));
        buttonPanel.add(buyButton);
        endTurnButton = new JButton("End Turn");
        endTurnButton.setEnabled(true);
        endTurnButton.setFont(font4);
        endTurnButton.setBackground(Color.pink);
        buttonPanel.add(endTurnButton);
        buttonPanel.setVisible(false);
        bottomPanel.add(buttonPanel);
        frame.setVisible(true);
    }

    public void updateOutput(String str)
    {
        output.append(str + "\r\n");
        output.setCaretPosition(output.getDocument().getLength());
    }

    public void updateBalance(String str)
    {
        balance.setText(null);
        balance.setText("$" + str + "\r\n");
    }

    // Now updates the JLabel
    public void inputFailed()
    {
        //playerSelection.setText(null);
        //playerSelection.setText("Enter Number of Players: ");
        selectionText.setText("Please Enter a Valid Number of Players (2+):");
    }

    public void updateProperties(ArrayList<Property> arrayList)
    {
        properties.setText(null);
        for(Property p: arrayList)
        {
            properties.append(p.getName());
        }
    }

    public void updatePlayerName(String str)
    {
        playerName.setText(str);
    }

    public String getPlayerAmount()
    {
        return playerSelection.getText();
    }

    // Put all ALs into one method for simplicity
    public void monopolyActionListener(ActionListener o)
    {
        rollButton.addActionListener(o);
        buyButton.addActionListener(o);
        endTurnButton.addActionListener(o);
        playerSelection.addActionListener(o);
        submit.addActionListener(o);
    }

    // Currently can be deleted if you keep the menu I made
    public int getPlayerNumber()
    {
        String pn = playerSelection.getText();
        pn.substring(23);
        pn.replaceAll("\\s+", "");
        return Integer.valueOf(pn);
    }

    public void startGame()
    {
        frame.remove(playerSelectionPanel);
        frame.add(mainPanel);
        frame.pack();
        frame.setSize(1000,650);
    }

    public void setRoll()
    {
        buttonPanel.setVisible(false);
        rollPanel.setVisible(true);
    }

    public void setButtons()
    {
        rollPanel.setVisible(false);
        buttonPanel.setVisible(true);
        buyButton.setEnabled(false);
    }

    public void setBuyable()
    {
        buyButton.setEnabled(true);
    }
}