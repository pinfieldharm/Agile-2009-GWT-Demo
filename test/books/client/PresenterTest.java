package books.client;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import books.client.model.Model;
import books.client.model.ModelChangeEvent;
import books.client.view.AddButtonClickedEvent;
import books.client.view.InputPanel;
import books.client.view.RemoveButtonClickedEvent;
import books.client.view.UpButtonClickedEvent;
import books.client.view.View;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;

public class PresenterTest {

	private HandlerManager eventBus;
	private View view;
	private Model model;
	private Presenter presenter;

	@Before
	public void setUp() {
		eventBus = mock(HandlerManager.class);
		view = mock(View.class);
		model = mock(Model.class);
		
		InputPanel inputPanel = mock(InputPanel.class);
		when(view.getInputPanel()).thenReturn(inputPanel);
		
		HasClickHandlers addButton = mock(HasClickHandlers.class);
		when(inputPanel.getAddButton()).thenReturn(addButton);

		presenter = new Presenter(eventBus, view, model);
	}
	
	@Test
	public void registersItselfAsAddButtonHandler() {
		verify(eventBus).addHandler(AddButtonClickedEvent.TYPE, presenter);
	}
	
	@Test
	public void registersItselfAsRemoveButtonHandler() {
		verify(eventBus).addHandler(RemoveButtonClickedEvent.TYPE, presenter);
	}

	@Test
	public void registersItselfAsUpButtonHandler() {
		verify(eventBus).addHandler(UpButtonClickedEvent.TYPE, presenter);
	}
	@Test
	public void registersItselfAsModelChangeHandler() {
		verify(eventBus).addHandler(ModelChangeEvent.TYPE, presenter);
	}
	
}
