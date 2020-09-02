public class App {
    
    public static void main(String args[]) {
        
        Square sqre = new Square("Square");
        Circle circle = new Circle("Circle");
        Triangle triangle = new Triangle("Triangle");
        EquilateralTriangle et = new EquilateralTriangle("ETriangle");
        
        sqre.setDimensions(4, 4);
        sqre.printDimensions();

        circle.setDimensions(5);
        circle.printDimensions();

        triangle.setDimensions(3,4,5);
        triangle.printDimensions();

        et.setDimensions(3);
        et.printDimensions();
    }
    
}