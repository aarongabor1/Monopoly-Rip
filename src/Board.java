import java.io.FileReader;
import java.io.Reader;
import java.io.Serializable;
import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

/**
 * Class that simulates the Monopoly board
 * @author Cam Sommerville
 * @author Braxton Martin
 */
public class Board implements Serializable {
    private List<Square> squares;
    private final int numSquares = 40;
    private String currency = "$";

    /**
     * Constructor for Board class
     */
    public Board() throws Exception {
        squares = new ArrayList<>();
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        jfc.setDialogTitle("Choose a map");
        jfc.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JSOn files only","json");
        jfc.addChoosableFileFilter(filter);
        int returnValue = jfc.showOpenDialog(null);
        initializeMap(jfc.getSelectedFile().getName());
        createBoard();
    }

    /**
     * Creates the game board with 40 Square objects in an ArrayList
     *
     */
    private void createBoard() {
        squares.add(new Property("Go", 0, -1,-1,0,-1));
        squares.add(new Property("Ottawa U", 1, 60,1,2,50));
        squares.add(new Property("Empty", 2, -1,-1,0,-1));
        squares.add(new Property("Carleton U", 3, 60,1,2,50));
        squares.add(new Property("Empty", 4, -1,-1,0,-1));
        squares.add(new Railroad("LRT", 5, 200,9,4,-1));
        squares.add(new Property("Chevrolet", 6, 100,2,3,50));
        squares.add(new Property("Empty", 7, -1,-1,0,3-1));
        squares.add(new Property("Honda", 8, 100,2,3,50));
        squares.add(new Property("Toyota", 9, 120,2,3, 50));
        squares.add(new Jail(10));
        squares.add(new Property("Home Depot", 11, 140,3,3,100));
        squares.add(new Utility("Electric Company", 12, -1,-1,0,-1));
        squares.add(new Property("CostCo", 13, 140,3,3,100));
        squares.add(new Property("Walmart", 14, 160,3,3,100));
        squares.add(new Railroad("VIA Rail", 15, 200,9,4,-1));
        squares.add(new Property("Skip the Dishes", 16, 180,4,3,100));
        squares.add(new Property("Empty", 17, -1,-1,0,-1));
        squares.add(new Property("Lyft", 18, 180,4,3,100));
        squares.add(new Property("Uber", 19, 200,4,3,100));
        squares.add(new Property("Free Parking", 20, -1,-1,0,-1));
        squares.add(new Property("Ebay", 21, 220,5,3,150));
        squares.add(new Property("Empty", 22, -1,-1,0,-1));
        squares.add(new Property("Shopify", 23, 220,5,3,150));
        squares.add(new Property("Amazon", 24, 240,5,3,150));
        squares.add(new Railroad("GO Train", 25, 200,9,4,150));
        squares.add(new Property("Facebook", 26, 260,6,3,150));
        squares.add(new Property("LinkedIn", 27, 260,6,3,150));
        squares.add(new Utility("Water Works", 28, -1,-1,0,-1));
        squares.add(new Property("Twitter", 29, 280,6,3,150));
        squares.add(new Property("Go to Jail", 30, -1,-1,0,-1));
        squares.add(new Property("Disney Plus", 31, 300,7,3,200));
        squares.add(new Property("Crave", 32, 300,7,3,200));
        squares.add(new Property("Empty", 33, -1,-1,0,-1));
        squares.add(new Property("Netflix", 34, 320,7,3,200));
        squares.add(new Railroad("Exo Train", 35, 200,9,4,-1));
        squares.add(new Property("Empty", 36, -1,-1,0,-1));
        squares.add(new Property("Samsung", 37, 350,8,2,-1));
        squares.add(new Property("Empty", 38, -1,-1,0,-1));
        squares.add(new Property("Apple", 39, 400,8,2,-1));
    }

    public void initializeMap(String fileName) throws Exception {
        JSONParser parser = new JSONParser();

        try {

            Reader reader = new FileReader(fileName);

            Object obj = parser.parse(reader);
            JSONObject jsonObject = (JSONObject) obj;

            Set keys = jsonObject.keySet();

            Object[] obArr = keys.toArray();
            if(obArr.length ==41) {
                for (int i = 0; i <= obArr.length-14; i++) {
                    JSONObject squareObject = (JSONObject) jsonObject.get(obArr[i]);
                    Set attributes = squareObject.keySet();
                     System.out.println(attributes);
                    //attributesArr has order [set, numInSet, price, housePrice, name, index, squareType]

                    Object[] attributesArr = attributes.toArray();
                    //System.out.println(indexCheck)
                    if(attributes.size() == 1){
                        currency = squareObject.get(attributesArr[0]).toString();
                    }else if(squareObject.get(attributesArr[1]).toString().equals("Jail")){
                        Jail j = new Jail(Integer.parseInt(squareObject.get(attributesArr[0]).toString()));
                        squares.add(j);
                    }else if (squareObject.get(attributesArr[6]).toString().equals("Property")) {
                            Property p = new Property(squareObject.get(attributesArr[4]).toString(), Integer.parseInt(squareObject.get(attributesArr[5]).toString()), Integer.parseInt(squareObject.get(attributesArr[2]).toString()), Integer.parseInt(squareObject.get(attributesArr[0]).toString()), Integer.parseInt(squareObject.get(attributesArr[1]).toString()), Integer.parseInt(squareObject.get(attributesArr[3]).toString()));
                            squares.add(p);
                    } else if (squareObject.get(attributesArr[6]).toString().equals("Utility")) {
                            Utility u = new Utility(squareObject.get(attributesArr[4]).toString(), Integer.parseInt(squareObject.get(attributesArr[5]).toString()), Integer.parseInt(squareObject.get(attributesArr[2]).toString()), Integer.parseInt(squareObject.get(attributesArr[0]).toString()), Integer.parseInt(squareObject.get(attributesArr[1]).toString()), Integer.parseInt(squareObject.get(attributesArr[3]).toString()));
                            squares.add(u);
                    } else if (squareObject.get(attributesArr[6]).toString().equals("Railroad")) {
                            Railroad r = new Railroad(squareObject.get(attributesArr[4]).toString(), Integer.parseInt(squareObject.get(attributesArr[5]).toString()), Integer.parseInt(squareObject.get(attributesArr[2]).toString()), Integer.parseInt(squareObject.get(attributesArr[0]).toString()), Integer.parseInt(squareObject.get(attributesArr[1]).toString()), Integer.parseInt(squareObject.get(attributesArr[3]).toString()));
                            squares.add(r);
                    }else{
                        System.out.println("Invalid Map Format");
                        System.out.println(squares);
                        squares.clear();
                        break;
                    }
                }

            }else{
                System.out.println("Not enough Squares in Map File");
            }


            //currency = (String)currencyArr[0];

        }catch(Exception e){
            e.printStackTrace();
        }


    }

    /**
     * Getter method that returns the Property at desired index in squares ArrayList
     * @param index
     * @return Property object
     */
    public Square getProperty(int index){
        return squares.get(index);
    }

    public ArrayList<Square> getPropertySet(int setNumber)
    {
        ArrayList<Square> set = new ArrayList<>();

        for(Square s:squares)
        {
            if(s.getSet()==setNumber)
            {
                set.add(s);
            }
        }
        return set;
    }

    /**
     *  toString method
     *
     * @return String
     */
    public String toString(){
        String str = "";
        for(Square square: squares){
            str += square.toString();
            str += "\n";
        }
        return str;
    }
}