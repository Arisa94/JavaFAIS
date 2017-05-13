/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Arisa
 */
public class Helper{
    private static final int LENGTH = 4;
	private static final int CHARS = 2;

	private static final java.util.Random rnd = new java.util.Random();
	private static final StringBuilder sb = new StringBuilder(LENGTH);

	public static String generate() {
		sb.delete(0, LENGTH);
		for (int i = 0; i < LENGTH; i++) {
			sb.append((char) (65 + rnd.nextInt(CHARS)));
		}

		return sb.toString();
	}

	/*public static void main(String[] args) {
		
		// uruchomienie tego programu powinno pokazac, ze
		// wygenerowane ciagli moga sie powtarzac!!!
		for (int i = 0; i < Math.pow(CHARS,LENGTH); i++) {
			System.out.println("> " + i + " : " + generate());
		}
	}*/
}
