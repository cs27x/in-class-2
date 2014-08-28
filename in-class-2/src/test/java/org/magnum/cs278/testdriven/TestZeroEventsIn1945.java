package org.magnum.cs278.testdriven;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.joda.time.DateTime;
import org.junit.Test;

public class TestZeroEventsIn1945 {

	private App app = new App();
	
	
	
	@Test
	public void testZeroEventsIn1945() throws Exception {
		List<Event> events = app.getEventsForYear("1945");
		assertTrue(events.size() == 0);
	}
	

}
