
public class Person {
	private String lastName, firstName;

	public Person(String l, String f) {
		this.lastName = l;
		this.firstName = f;
	}

	public String getLastName() {
		return lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLF() {
		return lastName + " " + firstName;
	}
}
