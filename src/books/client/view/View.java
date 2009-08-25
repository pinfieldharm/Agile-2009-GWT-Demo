package books.client.view;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class View {

	private BookStackPanelImpl stackPanel;
	private InputPanelImpl inputPanel;

	public View() {

		final VerticalPanel mainPanel = new VerticalPanel();
		mainPanel.setStylePrimaryName("mainPanel");
		RootPanel.get().add(mainPanel);

		mainPanel.add(new HTML("<h1>Book Stack</h1>"));

		inputPanel = new InputPanelImpl();
		mainPanel.add(inputPanel);

		stackPanel = new BookStackPanelImpl();
		stackPanel.setStylePrimaryName("stackPanel");

		mainPanel.add(stackPanel);
	}

	public BookStackPanel getStackPanel() {
		return stackPanel;
	}
	
	public InputPanel getInputPanel() {
		return inputPanel;
	}
	
}
