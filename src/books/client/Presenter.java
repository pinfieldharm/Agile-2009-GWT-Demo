package books.client;


import books.client.model.Model;
import books.client.model.ModelChangeEvent;
import books.client.view.AddButtonClickedEvent;
import books.client.view.BookPanel;
import books.client.view.BookStackPanel;
import books.client.view.RemoveButtonClickedEvent;
import books.client.view.UpButtonClickedEvent;
import books.client.view.View;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;

public class Presenter implements AddButtonClickedEvent.Handler, RemoveButtonClickedEvent.Handler, UpButtonClickedEvent.Handler, ModelChangeEvent.Handler {

	private final HandlerManager eventBus;
	private final View view;
	private final Model model;

	public Presenter(final HandlerManager eventBus, final View view,
			final Model model) {

		this.eventBus = eventBus;
		this.view = view;
		this.model = model;

		/* Bind low-level UI events */
		view.getInputPanel().getAddButton().addClickHandler(new AddButtonUIClickHandler());

		/* Bind to higher-level UI events */
		eventBus.addHandler(AddButtonClickedEvent.TYPE, this);
		eventBus.addHandler(RemoveButtonClickedEvent.TYPE, this);
		eventBus.addHandler(UpButtonClickedEvent.TYPE, this);

		/* Bind to model events */
		eventBus.addHandler(ModelChangeEvent.TYPE, this);
	}

	public void onAddButtonClicked(AddButtonClickedEvent addButtonClickedEvent) {
		String title = view.getInputPanel().getAddBox().getText().trim();
		if (title.length() > 0) {
			model.addTitle(title);
		}
		view.getInputPanel().getAddBox().setText("");
	}

	public void onModelChange(ModelChangeEvent addEvent) {
		BookStackPanel stackPanel = view.getStackPanel();
		stackPanel.clear();
		
		for (int i = 0; i < model.getTitles().size(); i++) {
			String title = model.getTitles().get(i);
			BookPanel bookPanel = stackPanel.addBookPanel(title);
			bookPanel.getRemoveButton().addClickHandler(new RemoveButtonUIClickHandler(i));
			bookPanel.getUpButton().addClickHandler(new UpButtonUIClickHandler(i));
		}
	}

	public void onRemoveButtonClicked(RemoveButtonClickedEvent removeButtonClickedEvent) {
		model.removeTitle(removeButtonClickedEvent.getPosition());
	}

	public void onUpButtonClicked(UpButtonClickedEvent removeButtonClickedEvent) {
		model.moveTitleUp(removeButtonClickedEvent.getPosition());
	}

	protected final class UpButtonUIClickHandler implements ClickHandler {
		private final int position;

		protected UpButtonUIClickHandler(int position) {
			this.position = position;
		}

		public void onClick(ClickEvent event) {
			eventBus.fireEvent(new UpButtonClickedEvent(position));
		}
	}

	protected final class RemoveButtonUIClickHandler implements ClickHandler {
		private final int position;

		protected RemoveButtonUIClickHandler(int position) {
			this.position = position;
		}

		public void onClick(ClickEvent event) {
			eventBus.fireEvent(new RemoveButtonClickedEvent(position));
		}
	}

	protected final class AddButtonUIClickHandler implements ClickHandler {
		public void onClick(ClickEvent event) {
			eventBus.fireEvent(new AddButtonClickedEvent());
		}
	}

	
}
