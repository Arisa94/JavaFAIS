import java.util.*;

class Tester {
	Anonymizer Gall;
	List<PhoneInterface> phones, hashes;
	Set<String> strings;
	Map<String, String> map;

	public Tester() {
		Gall = new Anonymizer();
		phones = new ArrayList<PhoneInterface>();
		phones.add(new Contact(0,"12 123456"));
		phones.add(new Contact(1,"12 214364"));
		phones.add(new Contact(2,"12 123456"));
		phones.add(new Contact(3,"12 654321"));
		phones.add(new Contact(4,"12 654399"));
		phones.add(new Contact(5,"12 123456"));
		phones.add(new Contact(6,"12 999999"));
		phones.add(new Contact(7,"12 654321"));
		phones.add(new Contact(8,"21 654321")); }

	public void run() {
		hashes = Gall.anonimize(phones);
		strings = Gall.getUsedStrings();
		map = Gall.getMapping(); }

	public void input_out() {
		System.out.println("original phones:");
		System.out.println("(repeating numbers: {{0,2,5},{3,7}})");
		for(PhoneInterface it: phones) {
			System.out.println( ((Contact)it).getId() + "\t" + it.getPhoneNumber()); }
		System.out.println(); }

	public void output_out() {
		System.out.println("after anonimization:");
		for(PhoneInterface it: hashes) {
			System.out.print( ((Contact)it).getId() + "\t" + it.getPhoneNumber()+ "\t" );
			System.out.println("backup:( " + ((Contact)it).getBackupOfPhoneNumber() + " )"); }
		System.out.println();

		System.out.println("returned map:");
		for(Map.Entry<String,String> it: map.entrySet()) {
			System.out.println(it.getKey() + "  ->  " + it.getValue()); }
		System.out.println();

		System.out.println("returned strings:");
		for(String it: strings) { System.out.println(it); } }

	public int check() {
		String it1;int
		errs = 0;
		int total_errs = 0;
		if(map.size() != strings.size()) {
			total_errs += (map.size()-strings.size());
			System.out.println("ERR: " + total_errs + " phone number(s) doesn't have unique hashes!"); }
		else { System.out.println("map is OK"); }

		errs = 0;
		for(PhoneInterface it: hashes) {
			if( map.get( ((Contact)it).getBackupOfPhoneNumber() ) != it.getPhoneNumber() ) { errs++; } }
		if(errs>0) { System.out.println("ERR: " + errs + " wrong matched strings!"); }
		else { System.out.println("anonimization is corresponding with retunred map (OK)"); }
		total_errs+=errs;

		return total_errs; }


}