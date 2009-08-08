package books.client.view;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class View {

	private VerticalPanel stackPanel;
	private InputPanel inputPanel;

	public View() {

		final VerticalPanel mainPanel = new VerticalPanel();
		mainPanel.setStylePrimaryName("mainPanel");
		RootPanel.get().add(mainPanel);

		mainPanel.add(new HTML("<h1>Book Stack</h1>"));

		inputPanel = new InputPanel();
		mainPanel.add(inputPanel);

		stackPanel = new VerticalPanel();
		stackPanel.setStylePrimaryName("stackPanel");

		mainPanel.add(stackPanel);
	}

	public VerticalPanel getStackPanel() {
		return stackPanel;
	}
	
	public InputPanel getInputPanel() {
		return inputPanel;
	}
	
}
