
public class ManyFieldsClass extends ToAnonimizeSuperClass {

	public int mazurek;
	protected String polonezCaro;

	@ToAnonimize(readyToAnonimize = false)
	public String polka; // tylko zawartosc tego pola trzeba wymieniac

	@ToAnonimize(readyToAnonimize = true)
	public String pole; // tylko zawartosc tego pola trzeba wymieniac

	@Override
	public void setString(String value) {
		pole = value;
		super.setString(value);
	}

	@Override
	public String getNewString() {
		return pole;
	}

}