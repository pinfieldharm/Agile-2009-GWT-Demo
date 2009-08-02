/**
 * 
 */
package books.client.view;

import com.google.gwt.user.client.ui.SuggestBox;

public class TitleSuggestBox extends SuggestBox {
	TitleSuggestBox() {
		super(new TitleOracle());
	}
}