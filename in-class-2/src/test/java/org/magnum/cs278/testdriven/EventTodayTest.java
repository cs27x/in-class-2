package org.magnum.cs278.testdriven;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Test;

public class EventTodayTest {
	
	private App app = new App();

	@Test
	public void testTodaysEvents() throws Exception {
		List<Event> whatToDo = app.getTodaysEvents();

		DateTime today = DateTime.now();
		
		for(Event thingToDo : whatToDo){
			assertNotNull(thingToDo);
			assertNotNull(thingToDo.getDate());
			
			try{
				DateTime eventDate = Event.DATE_TIME_FORMAT.parseDateTime(thingToDo.getDate());
				assertTrue(eventDate.isEqualNow());
			} catch(IllegalArgumentException arg) {
                arg.printStackTrace();
			}
		}
	}
	

}
