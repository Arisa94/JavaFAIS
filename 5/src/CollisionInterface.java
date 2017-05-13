public interface CollisionInterface {
	/**
	 * Metoda ustala liczbe obiektow, ktore beda sie poruszac.
	 * @param value liczba obiektow
	 */
	void setNumberOfObjects( int value );
	
	/**
	 * Metoda ustala poczatkowa pozycje obiektu
	 * @param objectID identyfikator obiektu (od 0 do liczba obiektow-1)
	 * @param ip poczatkowa pozycja obiektu 
	 */
	void setInitialPosition( int objectID, Point2D ip );
	
	/**
	 * Zleca zmiane polozenia obiektu o podanym identyfikatorze
	 * @param objectID identyfikator obiektu (od 0 do liczba obiektow-1)
	 * @param stepLength dlugosc kroku
	 * @param angle kurs ruchu obiektu
	 */
	void moveObject( int objectID, int stepLength,  int angle );
	
	/**
	 * Metoda zwraca tablice identyfikatorow obiektow, ktore znajduja
	 * sie w tym samym miejscu co obiekt o podanym objectID.
	 * @param objectID identyfikator obiektu (od 0 do liczba obiektow-1)
	 * @return tablica identyfikatorow obiektow, ktore znajduja sie w tym
	 * samym miejscu co obiekt o wskazanym objectID
	 */
	int[] getCollictionsOfObject( int objectID );
	
	/**
	 * Metoda zwraca polozenie obiektu o wkazanym identyfikatorze.
	 * @param objectID identyfikator obiektu (od 0 do liczba obiektow-1)
	 * @return aktualne polozenie obiektu o wskazanym identyfikatorze
	 */
	Point2D getPosition( int objectID );
}