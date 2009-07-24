package mq.client.model;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

import com.google.gwt.event.shared.HandlerManager;

public class ModelTest {

	private HandlerManager eventBus;
	private Model model;

	@Before
	public void setUp() {
		eventBus = mock(HandlerManager.class);
		model = new Model(eventBus);
	}
	
	@Test
	public void addInsertsTitleAndFiresEvent() {
		model.addTitle("Foo");

		assertTrue(model.getTitles().contains("Foo"));
		verify(eventBus).fireEvent(isA(ModelChangeEvent.class));
	}
	
	@Test
	public void additionsGoToTopOfList() {
		model.addTitle("First added");
		model.addTitle("Second added");
		
		assertThat(model.getTitles().get(0), is("Second added"));
	}
	
	@Test
	public void removeRemovesTitleAndFiresEvent() {
		model.addTitle("Foo");
		reset(eventBus);
		
		model.removeTitle(0);

		assertFalse(model.getTitles().contains("Foo"));
		verify(eventBus).fireEvent(isA(ModelChangeEvent.class));
	}
		
	@Test
	public void outOfBoundsRemovalIndicesAreIgnored() {
		model.addTitle("Foo");
		reset(eventBus);
		
		model.removeTitle(-1);
		model.removeTitle(12);

		verify(eventBus, never()).fireEvent(isA(ModelChangeEvent.class));
	}
	
	@Test
	public void testMoveTitleUpMovesAndFiresEvent() {
		model.addTitle("A");
		model.addTitle("B");
		model.addTitle("C");
		reset(eventBus);

		// Stack is C,B,A
		model.moveTitleUp(2);
		
		assertThat(model.getTitles().get(1), is("A"));
		verify(eventBus).fireEvent(isA(ModelChangeEvent.class));
	}

	@Test
	public void outOfBoundsIndicesAreIgnored() {
		model.addTitle("Foo");
		reset(eventBus);
		
		model.moveTitleUp(-1);
		model.moveTitleUp(12);

		verify(eventBus, never()).fireEvent(isA(ModelChangeEvent.class));
	}


	@Test
	public void zeroIndicesAreIgnoredForMovingUp() {
		model.addTitle("Foo");
		reset(eventBus);
		
		model.moveTitleUp(0);

		verify(eventBus, never()).fireEvent(isA(ModelChangeEvent.class));
	}

}
