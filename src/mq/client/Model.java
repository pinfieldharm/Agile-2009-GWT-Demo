package mq.client;

import java.util.ArrayList;

import com.google.gwt.event.shared.HandlerManager;

public class Model {
	
	private ArrayList<String> titles = new ArrayList<String>();
	
	private HandlerManager eventBus;
	
	public Model(HandlerManager eventBus) {
		this.eventBus = eventBus;
	}
	
	public void addTitle(String title) {
		titles.add(0, title);
		eventBus.fireEvent(new AddEvent());
	}
	
	public ArrayList<String> getTitles() {
		return titles;
	}

	public void removeTitle(String text) {
		titles.remove(text);
		eventBus.fireEvent(new RemoveEvent());
	}

}
