import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class PrincipalMenuFrame extends Frame {
	private Panel p;
	private Label l1;
	private Button b1,b2,b3,b4;
	private ButtonListener bl;
	private ABE abe;

	public PrincipalMenuFrame() {
		super("Book-Shop");
		l1 = new Label("Choose option:");
		add(l1,BorderLayout.NORTH);
		p = new Panel();
		p.setLayout(new GridLayout(2,2,10,10));
		b1 = new Button("Login");
		b4 = new Button("Logout");
		b2 = new Button("New User");
		b3 = new Button("Catalogue");
		bl = new ButtonListener();
		b1.addActionListener(bl);
		b1.addActionListener(abe);
		b4.addActionListener(bl);
		b2.addActionListener(bl);
		b3.addActionListener(bl);
		File file = new File("user_temp.txt");
		if(file.exists()) {
			b1.setEnabled(false);
			b2.setEnabled(false);
			b3.setEnabled(true);
			b4.setEnabled(true);
		}
		else {
			b1.setEnabled(true);
			b2.setEnabled(true);
			b3.setEnabled(false);
			b4.setEnabled(false);
		}

		p.add(b1);
		p.add(b4);
		p.add(b2);
		p.add(b3);
		add(p,BorderLayout.CENTER);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				PrincipalMenuFrame.this.dispose();
				File file = new File("user_temp.txt");
				if(file.exists()) {
					file.delete();
				}
			}
		});
	}

	public void showFrame(PrincipalMenuFrame f) {
		f.pack();
		f.setSize(300,150);
		f.setLocation(500,300);
		f.setVisible(true);
	}

	private class ABE implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == b1)	{
				b1.setEnabled(false);
				validate();
			}
		}
	}

	private class ButtonListener implements ActionListener {
		private Frame f;
		public ButtonListener() {
		}
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == b1) {
				PrincipalMenuFrame.this.dispose();
				f = new LoginFrame();
				f.pack();
				f.setVisible(true);
			}

			if(e.getSource() == b4) {
				File file = new File("user_temp.txt");
				if(file.exists()) {
					file.delete();
				}
				PrincipalMenuFrame.this.dispose();
				Frame f = new PrincipalMenuFrame();
				f.pack();
				f.setSize(300,150);
				f.setLocation(500,300);
				f.setVisible(true);
			}

			if(e.getSource() == b2) {
				PrincipalMenuFrame.this.dispose();
				f = new NewUserFrame();
				f.pack();
				f.setVisible(true);
			}

			if(e.getSource() == b3) {
				PrincipalMenuFrame.this.dispose();
				f = new CatalogueFrame();
				f.setSize(700,500);
				f.setVisible(true);
			}


		}
	}
}
