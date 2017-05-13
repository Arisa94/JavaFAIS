import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ObjectAnonymizerInterface {
	/**
	 * Metoda pozwala zglosic liste obiektow do
	 * przetworzenia i zwraca wynik pracy.
	 * Wszystkie obiekty na liscie musza byc instancjami tej samej klasy.
	 * @param objects lista obiektow do anonimizacji
	 * @return lista obiektow po przeprowadzeniu anonimizacji
	 */
	List<Object> anonimize( List<Object> objects );
	
	/**
	 * Zwraca zbior uzytych w procesie anonimizacji ciagow znakow.
	 * @return zbior ciagow znakow, ktore uzyto do anonimizacji
	 */
	Set<String> getUsedStrings();
	
	/**
	 * Mapa przemapowan oryginalnych ciagow na nowe
	 * ciagi znakow. Klucz to oryginalny ciag, 
	 * wartosc to nowy ciag znakow, ktorym oryginalny
	 * zastapiono.
	 * @return mapa, w ktorej kluczem jest stary ciag,
	 * a wartoscia ciag znakow, ktorym go zamieniono  
	 */
	Map<String,String> getMapping();
}