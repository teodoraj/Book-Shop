import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
@SuppressWarnings("serial")
public class PaymentFrame extends Frame {
	private BufferedReader br;
	private PrintWriter pw;
	private GregorianCalendar calendar;
	private Label l1,l2,l3,l4,l5,l6,l7,l8,l9;
	private Button b;
	private ButtonListener bl;
	private Checkbox c1,c2,c3,c4;
	private CheckboxGroup cb1,cb2;
	private Panel p,p1,p2,p5,p6;
	private CardLayout cl;
	private TextField tf1, tf2, tf3, tf4, tf5;
	private CheckboxListener lc;
	private java.util.List<Book> list;
	private double total;
	private double shippingFees;
	private FieldListener fl;
	private boolean test1 = false, test2 = false, t3=false,t4=false,t5=false,t6=false,t7=false;
    public PaymentFrame(java.util.List<Book> booksList, double total) {
    	super("Payment");
    	list = booksList;
    	this.total = total;
    	lc = new CheckboxListener();
    	p = new Panel();
    	p.setLayout(new GridLayout(4,4,10,10));
    	p.add(new Label("Shipping type:"));
    	l1 = new Label();
    	p.add(l1);
    	cb1 = new CheckboxGroup();
    	c1 = new Checkbox("Fast");
    	c2 = new Checkbox("Normal");
    	c1.setCheckboxGroup(cb1);
    	c2.setCheckboxGroup(cb1);
    	c1.addItemListener(lc);
    	c2.addItemListener(lc);
    	p.add(c1);
    	p.add(c2);


    	p.add(new Label("Payment type:"));
    	l2 = new Label();
    	p.add(l2);
    	cb2 = new CheckboxGroup();
    	c3 = new Checkbox("Credit Card");
    	c3.addItemListener(lc);
    	c4 = new Checkbox("Bank account");
    	c4.addItemListener(lc);
    	c3.setCheckboxGroup(cb2);
    	c4.setCheckboxGroup(cb2);
    	p.add(c3);
    	p.add(c4);

    	p5 = new Panel();
    	cl = new CardLayout();
    	p5.setLayout(cl);

		fl = new FieldListener();
    	p1 = new Panel();
    	l3 = new Label("Card number:");
    	tf1 = new TextField(16);
    	tf1.addFocusListener(fl);
    	l4 = new Label("Bank:");
    	tf2 = new TextField(15);
    	tf2.addFocusListener(fl);
    	l5 = new Label("Security code:");
    	tf3 = new TextField(3);
    	tf3.addFocusListener(fl);
    	l6 = new Label("Account number:");
    	tf4 = new TextField(24);
    	tf4.addFocusListener(fl);
    	l7 = new Label("Expire date:");
    	tf5 = new TextField(4);
    	tf5.addFocusListener(fl);
    	l8 = new Label();
    	l4.setEnabled(false);
		l6.setEnabled(false);
		tf2.setEnabled(false);
		tf4.setEnabled(false);
		l3.setEnabled(false);
		l5.setEnabled(false);
		l7.setEnabled(false);
		tf1.setEnabled(false);
		tf3.setEnabled(false);
		tf5.setEnabled(false);
		buildPanel1();

    	p6 = new Panel();
    	p6.add(p);
    	p6.add(p5);

    	add(p6);

		p2 = new Panel();
		l9 = new Label("Total : " + (total + shippingFees) + " $ ");
		b = new Button("Send");
		bl = new ButtonListener();
		b.addActionListener(bl);
		p2.add(l9);
		p2.add(b);
		add(p2,BorderLayout.SOUTH);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				PaymentFrame.this.dispose();
				Frame cf = new CatalogueFrame();
				cf.setSize(700,500);
				cf.setVisible(true);
			}
		});
    }
    public void buildPanel1() {
	   	p1.setLayout(new GridLayout(3,4,10,10));
	   	p1.add(l3);
	   	p1.add(tf1);
	   	p1.add(l4);
	   	p1.add(tf2);
	   	p1.add(l5);
	   	p1.add(tf3);
	   	p1.add(l6);
	   	p1.add(tf4);
	   	p1.add(l7);
	   	p1.add(tf5);
	   	p1.add(l8);
	   	p5.add(p1,BorderLayout.SOUTH);
    }

	public class ButtonListener implements ActionListener {
		public ButtonListener() {
		}
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == b && (test1 && test2) && ((t3 && t4 && t5) || (t6 && t7))) {
				b.setEnabled(false);
				String userLine = new String();
				calendar = new GregorianCalendar();
				String data = "" + calendar.get(Calendar.YEAR) + "." + (calendar.get(Calendar.MONTH)+1) + "." + calendar.get(Calendar.DATE) + "_" + calendar.get(Calendar.HOUR) + "." + calendar.get(Calendar.MINUTE) + "." + calendar.get(Calendar.SECOND);
				File user = new File("user_temp.txt");
				if(user.exists()) {
					try {
						br = new BufferedReader(new FileReader(user));
						userLine = br.readLine();
						br.close();
					}
					catch(IOException ioe) {
						ioe.printStackTrace();
					}
				}
				File f = new File(data + "_" + userLine.split("_")[0] + "_" + userLine.split("_")[1] + ".txt");

				try {
					pw = new PrintWriter(new FileWriter(f));
					pw.println("To: " + userLine.split("_")[0] + " " + userLine.split("_")[1]);
					pw.println("Address: " + userLine.split("_")[2]);
					pw.println("Order: ");
					for(int i=0; i<list.size(); i++) {
						pw.println(list.get(i).getBook().getTitle() + " - " + list.get(i).getBook().getPrice() + " $");
					}
					pw.println("Books : " + total + " $");
					pw.println("Shipping fees " + shippingFees + " $");
					pw.println("TOTAL: " + (total + shippingFees) + " $");

					pw.close();
				}
				catch(IOException ioef) {
					ioef.printStackTrace();
				}
				PaymentFrame.this.dispose();
				Frame cf = new CatalogueFrame();
				cf.setSize(700,500);
				cf.setVisible(true);
			}
		}


	}
	public class FieldListener implements FocusListener {
		public void focusLost(FocusEvent e) {
			if(e.getSource() == tf1) {
				String cardNumber= tf1.getText();
				if(cardNumber.length()!=16) {
					tf1.setText("");
					tf1.requestFocus();
					t3 = false;
				}
				if(cardNumber.length() == 16) {
					for(int i = 0; i<16; i++){
						if(cardNumber.charAt(i)<'0' || cardNumber.charAt(i)>'9') {
							tf1.setText("");
							tf1.requestFocus();
							t3 = false;
							break;
						}
						else {
							t3 = true;
						}
					}
				}
			}
			if(e.getSource() == tf2) {
				String bankName = tf2.getText();
				for(int i = 0; i<bankName.length(); i++) {
					if(!((bankName.charAt(i)>='A' && bankName.charAt(i)<='z') || bankName.charAt(i)==' ')){
						tf2.setText("");
						tf2.requestFocus();
						t6 = false;
						break;
					}
					else{
						t6 = true;
					}
				}
			}
			if(e.getSource() == tf3) {
				String securityCode = tf3.getText();
				if(securityCode.length()!=3){
					tf3.setText("");
					tf3.requestFocus();
					t4 = false;
				}
				if(securityCode.length()==3) {
					for(int i = 0; i<3; i++) {
						if(!(securityCode.charAt(i)>='0' && securityCode.charAt(i)<='9')) {
							tf3.setText("");
							tf3.requestFocus();
							t4 = false;
						}
						else {
							t4 = true;
						}
					}
				}
			}
			if(e.getSource() == tf4) {
				String IbanCode = tf4.getText();
				if(IbanCode.length()!=24) {
					tf4.setText("");
					tf4.requestFocus();
					t7 = false;
				}
				if(IbanCode.length()==24) {
					for(int i = 0; i<24; i++) {
						if(!(IbanCode.charAt(i)>='0' && IbanCode.charAt(i)<='9' || IbanCode.charAt(i)>='A' && IbanCode.charAt(i)<='Z')) {
							tf4.setText("");
							tf4.requestFocus();
							t7 = false;
						}
						else {
							t7 = true;
						}
					}
				}
			}
			if(e.getSource() == tf5) {
				String data = tf5.getText();
				if(data.length() != 5) {
					tf5.setText("");
					tf5.requestFocus();
					t5 = false;
				}
				if(data.length() == 5) {
					for(int i = 0; i<5; i++) {
						if(!((data.charAt(i)>='0' && data.charAt(i)<='9' && i!=2) || (i==2 && data.charAt(i)=='/'))) {
							tf5.setText("");
							tf5.requestFocus();
							t5 = false;
						}
						else {
							t5 = true;
						}
					}
				}
			}
		}
		public void focusGained(FocusEvent e) {}
	}

    public class CheckboxListener implements ItemListener {
    	public void itemStateChanged(ItemEvent e) {
    		if(e.getSource() == c1) {
    			shippingFees = 6;
    			l9.setText("Total: " + (total + shippingFees) + " $");
    			PaymentFrame.this.validate();
    			test1 = true;
    		}
    		if(e.getSource() == c2) {
    			shippingFees = 2;
    			l9.setText("Total : " + (total + shippingFees) + " $");
    			PaymentFrame.this.validate();
    			test1 = true;
    		}
    		if(e.getSource() == c3) {
    			l4.setEnabled(false);
    			l6.setEnabled(false);
    			tf2.setEnabled(false);
    			tf4.setEnabled(false);

    			l3.setEnabled(true);
    			l5.setEnabled(true);
    			l7.setEnabled(true);
    			tf1.setEnabled(true);
    			tf3.setEnabled(true);
    			tf5.setEnabled(true);
    			test2 = true;
    			t6 = t7 = false;
    		}
    		if(e.getSource() == c4) {
    			l4.setEnabled(true);
    			l6.setEnabled(true);
    			tf2.setEnabled(true);
    			tf4.setEnabled(true);

    			l3.setEnabled(false);
    			l5.setEnabled(false);
    			l7.setEnabled(false);
    			tf1.setEnabled(false);
    			tf3.setEnabled(false);
    			tf5.setEnabled(false);
    			test2 = true;
    			t3 = t4 = t5 = false;
    		}
    	}
    }

}