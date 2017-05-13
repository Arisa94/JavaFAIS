
public class Simple extends ToAnonimizeSuperClass {	
	
	@ToAnonimize(readyToAnonimize=true)
	public String surname; // tylko zawartosc tego pola trzeba wymieniac
	
	@Override
	public void setString( String value ) {
		surname = value;
		super.setString( value );
	}
	
	@Override
	public String getNewString() {
		return surname;
	}
}