package mq.client;

import com.google.gwt.event.shared.GwtEvent;

public class RemoveButtonClickedEvent extends GwtEvent<RemoveButtonClickedEventHandler> {
	public static final Type<RemoveButtonClickedEventHandler> TYPE = new Type<RemoveButtonClickedEventHandler>();

	private BookPanel panel;
	
	public RemoveButtonClickedEvent(BookPanel bookPanel) {
		panel = bookPanel;
	}


	public BookPanel getPanel() {
		return panel;
	}
	
	
	@Override
	protected void dispatch(RemoveButtonClickedEventHandler handler) {
		handler.onRemoveButtonClicked(this);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<RemoveButtonClickedEventHandler> getAssociatedType() {
		return TYPE;
	}

}
