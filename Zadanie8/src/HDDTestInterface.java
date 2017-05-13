
public interface HDDTestInterface {
    
    /**
	 * Metoda przekazuje dysk do testu.
	 * 
	 * @param hddInterface
	 *            referencja do obiektu-dysku
	 */
	void setHDD(HDDInterface hddInterface);

	/**
	 * Zwraca liczbe zdarzen typu BadSector
	 * 
	 * @return liczba blednych sektorow
	 */
	int getNumberOfBadSectorsEvents();

	/**
	 * Zwraca liczbe zdarzen typu DataCorruption
	 * 
	 * @return liczba wykrytych przypadkow (sektorow) uszkodzenia danych
	 */
	int getNumberOfDataCorruptionEvents();

	/**
	 * Zwraca liczbe zdarzen typu DiskTooHot
	 * 
	 * @return liczba zarejestrowanych zdarzen typu DiskTooHot
	 */
	int getNumberOfDiscTooHotEvents();
        
}
