
public class Notice {
	private String text;
	private Person author;
	public Notice(String t, Person a) {
		this.text = t;
		this.author = a;
	}
	public String getText() {
		return text;
	}
	public Person getAuthor() {
		return author;
	}
}
