package mq.client.view;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class View {

	private Button addButton;
	private SuggestBox addBox;
	private VerticalPanel stackPanel;

	public View() {

		final VerticalPanel mainPanel = new VerticalPanel();
		mainPanel.setStylePrimaryName("mainPanel");
		RootPanel.get().add(mainPanel);

		mainPanel.add(new HTML("<h1>Book Stack</h1>"));

		final HorizontalPanel inputPanel = new HorizontalPanel();
		inputPanel.setStylePrimaryName("inputPanel");
		mainPanel.add(inputPanel);

		MultiWordSuggestOracle oracle = new MultiWordSuggestOracle();
		oracle.add("Clean Code");
		oracle.add("Infinite Jest");
		oracle.add("The Art of Agile Development");
		oracle.add("Canary");

		addBox = new SuggestBox(oracle);
		inputPanel.add(addBox);

		addButton = new Button("Add");
		inputPanel.add(addButton);

		stackPanel = new VerticalPanel();
		stackPanel.setStylePrimaryName("stackPanel");
		mainPanel.add(stackPanel);
	}

	public Button getAddButton() {
		return addButton;
	}

	public SuggestBox getAddBox() {
		return addBox;
	}

	public VerticalPanel getStackPanel() {
		return stackPanel;
	}
	
}
