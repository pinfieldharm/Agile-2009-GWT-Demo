package mq.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.shared.HandlerManager;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class BookStack implements EntryPoint {

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		
		HandlerManager eventBus = new HandlerManager(null);
		View view = new View(eventBus);
		Model model = new Model(eventBus);
		new BookStackPresenter(eventBus, view, model);
		model.addTitle("This is a test");
	}
}
