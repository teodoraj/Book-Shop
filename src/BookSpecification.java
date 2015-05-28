import java.util.*;

public class BookSpecification {
	private String title;
	private int year, pages;
	private double price;
	private ArrayList<Person> authors;
	private ArrayList<Notice> notices;

	public BookSpecification(String t, int y, int p, double pr, ArrayList<Person> a, ArrayList<Notice> n) {
		this.title = t;
		this.year = y;
		this.pages = p;
		this.price = pr;
		this.authors = a;
		this.notices = n;
	}

	public String getTitle() {
		return title;
	}

	public int getYear() {
		return year;
	}

	public int getPages() {
		return pages;
	}

	public ArrayList<Person> getAuthors() {
		return authors;
	}

	public ArrayList<Notice> getNotices() {
		return notices;
	}

	public double getPrice() {
		return price;
	}

	public String toString() {
		String s;
		s = "Title: " + title + "\nYear:" + year + "\nPages number:" + pages + "\nAuthors:";
		for(int i = 0; i<authors.size(); i++) {
			s = s + authors.get(i).getLF() + ";";
		}
		s = s + "\nNotices:\n";
		for(int i = 0; i<notices.size(); i++) {
			s = s + notices.get(i).getAuthor().getLF() + ":\n" + notices.get(i).getText() + "\n";
		}
		return s;
	}
}
