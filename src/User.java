
public class User implements Comparable<User> {
	private Person person;
	public Account c;
	private String address;

	public User(String l, String f, String a) {
		person = new Person(l,f);
		this.address = a;
		c = new Account(genU(l, f), genP(l,f,a));
	}
	public User(String l, String f, String a, String u, String pw) {
		person = new Person(f,l);
		this.address = a;
		c = new Account(u,pw);
	}
	public String getLastName() {
		return person.getLastName();
	}
	public String getFirstName() {
		return person.getFirstName();
	}
	public String getAddress() {
		return address;
	}
	public String genU(String l, String f) {
		return l.substring(0,1) + f;
	}
	public String genP(String l, String f, String a) {
		return f.substring(0,2) + a.substring(a.length()-2) + l.substring(l.length()-2);
	}
	public String getU() {
		return c.getUsername();
	}
	public String getP() {
		return c.getPassword();
	}
	public String toString() {
		return getLastName() + " " + getFirstName() + " " + getAddress();
	}
	public String toFile() {
		return getLastName() + "_" + getFirstName() + "_" + getAddress() + "_" + getU() + "_" + getP();
	}
	public int compareTo(User c) {
		return (getLastName() + " " + getFirstName() + " " + getAddress()).compareTo(c.toString());
	}
}
