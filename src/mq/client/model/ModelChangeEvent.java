package mq.client.model;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class ModelChangeEvent extends GwtEvent<ModelChangeEvent.Handler>{

	public static final Type<Handler> TYPE = new Type<Handler>();
	
	@Override
	protected void dispatch(Handler handler) {
		handler.onModelChange(this);
	}

	@Override
	public Type<Handler> getAssociatedType() {
		return TYPE;
	}
	
	public interface Handler extends EventHandler {
		void onModelChange(ModelChangeEvent addEvent);
	}

	
}
