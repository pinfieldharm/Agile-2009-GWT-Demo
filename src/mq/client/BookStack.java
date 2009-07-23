package mq.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class BookStack implements EntryPoint {

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {

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

		final SuggestBox addBox = new SuggestBox(oracle);
		inputPanel.add(addBox);

		final Button addButton = new Button("Add");
		inputPanel.add(addButton);

		final VerticalPanel queuePanel = new VerticalPanel();
		queuePanel.setStylePrimaryName("queuePanel");
		mainPanel.add(queuePanel);

		addButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				String title = addBox.getText();
				if (title.length() > 0) {
					queuePanel.insert(new BookPanel(title, queuePanel), 0);
					addBox.setText("");
				}
			}
		});

		queuePanel.add(new BookPanel("Agile Principles, Patterns, and Practices in C#", queuePanel));

	}
}
