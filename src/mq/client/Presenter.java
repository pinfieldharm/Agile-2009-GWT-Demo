package mq.client;

import mq.client.model.Model;
import mq.client.model.ModelChangeEvent;
import mq.client.view.AddButtonClickedEvent;
import mq.client.view.BookPanel;
import mq.client.view.RemoveButtonClickedEvent;
import mq.client.view.UpButtonClickedEvent;
import mq.client.view.View;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.VerticalPanel;

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
		view.getAddButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new AddButtonClickedEvent());
			}
		});

		/* Bind to higher-level UI events */
		eventBus.addHandler(AddButtonClickedEvent.TYPE, this);
		eventBus.addHandler(RemoveButtonClickedEvent.TYPE, this);
		eventBus.addHandler(UpButtonClickedEvent.TYPE, this);

		/* Bind to model events */
		eventBus.addHandler(ModelChangeEvent.TYPE, this);
	}

	/*
	 * private void addTopButton() {
	 * 
	 * topButton.addClickHandler(new ClickHandler() { public void
	 * onClick(ClickEvent event) { BookPanel.this.removeFromParent();
	 * queuePanel.insert(BookPanel.this, 0); } }); }
	 */

	public void onAddButtonClicked(AddButtonClickedEvent addButtonClickedEvent) {
		String title = view.getAddBox().getText().trim();
		if (title.length() > 0) {
			model.addTitle(title);
		}
		view.getAddBox().setText("");
	}

	public void onRemoveButtonClicked(RemoveButtonClickedEvent removeButtonClickedEvent) {
		BookPanel bookPanel = removeButtonClickedEvent.getPanel();
		int position = view.getStackPanel().getWidgetIndex(bookPanel);
		model.removeTitle(position);
	}

	public void onModelChange(ModelChangeEvent addEvent) {
		VerticalPanel stackPanel = view.getStackPanel();
		stackPanel.clear();
		for (String title : model.getTitles()) {
			final BookPanel bookPanel = new BookPanel(title);
			stackPanel.add(bookPanel);
			bookPanel.getRemoveButton().addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					eventBus.fireEvent(new RemoveButtonClickedEvent(bookPanel));
				}
			});
			bookPanel.getUpButton().addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					eventBus.fireEvent(new UpButtonClickedEvent(bookPanel));
				}
			});

		}
	}

	public void onUpButtonClicked(UpButtonClickedEvent removeButtonClickedEvent) {
		BookPanel bookPanel = removeButtonClickedEvent.getPanel();
		int position = view.getStackPanel().getWidgetIndex(bookPanel);
		model.moveTitleUp(position);
	}

}