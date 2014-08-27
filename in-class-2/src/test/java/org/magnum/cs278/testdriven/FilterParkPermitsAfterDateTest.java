package org.magnum.cs278.testdriven;

import java.util.List;

import static org.junit.Assert.*;
import org.joda.time.DateTime;
import org.junit.Test;

public class FilterParkPermitsAfterDateTest {

	private App app = new App();
	
	@Test
	public void test() throws Exception {
		DateTime now = DateTime.now();
		List<Event> events = app.getParkSpecialPermitsAfterDate(now);
		
		for(Event event : events){
			assertTrue(event.getDateTime().isAfter(now));
		}
	}

}
