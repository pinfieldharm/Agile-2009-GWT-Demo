package books.client.view;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasText;

public interface InputPanel {

	public abstract HasClickHandlers getAddButton();

	public abstract HasText getAddBox();

}