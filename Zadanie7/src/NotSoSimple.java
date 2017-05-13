
public class NotSoSimple extends ToAnonimizeSuperClass {

	public int surname; 	
	
	@ToAnonimize(readyToAnonimize=true)
	public String name; // tylko zawartosc tego pola trzeba wymieniac
	
	@Override
	public void setString( String value ) {
		name = value;
		super.setString( value );
	}
	
	@Override
	public String getNewString() {
		return name;
	}
	
}