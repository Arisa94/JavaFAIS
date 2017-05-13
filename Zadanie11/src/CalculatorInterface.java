public interface CalculatorInterface {
	/**
	 * Ustawia wartosc w rejestrze 1.
	 * 
	 * <pre>
	 * Register1 = v
	 * </pre>
	 * 
	 * @param v
	 *            wartosc do zapisania w rejestrze
	 */
	void setReg1(int v);

	/**
	 * Ustawia wartosc w rejestrze 2.
	 * 
	 * <pre>
	 * Register2 = v
	 * </pre>
	 * 
	 * @param v
	 *            wartosc do zapisania w rejestrze
	 */
	void setReg2(int v);

	/**
	 * Zwraca zawartosc rejestru 1.
	 * 
	 * @return zawartosc rejestru 1
	 */
	int getReg1();

	/**
	 * Wpisuje do rejestrow Register1 i Register2 zera.
	 * 
	 * <pre>
	 * Register1 = 0
	 * Register2 = 0
	 * </pre>
	 */
	void clear();

	/**
	 * Zamienia miejscami zawartosc rejestru 1 i rejestru 2. Metoda dziala wg. pseudo-kodu:
	 * 
	 * <pre>
	 * tmp = Register1
	 * Register1 = Register2
	 * Register2 = tmp
	 * </pre>
	 */
	void swap();

	/**
	 * Dodaje zawartosc rejestru 1 do rejestru 2. Wynik znajduje sie w rejestrze
	 * 2.
	 * 
	 * <pre>
	 * Register2 = Register1 + Register2
	 * </pre>
	 */
	void add();

	/**
	 * Wykonuje na rejestrze 1 dzialanie opisane pseudo-kodem.
	 * 
	 * <pre>
	 * tmp = Register1
	 * IF ( tmp == expected ) THEN
	 *    Register1 = v
	 * ENDIF
	 * return tmp;
	 * </pre>
	 * 
	 * Czyli metoda zmienia zawartosc rejestru 1 na podana o ile w rejestrze
	 * znajduje sie wartosc expected. Metoda zwraca wartosc rejestru 1, ktora
	 * znajdowala sie w nim przed sprawdzeniem warunku
	 * 
	 * @param expected
	 *            oczekiwana zawartosc rejestru 1
	 * @param v
	 *            wartosc do wpisania, o ile w rejestrze 1 jest oczekiwana
	 *            wartosc
	 * @return poczatkowa wartosc rejestru 1
	 */
	int setIfMatchReturnOld(int expected, int v);
}