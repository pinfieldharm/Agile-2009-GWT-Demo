package mq.client;

import com.google.gwt.event.shared.GwtEvent;

public class AddButtonClickedEvent extends GwtEvent<AddButtonClickedEventHandler>{
	public static final Type<AddButtonClickedEventHandler> TYPE = new Type<AddButtonClickedEventHandler>();

	@Override
	protected void dispatch(AddButtonClickedEventHandler handler) {
		handler.onAddButtonClicked(this);
	}

	@Override
	public Type<AddButtonClickedEventHandler> getAssociatedType() {
		return TYPE;
	}

}


