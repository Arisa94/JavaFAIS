public interface HDDInterface {
    
    /**
	 * Metoda zwraca liczbe sektorow dysku
	 * 
	 * @return liczba sektorow dysku
	 */
	public int getNumberOfSectors();

	/**
	 * Metoda pozwala zlecic zapis wartosci <tt>value</tt> do sektora o numerze
	 * <tt>sector</tt>
	 * 
	 * @param sector
	 *            sektor, do ktorego zapisujemy dane. Numer sektora liczony jest
	 *            od zera do liczby sektorow minus jeden.
	 * @param value
	 *            wartosc od 0 do 255 wlacznie
	 * @throws BadSectorException
	 *             informacja o blednym sektorze
	 * @throws DiskTooHotException
	 *             informacja o zbyt wysokiej temperaturze dysku
	 */
	public void write(int sector, short value) throws BadSectorException, DiskTooHotException;

	/**
	 * Metoda pozwala zlecic odczyt warosci z sektora o numerze <tt>sector</tt>
	 * 
	 * @param sector
	 *            sektor, ktory ma zostac odczytany. Numer sektora liczony jest
	 *            od zera do liczby sektorow minus jeden.
	 * @return zawartosc sektora
	 * @throws BadSectorException
	 *             informacja o blednym sektorze
	 * @throws DiskTooHotException
	 *             informacja o zbyt wysokiej temperaturze dysku
	 */
	public short read(int sector) throws BadSectorException, DiskTooHotException;
        
}
