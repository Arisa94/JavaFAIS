import java.util.List;
import java.util.Map;
import java.util.Set;

public interface AnonymizerInterface{
    /**
	 * Metoda pozwala zglosic liste obiektow zgodnych z 
	 * interfejsem <tt>PhoneInterface</tt> do
	 * przetworzenia i zwraca wynik pracy.
	 * @param phones lista obiektow do anonimizacji
	 * @return lista obiektow po przeprowadzeniu anonimizacji
	 */
	List<PhoneInterface> anonimize( List<PhoneInterface> phones );
	
	/**
	 * Zwraca zbior uzytych w procesie anonimizacji ciagow znakow.
	 * @return zbior ciagow znakow, ktore uzyto do anonimizacji
	 */
	Set<String> getUsedStrings();
	
	/**
	 * Mapa przemapowan oryginalnych numerow telefonow na
	 * ciagi znakow. Klucz to oryginalny numer telefonu, 
	 * wartosc to ciag znakow, ktorym numer telefonu
	 * zastapiono.
	 * @return mapa, w ktorej kluczem jest numer telefonu,
	 * a wartoscia ciag znakow uzyty w procesie anonimizacji 
	 */
	Map<String,String> getMapping();
}
