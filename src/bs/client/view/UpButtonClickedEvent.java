package bs.client.view;


import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class UpButtonClickedEvent extends GwtEvent<UpButtonClickedEvent.Handler> {
	public static final Type<Handler> TYPE = new Type<Handler>();

	private BookPanel panel;
	
	public UpButtonClickedEvent(BookPanel panel) {
		this.panel = panel;
	}

	public BookPanel getPanel() {
		return panel;
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
		void onUpButtonClicked(UpButtonClickedEvent removeButtonClickedEvent);
	}

	
}
