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

public class Presenter implements AddButtonClickedEvent.Handler,
		RemoveButtonClickedEvent.Handler, UpButtonClickedEvent.Handler,
		ModelChangeEvent.Handler {

	private final HandlerManager eventBus;
	private final View view;
	private final Model model;

	public Presenter(final HandlerManager eventBus, final View view,
			final Model model) {

		this.eventBus = eventBus;
		this.view = view;
		this.model = model;

		bindEventHandlers(eventBus, view);
	}

	private void bindEventHandlers(final HandlerManager eventBus,
			final View view) {
		view.getInputPanel().getAddButton().addClickHandler(
				new AddButtonUIClickHandler());

		eventBus.addHandler(AddButtonClickedEvent.TYPE, this);
		eventBus.addHandler(RemoveButtonClickedEvent.TYPE, this);
		eventBus.addHandler(UpButtonClickedEvent.TYPE, this);

		eventBus.addHandler(ModelChangeEvent.TYPE, this);
	}

	protected final class AddButtonUIClickHandler implements ClickHandler {
		public void onClick(ClickEvent event) {
			eventBus.fireEvent(new AddButtonClickedEvent());
		}
	}
	
	public void onAddButtonClicked(AddButtonClickedEvent addButtonClickedEvent) {
		String title = view.getInputPanel().getAddBox().getText().trim();
		if (title.length() > 0) {
			model.addTitle(title);
		}
		view.getInputPanel().getAddBox().setText("");
	}

	public void onModelChange(ModelChangeEvent event) {
		BookStackPanel stackPanel = view.getStackPanel();
		stackPanel.clear();
		for (int pos = 0; pos < model.getTitles().size(); pos++) {
			String title = model.getTitles().get(pos);
			BookPanel bookPanel = stackPanel.addBookPanel(title);
			bookPanel.getRemoveButton().addClickHandler(new RemoveButtonUIClickHandler(pos));
			bookPanel.getUpButton().addClickHandler(new UpButtonUIClickHandler(pos));
		}
	}

	public void onRemoveButtonClicked(RemoveButtonClickedEvent removeButtonClickedEvent) {
		model.removeTitle(removeButtonClickedEvent.getPosition());
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

}
