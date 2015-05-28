import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class CatalogueFrame extends Frame {
	private Catalogue ci;
	private TreeSet<Book> ts;
	private Book b;
	private java.util.List<Book> l1 = new ArrayList<Book>();
	private java.util.List<Book> l2 = new ArrayList<Book>();
	private TextArea ta;
	private Panel p1;
	private Button b1,b2,b3;
	private ButtonListener bl;
	private java.awt.List l = new java.awt.List(6,false);
	private double total;
	
	public CatalogueFrame() {
		super("Catalogue");
		ci = Catalogue.getInstance();
		p1 = new Panel();
		ts = ci.getTS();
		total = 0;
		for(Book b: ts) {
			l.add(b.getBook().getTitle());
			l1.add(b);
		}
		l.setSize(500,400);
		p1.add(l);
		add(p1,BorderLayout.NORTH);
		p1 = new Panel();
		ta = new TextArea(20,80);
		p1.add(ta);
		add(p1,BorderLayout.CENTER);

		p1 = new Panel();
		b1 = new Button("Add");
		b2 = new Button("Details");
		b3 = new Button("Total: $ 0");
		bl = new ButtonListener();
		b1.addActionListener(bl);
		b2.addActionListener(bl);
		b3.addActionListener(bl);
		p1.add(b1);
		p1.add(b2);
		p1.add(b3);
		add(p1,BorderLayout.SOUTH);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				CatalogueFrame.this.dispose();
				Frame f = new PrincipalMenuFrame();
				f.pack();
				f.setSize(300,150);
				f.setLocation(500,300);
				f.setVisible(true);
			}
		});
	}
	private class ButtonListener implements ActionListener {
		private Frame f;
		public ButtonListener() {
		}
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == b1) {
				int i = l.getSelectedIndex();
				l2.add(l1.get(i));
				ta.setText(ta.getText() + l2.get(l2.size()-1).getBook().getTitle() + " - " + l2.get(l2.size()-1).getBook().getPrice() + " $" + "\n");
				total += l2.get(l2.size()-1).getBook().getPrice();
				b3.setLabel("Total: " + total + " $");
				CatalogueFrame.this.validate();
			}

			if(e.getSource() == b2) {
				int i = l.getSelectedIndex();
				f = new BookDetailsFrame(l1.get(i));
				f.setSize(1280,400);
				f.setVisible(true);
			}
			if(e.getSource() == b3) {
				CatalogueFrame.this.dispose();
				f = new PaymentFrame(l2,total);
				f.pack();
				f.setVisible(true);
			}
		}
	}
}
