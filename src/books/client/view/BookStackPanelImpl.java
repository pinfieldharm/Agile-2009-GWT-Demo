package books.client.view;

import com.google.gwt.user.client.ui.VerticalPanel;

public class BookStackPanelImpl extends VerticalPanel implements BookStackPanel {

	public BookPanelImpl addBookPanel(String title) {
		BookPanelImpl bookPanel = new BookPanelImpl(title);
		add(bookPanel);
		return bookPanel;
	}

}
