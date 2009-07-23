package mq.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.VerticalPanel;

public class BookStackPresenter implements AddButtonClickedEvent.Handler, RemoveButtonClickedEvent.Handler, ModelChangeEvent.Handler {

	private final HandlerManager eventBus;
	private final View view;
	private final Model model;

	public BookStackPresenter(final HandlerManager eventBus, final View view,
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
		model.removeTitle(removeButtonClickedEvent.getPanel().getLabel().getText());
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
		}
	}

}
