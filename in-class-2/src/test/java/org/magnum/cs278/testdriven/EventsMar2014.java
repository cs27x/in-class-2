package org.magnum.cs278.testdriven;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class EventsMar2014 {

	private App app = new App();
	@Test
	public void testMonthAndYearEvents() throws Exception {
		List<Event> pubs = app.getEventsForMonthAndYear("mar", "2014");
		assertTrue(pubs.size() > 0); // At least one March-2014 event.
		for(Event temp : pubs) {
			assertEqualsIgnoreCase(temp.getMonth(),"Mar-2014");
		}
	}
	private void assertEqualsIgnoreCase(String month, String string) {
		// TODO Auto-generated method stub
		
	}
}
