import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;

public class Catalogue {
	private TreeSet<Book> ts;
	private BufferedReader br;
	private PrintWriter pw;
	private static Catalogue instance;
	private Book book;
	private String l;
	private Catalogue() {
		File f = new File("catalogue.txt");
		ts = new TreeSet<Book>();
		String[] s,sc,a,r;
		ArrayList<Person> auth = new ArrayList<Person>();
		ArrayList<Notice> notice = new ArrayList<Notice>();
		if(f.exists()) {
			try {
				br = new BufferedReader(new FileReader(f));
				while((l = br.readLine())!=null) {
					s = null;
					s = l.split("_");
					sc = s[1].split("`");
					a = sc[4].split("~");
					auth = new ArrayList<Person>();
					for(int i=0; i<a.length; i++) {
						auth.add(new Person(a[i].split(" ")[0],a[i].split(" ")[1]));

					}
					r = sc[5].split("~");
					notice = new ArrayList<Notice>();
					for(int i=0; i<r.length; i++) {
						notice.add(new Notice(r[i].split("#")[0],new Person(r[i].split("#")[1].split(" ")[0],r[i].split("#")[1].split(" ")[1])));
					}
					book = new Book(new BookSpecification(sc[0],Integer.parseInt(sc[1]),Integer.parseInt(sc[2]),Double.parseDouble(sc[3]),auth,notice),Integer.parseInt(s[0]));
					ts.add(book);
				}
			}
			catch(IOException ioe) {
				ioe.printStackTrace();
			}
		}
		else {
			System.out.println("File catalogue.txt doesn't exist!");
		}
	}
	public static Catalogue getInstance() {
		if(instance==null) {
			instance = new Catalogue();
		}
		return instance;
	}
	public TreeSet<Book> getTS() {
		return ts;
	}
}
