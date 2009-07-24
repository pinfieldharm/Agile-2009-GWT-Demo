package bs.client.model;

import java.util.ArrayList;


import com.google.gwt.event.shared.HandlerManager;

public class Model {
	
	private ArrayList<String> titles = new ArrayList<String>();
	
	private HandlerManager eventBus;
	
	public Model(HandlerManager eventBus) {
		this.eventBus = eventBus;
	}

	public ArrayList<String> getTitles() {
		return titles;
	}
	
	public void addTitle(String title) {
		insertAtBeginning(title);
		fireChangeEvent();
	}

	public void removeTitle(int position) {
		if (isOutOfBounds(position)) return;
		
		removeTitleAt(position);
		fireChangeEvent();
	}

	public void moveTitleUp(int position) {
		if (isOutOfBounds(position) || isFirst(position)) return;

		moveTitleUpAt(position);
		fireChangeEvent();
	}

	private void insertAtBeginning(String title) {
		titles.add(0, title);
	}
	
	private void fireChangeEvent() {
		eventBus.fireEvent(new ModelChangeEvent());
	}
	
	private void removeTitleAt(int position) {
		titles.remove(position);
	}
	
	private void moveTitleUpAt(int position) {
		String title = titles.remove(position);
		titles.add(position - 1, title);
	}

	private boolean isFirst(int position) {
		return position == 0;
	}

	private boolean isOutOfBounds(int position) {
		return position < 0 || position >= titles.size();
	}
	

}
