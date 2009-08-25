package books.client.view;


import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class UpButtonClickedEvent extends GwtEvent<UpButtonClickedEvent.Handler> {
	public static final Type<Handler> TYPE = new Type<Handler>();

	private int position;
	
	public UpButtonClickedEvent(int position) {
		this.position = position;
	}

	public int getPosition() {
		return position;
	}
	
	@Override
	protected void dispatch(Handler handler) {
		handler.onUpButtonClicked(this);
	}

	@Override
	public Type<Handler> getAssociatedType() {
		return TYPE;
	}

	public interface Handler extends EventHandler {
		void onUpButtonClicked(UpButtonClickedEvent upButtonClickedEvent);
	}

	
}
