public class Main {

    public static void main(String[] args)
    {
        Game game = new Game();
        View view = new View();
        Controller controller = new Controller(game, view);

        //game.buyHouseTest();
        //System.out.println(game.canBuyHouse());

        //game.buyHouseTest();
        //System.out.println(game.canBuyHotel());
        //controller.buildProperty();
    }
}
