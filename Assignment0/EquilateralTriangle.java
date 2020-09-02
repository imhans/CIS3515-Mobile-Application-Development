public class EquilateralTriangle extends Triangle {
    
    Double side;

    public EquilateralTriangle(String name) {
        super(name);
    }
    
    public void setDimensions(double side) {
        this.side = side;
    }

    @Override
    public void printDimensions() {
        System.out.println("Name: " + getName() + " | Dimension: " + getArea());
    }
    @Override
    public double getArea() {
        double area = (Math.sqrt(3)/4) * side * side;
        return area;
    }
}