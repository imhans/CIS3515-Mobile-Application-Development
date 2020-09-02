public class Triangle extends Shape {
    
    Double firstSide;
    Double secondSide;
    Double thirdSide;

    public Triangle(String name) {
        super(name);
    }
    
    public void setDimensions(double firstSide, double secondSide, double thirdSide) {
        this.firstSide = firstSide;
        this.secondSide = secondSide;
        this.thirdSide= thirdSide;
    }

    @Override
    public void printDimensions() {
        System.out.println("Name: " + getName() + " | Dimension: " + getArea());
    }
    @Override
    public double getArea() {
        double s = (firstSide + secondSide + thirdSide) * 0.5;
        double area = (Math.sqrt(s * (s-firstSide) * (s-secondSide) * (s-thirdSide)));
        return s;
    }
}