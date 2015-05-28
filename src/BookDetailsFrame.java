import java.awt.*;
import java.awt.event.*;
public class BookDetailsFrame extends Frame {
	private TextArea ta;
	private Panel p;
	public BookDetailsFrame(Book c) {
		ta = new TextArea(20,30);
		ta.setText(c.getBook().toString());
		add(ta);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				BookDetailsFrame.this.dispose();
			}
		});
	}
}
