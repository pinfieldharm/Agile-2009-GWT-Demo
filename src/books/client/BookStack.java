package books.client;


import books.client.model.Model;
import books.client.view.View;

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
		View view = new View();
		Model model = new Model(eventBus);
		new Presenter(eventBus, view, model);
		model.addTitle("The Art of Computer Programming, Vol. 1");
	}
}
