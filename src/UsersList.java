import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
public class UsersList {
	private TreeSet<User> ts;
	private BufferedReader br;
	private PrintWriter pw;
	private String l;
	private User user;
	private static UsersList instance;
	private UsersList() {
		File f = new File("users.txt");
		ts = new TreeSet<User>();
		String[] s;
		if(f.exists()) {
			try {
				br = new BufferedReader(new FileReader(f));
				while((l = br.readLine())!=null) {
					s = l.split("_");
					user = new User(s[0], s[1], s[2]);
					ts.add(user);
				}
			}
			catch(IOException ioe) {
				ioe.printStackTrace();
			}
		}
		else {
			System.out.println("The file doesn't exist!");
		}
	}

	public String getUser() {
		String res = "";
		Iterator<User> it = ts.iterator();
		while(it.hasNext()) {
			res += it.next() + "\n";
		}
		return res;
	}

	public boolean addUser(String l, String f, String a) {
		user = new User(l, f, a);
		if(ts.contains(user)) {
			System.out.println("The User is already registered!");
			return false;
		}
		ts.add(user);
		System.out.println("The User has been added!");
		return true;
	}

	public TreeSet<User> getTS() {
		return ts;
	}

	public String searchUser(String name) {
		for(User c: ts) {
			if(name.equals(c.getLastName())) {
				return c.toString();
			}
		}
		return "No existent user is found with this name : " + name;
	}

	public void removeUser(String name) {
		User u = null;
		Iterator<User> it = ts.iterator();
		while(it.hasNext()) {
			if(name.equalsIgnoreCase(it.next().getLastName())) {
				it.remove();
				System.out.println("User has been removed");
				return;
			}
		}
		System.out.println(" User doesn't exist!");
	}

	public void save() {
		try {
			pw = new PrintWriter(new FileWriter("users.txt"));
			for(User u: ts) {
				pw.println(u.toFile());
			}
			pw.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

	public static UsersList getInstance() {
		if(instance==null) {
			instance = new UsersList();
		}
		return instance;
	}
}
