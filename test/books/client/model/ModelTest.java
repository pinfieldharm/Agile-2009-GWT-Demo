package books.client.model;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.util.Arrays;

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
		startingWithTitles("First added");

		model.addTitle("Second added");
		
		assertThat(model.getTitles().get(0), is("Second added"));
	}
	
	@Test
	public void removeRemovesTitleAndFiresEvent() {
		startingWithTitles("Foo");
		
		model.removeTitle(0);

		assertFalse(model.getTitles().contains("Foo"));
		verify(eventBus).fireEvent(isA(ModelChangeEvent.class));
	}
		
	@Test
	public void outOfBoundsRemovalIndicesAreIgnored() {
		startingWithTitles("Foo");
		
		model.removeTitle(-1);
		model.removeTitle(12);

		verify(eventBus, never()).fireEvent(isA(ModelChangeEvent.class));
	}
	
	@Test
	public void testMoveTitleUpMovesAndFiresEvent() {
		startingWithTitles("A", "B", "C");

		model.moveTitleUp(2);
		
		assertThat(model.getTitles().get(1), is("C"));
		assertThat(model.getTitles().get(2), is("B"));
		verify(eventBus).fireEvent(isA(ModelChangeEvent.class));
	}

	@Test
	public void outOfBoundsIndicesAreIgnored() {
		startingWithTitles("Foo");
		
		model.moveTitleUp(-1);
		model.moveTitleUp(12);

		verify(eventBus, never()).fireEvent(isA(ModelChangeEvent.class));
	}


	@Test
	public void zeroIndicesAreIgnoredForMovingUp() {
		startingWithTitles("Foo");
		
		model.moveTitleUp(0);

		verify(eventBus, never()).fireEvent(isA(ModelChangeEvent.class));
	}
	
	private void startingWithTitles(String... titles) {
		model.getTitles().addAll(Arrays.asList(titles));
	}

}
