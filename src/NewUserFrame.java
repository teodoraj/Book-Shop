import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;
public class NewUserFrame extends Frame {
	private Label firstName, lastName, address;
	private TextField firstN, lastN, faddress;
	private Panel p1, p2;
	private Button b1, b2;
	private FieldListener fl;
	private ButtonListener bl;
	public NewUserFrame() {
		setTitle("Create new user");
		p1 = new Panel();
		p1.setLayout(new GridLayout(3,2,10,10));
		firstName = new Label("First name:");
		lastName = new Label("Last name:");
		address = new Label("Address:");

		firstN = new TextField(10);
		lastN = new TextField(10);
		faddress = new TextField(10);

		fl = new FieldListener();

		firstN.addFocusListener(fl);
		lastN.addFocusListener(fl);
		faddress.addFocusListener(fl);

		p1.add(firstName);
		p1.add(firstN);
		p1.add(lastName);
		p1.add(lastN);
		p1.add(address);
		p1.add(faddress);
		add(p1,BorderLayout.NORTH);

		p2 = new Panel();

		b1 = new Button("Create");
		b2 = new Button("Cancel");
		bl = new ButtonListener();
		b1.addActionListener(bl);
		b2.addActionListener(bl);
		p2.add(b1);
		p2.add(b2);
		add(p2,BorderLayout.SOUTH);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				NewUserFrame.this.dispose();
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
				boolean existent = false;
				if(!lci.addUser(lastN.getText(), firstN.getText(), faddress.getText())) {
					final Dialog d = new Dialog(NewUserFrame.this,"Error!");
					d.setLayout(new GridLayout(2,1,0,10));
					d.add(new Label("Existent User"));
					Button ok = new Button("OK");
					ok.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent ae) {
							d.dispose();
							lastN.setText(null);
							firstN.setText(null);
							faddress.setText(null);
						}
					});
					d.add(ok);
					d.setBounds(250,250,100,100);
					d.setVisible(true);
					existent = true;
				}
				if(!existent) {
					lci.save();
					NewUserFrame.this.dispose();
					Frame f = new PrincipalMenuFrame();
					f.pack();
					f.setSize(300,150);
					f.setLocation(500,300);
					f.setVisible(true);
				}
			}

			if(e.getSource() == b2) {
				NewUserFrame.this.dispose();
				Frame f = new PrincipalMenuFrame();
				f.pack();
				f.setSize(300,150);
				f.setLocation(500,300);
				f.setVisible(true);
			}
		}
	}

	public class FieldListener implements FocusListener {
		private Dialog d;
		public void focusLost(FocusEvent e) {
			if(e.getSource() == lastN) {
				String last = lastN.getText();
				boolean ok = true;
				for(int i = 0; i<last.length(); i++) {
					if(!((last.charAt(i)>='a' && last.charAt(i)<='z')||(last.charAt(i)>='A' && last.charAt(i)<='Z')))
						ok = false;
				}
				if(ok == false) {
					d = new Dialog(NewUserFrame.this,"Error!");
					d.setLayout(new GridLayout(2,1,0,10));
					d.add(new Label("Last name contains only letters!"));
					Button okb = new Button("Ok");
					okb.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e) {
							lastN.setText("");
							validate();
							d.dispose();
							lastN.requestFocus();
						}
					});
					d.add(okb);
					d.setBounds(250,250,350,100);
					d.setVisible(true);
				}
			}
			if(e.getSource() == firstN) {
				String first = firstN.getText();
				boolean ok = true;
				for(int i = 0; i<first.length(); i++) {
					if(!((first.charAt(i)>='a' && first.charAt(i)<='z')||(first.charAt(i)>='A' && first.charAt(i)<='Z')||(first.charAt(i)==' ')||(first.charAt(i)=='-')))
						ok = false;
				}
				if(ok == false) {
					d = new Dialog(NewUserFrame.this,"Error!");
					d.setLayout(new GridLayout(2,1,0,10));
					d.add(new Label("Fisrt name contains letters ' ' and '-'!"));
					Button okb = new Button("Ok");
					okb.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e) {
							firstN.setText("");
							validate();
							d.dispose();
							firstN.requestFocus();
						}
					});
					d.add(okb);
					d.setBounds(250,250,350,100);
					d.setVisible(true);
				}
			}

			if(e.getSource() == faddress) {
				String address = faddress.getText();
				boolean ok = true;
				for(int i = 0; i<address.length(); i++) {
					if(!((address.charAt(i)>='a' && address.charAt(i)<='z')||(address.charAt(i)>='A' && address.charAt(i)<='Z')||(address.charAt(i)=='-')||(address.charAt(i)==',')||(address.charAt(i)=='.')||(address.charAt(i)==' ')||(address.charAt(i)>='0' && address.charAt(i)<='9'))){
						ok = false;
					}
				}
				if(ok == false) {
					d = new Dialog(NewUserFrame.this,"Error!");
					d.setLayout(new GridLayout(2,1,0,10));
					d.add(new Label("Addres contains letters, numbers, '-', ',' and '.'!"));
					Button okb = new Button("Ok");
					okb.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e) {
							faddress.setText("");
							validate();
							d.dispose();
							faddress.requestFocus();
						}
					});
					d.add(okb);
					d.setBounds(250,250,350,100);
					d.setVisible(true);
				}
			}
		}
		public void focusGained(FocusEvent e) {}
	}

}
