package mq.client;

import com.google.gwt.event.shared.GwtEvent;

public class RemoveEvent extends GwtEvent<RemoveEventHandler>{

	public static final Type<RemoveEventHandler> TYPE = new Type<RemoveEventHandler>();

	@Override
	protected void dispatch(RemoveEventHandler handler) {
		handler.onRemove(this);
	}

	@Override
	public Type<RemoveEventHandler> getAssociatedType() {
		return TYPE;
	}

}
