public class Piece {
    private String name;
    private Square position;

    public Piece(String name, Square position){
        this.name = name;
        this.position = position;
    }

    public Square getPosition() {
        return position;
    }

    public void setPosition(Square position) {
        this.position = position;
    }
}
