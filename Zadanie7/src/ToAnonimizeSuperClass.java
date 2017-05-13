import java.util.Arrays;
import java.util.Map;
import java.util.Set;

import org.hamcrest.Matchers;

import static org.junit.Assert.*;

abstract public class ToAnonimizeSuperClass implements PMO_AutoTestable {

	private String oldValue;

	@Override
	public void setString(String value) {
		oldValue = value;
	}

	@Override
	public String getOldString() {
		return oldValue;
	}

	@Override
	public void autotest(Set<String> set, Map<String, String> map) {
		//System.out.println( "Zbior " + Arrays.toString( set.toArray( new String[ set.size()]) ));
		assertThat("Nowy ciag powinien zostac znaleziony w zbiorze zwracanym przez getUsedStrings", set,
				Matchers.hasItem(getNewString()));
		assertThat("Stary ciag powinien zostac znaleziony w zbiorze kluczy zwracanym przez getMapping", map,
				Matchers.hasKey(  getOldString()));
		assertThat("Stary -> Nowy ciag powinien zostac znaleziony w mapie zwracanej przez getMapping", map,
				Matchers.hasEntry( getOldString(), getNewString() ));
	}

}