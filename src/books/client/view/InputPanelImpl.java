/**
 * 
 */
package books.client.view;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.SuggestBox;

public class InputPanelImpl extends HorizontalPanel implements InputPanel {

	private Button addButton;
	private SuggestBox addBox;
	
	public InputPanelImpl() {
		this.setStylePrimaryName("inputPanel");
		addBox = new TitleSuggestBox();
		this.add(addBox);

		addButton = new Button("Add");
		this.add(addButton);
		this.setCellWidth(addButton, "50px");
		this.setCellVerticalAlignment(addButton, ALIGN_MIDDLE);
		this.setCellHorizontalAlignment(addButton, ALIGN_RIGHT);
	}
	
	public HasClickHandlers getAddButton() {
		return addButton;
	}

	public HasText getAddBox() {
		return addBox;
	}
}