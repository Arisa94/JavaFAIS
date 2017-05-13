
public class Main{

    public static void main(String[] args){
        Tester theT = new Tester();
        	theT.input_out();
        theT.run();
        	theT.output_out();
        System.out.println("_____________________________________");
        System.out.println("TOTAL ERRORS: " + theT.check());
        System.out.println("_____________________________________");
    }

}
