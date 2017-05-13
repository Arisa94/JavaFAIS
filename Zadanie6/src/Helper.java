import java.util.List;

public class Helper {

	static List<String> magazine;
	private static int numberOfGenerateCalls;
	
	static void setMagazine( List<String> magazine ) {
		Helper.magazine = magazine;
		numberOfGenerateCalls = 0;
	}
	
	static int getNumberOfGeneratedCalls() {
		return numberOfGenerateCalls; 
	}
	
	public static String generate() {
		return magazine.get( ( numberOfGenerateCalls ++ ) % magazine.size() );
	}

}