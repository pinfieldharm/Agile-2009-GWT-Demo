/**
 * 
 */
package books.client.view;

import com.google.gwt.user.client.ui.Button;
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
	
	/* (non-Javadoc)
	 * @see books.client.view.InputPanel#getAddButton()
	 */
	public Button getAddButton() {
		return addButton;
	}

	/* (non-Javadoc)
	 * @see books.client.view.InputPanel#getAddBox()
	 */
	public SuggestBox getAddBox() {
		return addBox;
	}
}