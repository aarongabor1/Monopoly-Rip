import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/**
 * Controller Class for the Monopoly GUI
 * @author Brady Norton
 * Modifications by Cam Sommerville
 */
public class Controller implements ActionListener, Serializable
{

    // Monopoly Model
    private Game m;

    // GUI
    private View v;

    // Build Property Frame
    private BuildPropertyView buildView;

    /**
     * constructor for controller class
     *
     * @param m
     * @param v
     */
    public Controller(Game m, View v)
    {
        this.m = m;
        this.v = v;
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
           roll();

           if(m.doesPlayerOwnFullSet())
           {
               v.setHouseHotelBuyable();
           }
        }
        else if(o.equals("Buy"))
        {
            buy();
        }
        else if(o.equals("Pay Rent"))
        {
            payRent();
        }
        else if (o.equals("House/Hotel"))
        {
            // Update View
            //v.openHouseBuy();
            buildView = new BuildPropertyView(m.getCompleteSetProperties(m.getCurrentPlayer()),m.getCurrentPlayer());
            buildView.buildPropertyActionListeners(new BuildPropertyController(m,buildView,v));

        }
        else if(o.equals("Submit"))
        {
            System.out.println("Players Selected: " + v.getPlayerAmount());

            // Check if input is valid
            if(m.checkPlayerAmount(v.getPlayerAmount()) && m.checkPlayerAmount(v.getAIAmount()))
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
        else if(o.equals("Pay 50"))
        {
            m.getCurrentPlayer().payJailor();
            v.updateOutput("Player paid to get out of jail");
            v.turnOffButtons();

        }
        else if(o.equals("Save Game"))
        {
            try {
                saveToFile("save.ser");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            v.updateOutput("Game Saved");
        }
        else if(o.equals("Load Game"))
        {
            Controller loadedGame = null;
            File file = null;
            JFileChooser fc = new JFileChooser();
            int r = fc.showOpenDialog(null);

            if (r == JFileChooser.APPROVE_OPTION) {
                file = fc.getSelectedFile();
            }
            if (!(file.getName().equals("save.ser"))){
                v.updateOutput("Invalid File");
                return;
            }
            try {
                loadedGame = Controller.loadFromFile(file);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }
            this.disableGUI();
            loadedGame.displayGUI();
        }
        else if(o.equals("End Turn"))
        {
            endTurn();
        }
        else
        {
            System.out.println("Action Not Recognized");
        }

    }


    /**
     * Outputs that a house has been bought
     *
     **/
    public void houseOutput(String p){
        v.updateOutput(m.getCurrentPlayer() + " bought a house on: "+ p);
    }

    /**
     * Outputs that a hotel has been bought
     **/
    public void hotelOutput(String p){
        v.updateOutput(m.getCurrentPlayer() + " bought a hotel on: "+ p);
    }


    /**
     * method to simulate a roll in the model and update the view accordingly
     */
    public void roll(){
        // Tell Model that user selected Roll command
        m.roll();

        // Update view with roll results
        rollUpdate();

        // Update user options for the property they landed on
        propertyOptions();
    }

    /**
     * method to simulate a purchase in the model and update the view accordingly
     */
    public void buy(){
        // Test
        v.updateOutput("Buy Selected");

        // Update Model and View
        buyUpdate();
        v.turnOffButtons();
    }

    /**
     * method to pay rent and update the view accordingly
     */
    public void payRent(){
        v.updateOutput("Pay Rent Selected");
        int numInSet;
        if(m.getLandedOnProperty() instanceof Railroad){
            numInSet = this.m.getNumInSetOwned(((Railroad) this.m.getCurrentPlayer().getPosition()).getOwner(), (Railroad) this.m.getCurrentPlayer().getPosition());
            v.updateOutput(m.getCurrentPlayer().getName() + " paid " + ((Railroad) m.getLandedOnProperty()).getOwner().getName() + ": $" + ((Railroad) m.getLandedOnProperty()).getRent(numInSet));
        }else if(m.getLandedOnProperty() instanceof Utility) {
            numInSet = this.m.getNumInSetOwned(((Utility) this.m.getCurrentPlayer().getPosition()).getOwner(), (Utility) this.m.getCurrentPlayer().getPosition());
            v.updateOutput(m.getCurrentPlayer().getName() + " paid " + ((Utility) m.getLandedOnProperty()).getOwner().getName() + ": $" + ((Utility) m.getLandedOnProperty()).getRent(numInSet,m.getCurrentRoll()));
        }else{
            numInSet = this.m.getNumInSetOwned(((Property) this.m.getCurrentPlayer().getPosition()).getOwner(), (Property) this.m.getCurrentPlayer().getPosition());
            boolean hotel = ((Property) this.m.getCurrentPlayer().getPosition()).hasHotel();
            v.updateOutput(m.getCurrentPlayer().getName() + " paid " + ((Property) m.getLandedOnProperty()).getOwner().getName() + ": $" + ((Property) m.getLandedOnProperty()).getRent(numInSet, hotel));
        }
        m.payRent();
        v.updateBalance(String.valueOf(m.getCurrentPlayer().getBalance()));
        v.setEndTurn();
        v.turnOffButtons();
    }

    /**
     * Method used to end the current players turn
     */
    public void endTurn(){
        if (m.getCurrentPlayer().getRoll(1) == m.getCurrentPlayer().getRoll(2)
                && !m.getCurrentPlayer().isInJail() && m.getCurrentPlayer().getNumDoubles() < 3
                && !(m.getCurrentPlayer().getJailedTurns() > 0)){
            if(m.getCurrentPlayer() instanceof AI){
                v.updateOutput("AI rolled doubles, It will roll again");
                ((AI) m.getCurrentPlayer()).AITurn(m);
                return;
            }
            else {
                v.updateOutput("Player rolled doubles, roll again");
                v.setRoll();
                return;
            }
        }
        v.updateOutput("End Turn Selected");
        m.endTurn();
        endTurnUpdate();
        buildProperty();
    }

    /**
     * Tells the Model to setup and start the game with the number of players selected in menu
     */
    private void startGame()
    {
        // Setup the Model
        m.setup(Integer.parseInt(v.getPlayerAmount()),Integer.parseInt(v.getAIAmount()),this);

        // Setup the View
        v.startGame();
        v.setRoll();
        v.setHouseHotelBuyable();

        // Test
        System.out.println("Current Player: "+m.getCurrentPlayer().getName());
    }


    /**
     * update view for current player
     *
     * @param cp
     */
    public void updatePlayer(Player cp)
    {
        // Update View Player Data
        v.updatePlayerName(cp.getName());
        v.updateBalance(String.valueOf(cp.getBalance()));
        v.updateProperties(cp.getProperties());
        v.setRoll();
        if(cp.isInJail() && (cp instanceof AI))
        {
            v.updateOutput("This AI is in Jail");
            v.aITurn();
        }
        if(cp.isInJail()){
            v.updateOutput("This Player is in Jail");
            v.setJailed((m.getCurrentPlayer().getJailedTurns() == 4));
        }

        if(cp instanceof AI){
            v.aITurn();
            ((AI) cp).AITurn(m);
        }
        else {
            // Update View Buttons
            v.setRoll();
        }
    }

    /**
     * update output in view with roll data
     */
    private void rollUpdate()
    {
        v.updateOutput("Roll Selected");

        // Players roll number
        v.updateOutput(m.getCurrentPlayer().getName() + " rolled a " + m.getCurrentPlayer().getRoll(1)
                + " and a " + m.getCurrentPlayer().getRoll(2));

        if(m.getCurrentPlayer().isInJail() && m.getCurrentPlayer().getJailedTurns() == 1){
            v.updateOutput("Rolled doubles three times and sent to Jail");
            return;
        }

        if(m.getCurrentPlayer().getJailedTurns() > 0){
            if (m.getCurrentPlayer().isInJail()){
                v.updateOutput("Player still in jail");
                v.setJailed((m.getCurrentPlayer().getJailedTurns() == 4));
            }
            else if (m.getCurrentPlayer().getRoll(1) == m.getCurrentPlayer().getRoll(2)){
                v.updateOutput("Rolled doubles and escaped Jail");
                //m.endTurn();
                //endTurnUpdate();
            }
            return;
        }

        if(m.getCurrentPlayer().getPosition().getIndex() != 30) {
            // Players New Position on board
            v.updateOutput("Position on board: " + m.getCurrentPlayer().getPosition().getIndex());
        }
        else{
            v.updateOutput("Landed on: Go To Jail");
            return;
        }
        if(m.getCurrentPlayer().isInJail() && m.getCurrentPlayer().getJailedTurns() == 1){
            v.updateOutput("Rolled doubles three times and sent to Jail");
            return;
        }

        if(m.getCurrentPlayer().getJailedTurns() > 0){
            if (m.getCurrentPlayer().isInJail()){
                v.updateOutput("Player still in jail");
                v.setJailed((m.getCurrentPlayer().getJailedTurns() == 4));
            }
            else if (m.getCurrentPlayer().getRoll(1) == m.getCurrentPlayer().getRoll(2)){
                v.updateOutput("Rolled doubles and escaped Jail");
                //m.endTurn();
                //endTurnUpdate();
            }
            return;
        }

        if(m.getCurrentPlayer().getPosition().getIndex() != 30) {
            // Players New Position on board
            v.updateOutput("Position on board: " + m.getCurrentPlayer().getPosition().getIndex());
        }
        else{
            v.updateOutput("Landed on: Go To Jail");
            return;
        }
                // Players new position
        String property = m.getCurrentPlayer().getPosition().getName();

        if(m.isPropertyEmpty())
        {
            v.updateOutput(m.getCurrentPlayer().getName()+" landed on an empty space");
        }
        else if (m.getCurrentPlayer().getPosition() instanceof Property){
            v.updateOutput(m.getCurrentPlayer().getName()+" landed on: "+m.getCurrentPlayer().getPosition().getName()
                    + " Set: " + m.getCurrentPlayer().getPosition().getSet());
        }
        else {
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
            //v.gameOver();
        }

        // Update the View for the next Player
        else {updatePlayer(m.getCurrentPlayer());}
    }

    /**
     * Builds the Property Buying interface
     */
    public void buildProperty()
    {
        if(m.doesPlayerOwnFullSet())
        {
            // Update View
            v.setHouseHotelBuyable();
        }
        else
        {
            v.disableHouseHotelBuyable();
        }
    }

    /**
     * Method to save the game to a file using Java Serializable
     * @param fileName
     * @throws IOException
     */
    public void saveToFile(String fileName) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(this);
        objectOutputStream.close();
        fileOutputStream.close();
    }

