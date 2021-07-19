package week05.muhammad.id.ac.umn;

public class Rectangle extends Shape {
    private int length, width;

    public Rectangle() {}
    public Rectangle(int length, int width, String color) {
        super(color);
        this.length = length;
        this.width = width;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public double getArea() {
        return length * width;
    }

    public double getPerimeter() {
        return 2 * (length + width);
    }
}
