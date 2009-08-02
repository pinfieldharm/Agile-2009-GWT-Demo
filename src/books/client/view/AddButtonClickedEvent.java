package books.client.view;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class AddButtonClickedEvent extends GwtEvent<AddButtonClickedEvent.Handler>{
	public static final Type<Handler> TYPE = new Type<Handler>();

	@Override
	protected void dispatch(Handler handler) {
		handler.onAddButtonClicked(this);
	}

	@Override
	public Type<Handler> getAssociatedType() {
		return TYPE;
	}
	
	public interface Handler extends EventHandler {
		void onAddButtonClicked(AddButtonClickedEvent addButtonClickedEvent);
	}

}


