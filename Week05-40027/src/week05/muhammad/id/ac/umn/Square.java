package week05.muhammad.id.ac.umn;

public class Square extends Shape {
    private int side;

    public Square() {}
    public Square(int side, String color) {
        super(color);
        this.side = side;
    }

    public int getSide() {
        return side;
    }

    public double getArea() {
        return side * side;
    }

    public double getPerimeter() {
        return 4 * side;
    }
}
