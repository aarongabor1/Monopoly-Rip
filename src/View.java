import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class View
{
    private JPanel mainPanel;
    private JTextArea output;
    private JPanel updatePanel;
    private JTextArea balance;
    private JTextArea properties;
    private JPanel rollPanel;
    private JPanel buttonPanel;
    private JButton rollButton;
    private JButton buyButton;
    private JButton endTurnButton;
    public View()
    {
        JFrame frame = new JFrame("Monopoly");
        JPanel bottomPanel = new JPanel();
        frame.setSize(1000,750);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3,1));
        frame.add(mainPanel);
        mainPanel.setVisible(true);
        output = new JTextArea();
        mainPanel.add(output);
        updatePanel = new JPanel();
        updatePanel.setLayout(new GridLayout(1,2));
        balance = new JTextArea();
        properties = new JTextArea();
        updatePanel.add(balance);
        updatePanel.add(properties);
        mainPanel.add(updatePanel);
        mainPanel.add(bottomPanel);
        bottomPanel.setVisible(true);
        rollPanel = new JPanel();
        rollButton = new JButton("Roll");
        rollButton.setEnabled(true);
        rollPanel.add(rollButton);
        rollPanel.setVisible(false);
        bottomPanel.add(rollPanel);
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1,2));
        buyButton = new JButton("Buy");
        buyButton.setEnabled(false);
        buttonPanel.add(buyButton);
        endTurnButton = new JButton("End Turn");
        endTurnButton.setEnabled(true);
        buttonPanel.add(endTurnButton);
        buttonPanel.setVisible(false);
        bottomPanel.add(buttonPanel);
        frame.setVisible(true);
    }

    public void updateOutput(String str)
    {
        output.append(str + "\r\n");
    }

    public void updateBalance(String str)
    {
        balance.append("$" + str + "\r\n");
    }

    public void updateProperties(ArrayList<Property> arrayList)
    {
        for(Property p: arrayList)
        {
            properties.append(p.getName());
        }
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
        n.updateBalance("10000");
        n.updateBalance("20000");
        n.setRoll();
    }
}
