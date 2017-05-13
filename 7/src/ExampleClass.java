
public class ExampleClass{
	
	private int id;
	public String value1;
	
	protected String value2;
	
	@ToAnonimize(readyToAnonimize=true)
	public String name = "kupa";

	@ToAnonimize(readyToAnonimize=false)
	public Integer nameI;
	
	@ToAnonimize(readyToAnonimize=true)
	public String surname = "kupa2"; // tylko zawartosc tego pola trzeba wymieniac
        
}