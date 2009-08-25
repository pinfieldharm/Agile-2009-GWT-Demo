package books.client;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.isA;

import books.client.model.Model;
import books.client.model.ModelChangeEvent;
import books.client.view.AddButtonClickedEvent;
import books.client.view.InputPanel;
import books.client.view.RemoveButtonClickedEvent;
import books.client.view.UpButtonClickedEvent;
import books.client.view.View;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasText;

public class PresenterTest {

	private HandlerManager eventBus;
	private View view;
	private Model model;
	private Presenter presenter;
	private HasClickHandlers addButton;
	private InputPanel inputPanel;
	private HasText addBox;

	@Before
	public void setUp() {
		eventBus = mock(HandlerManager.class);
		view = mock(View.class);
		model = mock(Model.class);
		
		inputPanel = mock(InputPanel.class);
		when(view.getInputPanel()).thenReturn(inputPanel);
		
		addButton = mock(HasClickHandlers.class);
		when(inputPanel.getAddButton()).thenReturn(addButton);

		addBox = mock(HasText.class);
		when(addBox.getText()).thenReturn("");
		when(inputPanel.getAddBox()).thenReturn(addBox);
		
		presenter = new Presenter(eventBus, view, model);
	}
		
	@Test
	public void registersItselfAsAddButtonHandler() {
		verify(eventBus).addHandler(AddButtonClickedEvent.TYPE, presenter);
	}
	
	
	@Test
	public void addEventHandlerMethodAddsToModel() {
		when(addBox.getText()).thenReturn("foo");
		presenter.onAddButtonClicked(new AddButtonClickedEvent());
		verify(model).addTitle("foo");
	}
	
	@Test
	public void addEventHandlerTrimsStringBeforeAddingToModel() {
		when(addBox.getText()).thenReturn("   bar\t  ");
		presenter.onAddButtonClicked(new AddButtonClickedEvent());
		verify(model).addTitle("bar");
	}
	
	@Test
	public void addEventHandlerClearsInputBox() {
		presenter.onAddButtonClicked(new AddButtonClickedEvent());
		verify(addBox).setText("");
	}
	
	@Test
	public void registersItselfAsRemoveButtonHandler() {
		verify(eventBus).addHandler(RemoveButtonClickedEvent.TYPE, presenter);
	}
	
	@Test
	public void removesTitleAtIndexWhenRemoveClicked() {
		presenter.onRemoveButtonClicked(new RemoveButtonClickedEvent(42));
		verify(model).removeTitle(42);
	}

	@Test
	public void registersItselfAsUpButtonHandler() {
		verify(eventBus).addHandler(UpButtonClickedEvent.TYPE, presenter);
	}
	
	@Test
	public void movesTitleAtIndexUpWhenUpClicked() {
		presenter.onUpButtonClicked(new UpButtonClickedEvent(17));
		verify(model).moveTitleUp(17);
	}
	
	@Test
	public void registersItselfAsModelChangeHandler() {
		verify(eventBus).addHandler(ModelChangeEvent.TYPE, presenter);
	}
	
	@Test
	public void registersUIHandlerForAddClickEvent() {
		verify(addButton).addClickHandler(isA(Presenter.AddButtonUIClickHandler.class));
	}
	
	@Test
	public void uiHandlerForAddButtonFiresEventBusEvent() {
		presenter.new AddButtonUIClickHandler().onClick(mock(ClickEvent.class));
		verify(eventBus).fireEvent(isA(AddButtonClickedEvent.class));
	}
}
