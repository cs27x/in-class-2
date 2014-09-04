package org.magnum.cs278.testdriven;

import static org.junit.Assert.*;

import java.util.List;

import org.joda.time.DateTime;
import org.junit.Test;

public class GetLocationTest {
	
	private App app = new App();
	
	@Test
	public void testGetSanFrancisco() throws Exception {
		List<Event> sanFranEvents = app.getEventsWithLocation("San Francisco");
				
		for(Event event : sanFranEvents){
			assertNotNull(event);
			assertNotNull(event.getLocation());
			assertNotNull(event.getName());
			assertNotNull(event.getAttendance());
			assertNotNull(event.getDate());
			assertEquals(event.getLocation(), "San Francisco");
		}
	}

}
