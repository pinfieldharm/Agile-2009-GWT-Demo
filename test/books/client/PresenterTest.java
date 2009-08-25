package books.client;

import static java.util.Arrays.asList;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import books.client.model.Model;
import books.client.model.ModelChangeEvent;
import books.client.view.AddButtonClickedEvent;
import books.client.view.BookPanel;
import books.client.view.BookStackPanel;
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
	private BookStackPanel bookStackPanel;

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
		
		bookStackPanel = mock(BookStackPanel.class);
		when(view.getStackPanel()).thenReturn(bookStackPanel);
		
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
	
	@Test
	public void clearsStackPanelAndAddsEachTitleWhenModelChanges() {
		ArrayList<String> titles = new ArrayList<String>(asList("foo", "bar", "baz"));
		when(model.getTitles()).thenReturn(titles);
		BookPanel bookPanel = mockBookPanel();
		when(bookStackPanel.addBookPanel(Mockito.anyString())).thenReturn(bookPanel);

		presenter.onModelChange(new ModelChangeEvent());

		verify(bookStackPanel).clear();
		verify(bookStackPanel).addBookPanel("foo");
		verify(bookStackPanel).addBookPanel("bar");
		verify(bookStackPanel).addBookPanel("baz");
	}

	@Test
	public void addsPresenterAsEventHandlerToNewPanelsWhenModelChanges() {
		ArrayList<String> titles = new ArrayList<String>(asList("foo"));
		when(model.getTitles()).thenReturn(titles);
		BookPanel bookPanel = mockBookPanel();
		when(bookStackPanel.addBookPanel(Mockito.anyString())).thenReturn(bookPanel);

		presenter.onModelChange(new ModelChangeEvent());

		verify(bookPanel.getRemoveButton()).addClickHandler(isA(Presenter.RemoveButtonUIClickHandler.class));
		verify(bookPanel.getUpButton()).addClickHandler(isA(Presenter.UpButtonUIClickHandler.class));
	}

	private BookPanel mockBookPanel() {
		BookPanel bookPanel = mock(BookPanel.class);
		HasClickHandlers removeButton = mock(HasClickHandlers.class);
		when(bookPanel.getRemoveButton()).thenReturn(removeButton);
		HasClickHandlers addButton = mock(HasClickHandlers.class);
		when(bookPanel.getUpButton()).thenReturn(addButton);
		return bookPanel;
	}


}
