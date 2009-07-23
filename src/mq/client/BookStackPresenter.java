package mq.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.VerticalPanel;

public class BookStackPresenter {

	private final HandlerManager eventBus;
	private final View view;
	private final Model model;

	public BookStackPresenter(final HandlerManager eventBus, final View view,
			final Model model) {

		this.eventBus = eventBus;
		this.view = view;
		this.model = model;
		view.getAddButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new AddButtonClickedEvent());
			}
		});

		eventBus.addHandler(AddButtonClickedEvent.TYPE,
				new AddButtonClickedEventHandler() {
					public void onAddButtonClicked(
							AddButtonClickedEvent addButtonClickedEvent) {
						String title = view.getAddBox().getText().trim();
						if (title.length() > 0) {
							model.addTitle(title);
						}
						view.getAddBox().setText("");
					}
				});

		eventBus.addHandler(RemoveButtonClickedEvent.TYPE,
				new RemoveButtonClickedEventHandler() {
					public void onRemoveButtonClicked(
							RemoveButtonClickedEvent removeButtonClickedEvent) {
						model.removeTitle(removeButtonClickedEvent.getPanel().getLabel().getText());
					}
				});

		eventBus.addHandler(AddEvent.TYPE, new AddEventHandler() {
			public void onAdd(AddEvent addEvent) {
				redrawStack();
			}
		});

		eventBus.addHandler(RemoveEvent.TYPE, new RemoveEventHandler() {

			public void onRemove(RemoveEvent removeEvent) {
				redrawStack();
			}
		});

	}

	/*
	 * private void addTopButton() {
	 * 
	 * topButton.addClickHandler(new ClickHandler() { public void
	 * onClick(ClickEvent event) { BookPanel.this.removeFromParent();
	 * queuePanel.insert(BookPanel.this, 0); } }); }
	 */

	private void redrawStack() {
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
		}
	}

}
