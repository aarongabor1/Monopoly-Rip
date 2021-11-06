import javax.swing.*;
import javax.swing.text.StyleConstants;
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
    private JScrollPane scrollPane;

    public View()
    {
        JFrame frame = new JFrame("Monopoly");
        JPanel bottomPanel = new JPanel();
        frame.setSize(1000,650);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainPanel = new JPanel(new GridLayout(3,1));
        frame.add(mainPanel);
        mainPanel.setVisible(true);
        JPanel outputPanel = new JPanel();
        playerSelection = new JTextField("Enter Number of Players: ");
        playerSelection.setFont(font5);
        outputPanel.add(playerSelection);
        output = new JTextArea();
        output.setFont(font2);
        output.setEditable(false);
        scrollPane = new JScrollPane(output, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVisible(false);
        outputPanel.add(scrollPane);
        mainPanel.add(outputPanel);
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

    public void addActionListenerRoll(ActionListener al)
    {
        rollButton.addActionListener(al);
    }

    public void addActionListenerBuy(ActionListener al)
    {
        buyButton.addActionListener(al);
    }

    public void addActionListenerEndTurn(ActionListener al)
    {
        endTurnButton.addActionListener(al);
    }

    public void addActionListenerNumberPlayer(ActionListener al)
    {
        playerSelection.addActionListener(al);
    }

    public int getPlayerNumber()
    {
        String pn = playerSelection.getText();
        pn.substring(23);
        pn.replaceAll("\\s+", "");
        return Integer.valueOf(pn);
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
        n.setRoll();
        n.setButtons();
        n.setBuyable();
    }
}
