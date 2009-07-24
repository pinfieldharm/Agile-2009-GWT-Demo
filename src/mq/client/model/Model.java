package mq.client.model;

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
		eventBus.fireEvent(new ModelChangeEvent());
	}
	
	public ArrayList<String> getTitles() {
		return titles;
	}

	public void removeTitle(int position) {
		titles.remove(position);
		eventBus.fireEvent(new ModelChangeEvent());
	}
	
	public void moveTitleUp(int position) {
		if (position == 0) return;
		String title = titles.remove(position);
		titles.add(position - 1, title);
		eventBus.fireEvent(new ModelChangeEvent());
	}

}
