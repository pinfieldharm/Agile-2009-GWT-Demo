/**
 * 
 */
package mq.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public final class MoviePanel extends HorizontalPanel {
	private final VerticalPanel queuePanel;

	public MoviePanel(String title, VerticalPanel queuePanel) {
		this.queuePanel = queuePanel;
		this.add(new HTML(title));
		addRemoveButton();
		addTopButton();
	}

	private void addTopButton() {
		Button topButton = new Button("Move to top");
		this.add(topButton);
		topButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				MoviePanel.this.removeFromParent();
				queuePanel.insert(MoviePanel.this, 0);
			}
		});
	}

	private void addRemoveButton() {
		Button removeButton = new Button("Remove");
		this.add(removeButton);
		removeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				queuePanel.remove(MoviePanel.this);
			}
		});
		queuePanel.add(this);
	}
}