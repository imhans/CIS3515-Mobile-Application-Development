public class Square extends Shape {
    Double length;
    Double height;

    public Square(String name) {
        super(name);
    }

    public void setDimensions(double length, double height) {
        this.length = length;
        this.height = height;
    }

    @Override
    public void printDimensions() {
        System.out.println("Name: " + getName() + " | Dimension: " + getArea());
    }
    @Override
    public double getArea() {
        double area = length * height;
        return area;
    }
}