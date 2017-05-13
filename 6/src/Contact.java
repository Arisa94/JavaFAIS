public class Contact implements PhoneInterface{
    private final int id;
    private String phoneNumber;
    private final String phoneNumberBackup;
	
    public Contact( int id, String phoneNumber ) {
	this.id = id;
	this.phoneNumber = phoneNumber;
	phoneNumberBackup = phoneNumber; // zapamietujemy oryginalny numer
    }
	
    public int getId() {
	return id;
    }
	
    @Override
    public String getPhoneNumber() {
    	return phoneNumber;
    }
	
    @Override
    public void setPhoneNumber(String phoneNumber) {
	this.phoneNumber = phoneNumber;
    }
	
    public String getBackupOfPhoneNumber() {
	return phoneNumberBackup;
    }
	
    @Override
    public String toString() {
	return "Contact> id: " + id + " number: " + phoneNumber; 
    }
}
