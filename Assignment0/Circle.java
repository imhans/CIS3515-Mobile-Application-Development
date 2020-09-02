public class Circle extends Shape {
    
    Double radius;

    public Circle(String name) {
        super(name);
    }
    
    public void setDimensions(double radius) {
        this.radius = radius;
    }
    
    @Override
    public void printDimensions() {
        System.out.println("Name: " + getName() + " | Dimension: " + getArea());
    }
    @Override
    public double getArea() {
        double area = Math.pow(radius,2) * 3.14;
        return area;
    }
}