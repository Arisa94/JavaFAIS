import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Main{
	private static final int LENGTH = 4;
	private static final int CHARS = 2;

	private static final java.util.Random rnd = new java.util.Random();
	private static final StringBuilder sb = new StringBuilder(LENGTH);
        public static List<Object> test = new ArrayList<>();
        public static ExampleClass ex = new ExampleClass();
        public static ObjectAnonymizer oa = new ObjectAnonymizer();

	public static String generate() {
		sb.delete(0, LENGTH);
		for (int i = 0; i < LENGTH; i++) {
			sb.append((char) (65 + rnd.nextInt(CHARS)));
		}

		return sb.toString();
	}

	public static void main(String[] args) throws NoSuchFieldException {
		
               //System.out.println(ex.getClass().getField("surname").getAnnotation(ToAnonimize.class).readyToAnonimize());
               
                test.add(ex);
                oa.anonimize(test);
            
		// uruchomienie tego programu powinno pokazac, ze
		// wygenerowane ciagli moga sie powtarzac!!!
		/*for (int i = 0; i < Math.pow(CHARS,LENGTH); i++) {
			System.out.println("> " + i + " : " + generate());
		}*/
                
                
                
                
	}
    
}