    /**
     * Method to create a new game from a saved file.
     * @param file
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static Controller loadFromFile(File file) throws IOException, ClassNotFoundException {
        Controller c = null;
        FileInputStream fileInputStream = new FileInputStream(file);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        c = (Controller) objectInputStream.readObject();
        objectInputStream.close();
        fileInputStream.close();
        return c;
    }

    public  void displayGUI(){
        v.displayGUI();
    }

    public void disableGUI(){
        v.disableGUI();
    }
    public void updateGUIOutput(String str){
        v.updateOutput(str);
    }

}

/**
 *
 */

/**
 *
 */

class BuildPropertyController implements ActionListener
{
    // Monopoly Model
    private Game model;

    // Monopoly View
    private BuildPropertyView view;

    // Main Game View
    private View mainView;

    public BuildPropertyController(Game m, BuildPropertyView v, View v2)
    {
        this.model = m;
        this.view = v;
        this.mainView = v2;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        // Get action command
        String o = e.getActionCommand();

        if(o.equals("Buy House"))
        {
            // Update the Model
            buyHouse(view.getSelection());

            // Update view
            mainView.updateOutput(model.getCurrentPlayer().getName() + " just bought a house on: " + view.getSelection());

            // Close frame
            view.dispose();
        }
        else if(o.equals("Buy Hotel"))
        {
            buyHotel(view.getSelection());
            mainView.updateOutput(model.getCurrentPlayer().getName() + " just bought a hotel on: " + view.getSelection());
            view.dispose();

        }
        else if(o.equals("Close"))
        {
            mainView.updateProperties(model.getCompleteSetProperties(model.getCurrentPlayer()));

            // Close View
            view.dispose();
        }
        else if(o.equals("Selected Property"))
        {
            // Check if house can be purchased for selected property
            setViewOptions(view.getSelection());

            System.out.println(model.getPropertyByName(view.getSelection()).getName()+" house amount: " + model.getPropertyByName(view.getSelection()).getHouses());
        }
        else
        {
            // In-Line Test
            System.out.println("Command not recognized");
        }
    }

    private void setViewOptions(String s)
    {
        // Both buttons disabled by default
        view.disableHotelAndHouse();

        if(model.canBuyHouse(model.getPropertyByName(s)) && model.getPropertyByName(s).getHouses()<4)
        {
           view.setBuyHouseButton();
        }
        else if(model.canBuyHotel(model.getPropertyByName(s)) && model.getPropertyByName(s).getHouses()==4 && !(model.getPropertyByName(s).hasHotel()))
        {
            view.setBuyHotelButton();
        }
    }

    private void buyHouse(String s)
    {
        Property temp = model.getPropertyByName(s);

        // Update Model
        System.out.println("Houses Before: " + model.getPropertyByName(s).getHouses());
        model.buyHouse(s);
        System.out.println("Houses After: " + model.getPropertyByName(s).getHouses());

        updateView();

    }

    private void buyHotel(String s)
    {
        model.buyHotel(s);

        updateView();
    }

    private void updateView()
    {
        mainView.updateBalance(""+model.getCurrentPlayer().getBalance());
    }
}