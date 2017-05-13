
public interface HDDIOInterface {
    
    // liczba prob odczytu w celu potwierdzenia poprawnosci zapisu
	public static final int NUMBER_OF_ATTEMPTS = 3;

	/**
	 * Metoda przekazuje dysk do testu.
	 * 
	 * @param hddInterface
	 *            referencja do obiektu-dysku
	 */
	void setHDD(HDDInterface hddInterface);

	/**
	 * Zapisuje przekazane dane do sektora o wskazanym numerze i sprawdza czy
	 * zapis zostal przeprowadzony poprawnie.
	 * 
	 * @param sector
	 *            numer sektora pod ktory nalezy zapisac dane
	 * @param value
	 *            dane do zapisu
	 * @throws BadSectorException
	 *             informacja o blednym sektorze na dysku
	 * @throws DataCorruptionException
	 *             blad oznaczajacy uszkodzenie danych
	 * @throws DiskTooHotException
	 *             informacja o zbyt wysokiej temperaturze dysku
	 */
	public void writeAndTest(int sector, short value)
			throws BadSectorException, DataCorruptionException, DiskTooHotException;
        
}
