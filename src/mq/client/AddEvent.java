package mq.client;

import com.google.gwt.event.shared.GwtEvent;

public class AddEvent extends GwtEvent<AddEventHandler>{

	public static final Type<AddEventHandler> TYPE = new Type<AddEventHandler>();
	
	@Override
	protected void dispatch(AddEventHandler handler) {
		handler.onAdd(this);
	}

	@Override
	public Type<AddEventHandler> getAssociatedType() {
		return TYPE;
	}
	
}
