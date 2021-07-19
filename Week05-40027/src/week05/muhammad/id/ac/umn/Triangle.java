package week05.muhammad.id.ac.umn;

public class Triangle extends Shape {
    private int base, height;

    public Triangle() {}
    public Triangle(int base, int height, String color) {
        super(color);
        this.base = base;
        this.height = height;
    }

    public int getBase() {
        return base;
    }

    public int getHeight() {
        return height;
    }

    public double getArea() {
        return 0.5 * (base * height);
    }

    public double getPerimeter() {
        double hypotenuse = Math.sqrt(Math.pow(base, 2) + Math.pow(height, 2));

        return base + height + hypotenuse;
    }
}
