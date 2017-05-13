
public class ContactsFactory {
	static Contact get( int id, String phoneNumber ) {
		return new Contact(id, phoneNumber);
	}
	
	static private int counter = 0;
	
	static Contact get( String phoneNumber ) {
		return get( counter++, phoneNumber );
	}
}