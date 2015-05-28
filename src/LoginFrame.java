import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class LoginFrame extends Frame {
	private Button b1, b2;
	private TextField tf1, tf2;
	private Label l1, l2;
	private ButtonListener bl;
	private PrintWriter pw;

	public LoginFrame() {
		super("Login");
		Panel p = new Panel();
		p.setLayout(new GridLayout(2,2,10,10));
		l1 = new Label("Username:");
		l2 = new Label("Password:");
		tf1 = new TextField(10);
		tf2 = new TextField(10);
		tf2.setEchoChar('*');
		p.add(l1);
		p.add(tf1);
		p.add(l2);
		p.add(tf2);
		add(p,BorderLayout.NORTH);

		p = new Panel();
		b1 = new Button("Login");
		b2 = new Button("Cancel");
		bl = new ButtonListener();
		b1.addActionListener(bl);
		b2.addActionListener(bl);
		p.add(b1);
		p.add(b2);
		add(p,BorderLayout.SOUTH);
		setSize(400,150);
		setLocation(300,300);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				LoginFrame.this.dispose();
				Frame f = new PrincipalMenuFrame();
				f.pack();
				f.setSize(300,150);
				f.setLocation(500,300);
				f.setVisible(true);
			}
		});
	}
	public class ButtonListener implements ActionListener {
		private UsersList lci;
		public ButtonListener() {
			lci = UsersList.getInstance();
		}
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == b1) {
				String[] s;
				String g = new String();
				int i=0, index;
				boolean found = false;
				for(User u: lci.getTS()) {
					s = u.toFile().split("_");
					if(s[3].equals(tf1.getText()) && s[4].equals(tf2.getText())) {
						found = true;
						g = u.toFile();
					}
				}
				if(found) {
					try {
						pw = new PrintWriter(new FileWriter("user_temp.txt"));
						pw.println(g);
						pw.flush();
						pw.close();
					}
					catch(IOException ioe) {
						ioe.printStackTrace();
					}
				}
				else {
					final Dialog d = new Dialog(LoginFrame.this, "Error!");
					d.setLayout(new GridLayout(2,1,0,10));
					d.add(new Label("Incorect!"));
					Button ok = new Button("OK");
					ok.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent ae) {
							tf1.setText("");
							tf2.setText("");
							d.dispose();
							tf1.requestFocus();
						}
					});
					d.add(ok);
					d.setBounds(250,250,100,100);
					d.setVisible(true);
				}
				if(found) {
					LoginFrame.this.dispose();
					Frame f = new PrincipalMenuFrame();
					f.pack();
					f.setSize(300,150);
					f.setLocation(500,300);
					f.setVisible(true);
				}

			}

			if(e.getSource() == b2) {
				LoginFrame.this.dispose();
				Frame f = new PrincipalMenuFrame();
				f.pack();
				f.setSize(300,150);
				f.setLocation(500,300);
				f.setVisible(true);
			}
		}
	}
}
