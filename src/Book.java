
public class Book implements Comparable<Book>{
	private int quantity;
	private BookSpecification sc;
	public Book(BookSpecification s, int q) {
		this.quantity = q;
		this.sc = s;
	}
	public int getQuantity() {
		return quantity;
	}
	public BookSpecification getBook() {
		return sc;
	}
	public int compareTo(Book c) {
		return (sc.toString()).compareTo(c.getBook().toString());
	}
}
