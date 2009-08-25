package books.client.view;


import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class RemoveButtonClickedEvent extends GwtEvent<RemoveButtonClickedEvent.Handler> {
	public static final Type<Handler> TYPE = new Type<Handler>();

	private final int position;
	
	public RemoveButtonClickedEvent(int position) {
		this.position = position;
	}

	public int getPosition() {
		return position;
	}
	
	@Override
	protected void dispatch(Handler handler) {
		handler.onRemoveButtonClicked(this);
	}

	@Override
	public Type<Handler> getAssociatedType() {
		return TYPE;
	}

	public interface Handler extends EventHandler {
		void onRemoveButtonClicked(RemoveButtonClickedEvent removeButtonClickedEvent);
	}

	
}
