/**
 * 
 */
package books.client.view;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.SuggestBox;

public class InputPanel extends HorizontalPanel {

	private Button addButton;
	private SuggestBox addBox;
	
	public InputPanel() {
		this.setStylePrimaryName("inputPanel");
		addBox = new TitleSuggestBox();
		this.add(addBox);

		addButton = new Button("Add");
		this.add(addButton);
		this.setCellWidth(addButton, "50px");
		this.setCellVerticalAlignment(addButton, ALIGN_MIDDLE);
		this.setCellHorizontalAlignment(addButton, ALIGN_RIGHT);
	}
	
	public Button getAddButton() {
		return addButton;
	}

	public SuggestBox getAddBox() {
		return addBox;
	}
}