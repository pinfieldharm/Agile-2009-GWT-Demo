/**
 * 
 */
package mq.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public final class MoviePanel extends FlowPanel {
	private final VerticalPanel queuePanel;

	public MoviePanel(String title, VerticalPanel queuePanel) {
		this.queuePanel = queuePanel;
		this.setStylePrimaryName("moviepanel");
		this.add(new Label(title));
		addRemoveButton();
		addTopButton();
	}

	private void addTopButton() {
		
		Image topButton = new Image("icons/arrow_up.png");
		this.add(topButton);
		topButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				MoviePanel.this.removeFromParent();
				queuePanel.insert(MoviePanel.this, 0);
			}
		});
	}

	private void addRemoveButton() {
		
		Image removeButton = new Image("icons/cross.png");
		this.add(removeButton);
		removeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				queuePanel.remove(MoviePanel.this);
			}
		});
	}
}