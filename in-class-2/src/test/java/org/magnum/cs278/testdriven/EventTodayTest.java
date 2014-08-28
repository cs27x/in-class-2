package org.magnum.cs278.testdriven;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Test;

public class EventTodayTest {

	private App app = new App();

	@Test
	public void testTodaysEvents() throws Exception {

		List<Event> whatToDo = app.getTodaysEvents();
		DateFormat dateFormat = new SimpleDateFormat("M/d/yy");
		Date date = new Date();
		String today = dateFormat.format(date);
		for (Event thingToDo : whatToDo) {
			assertNotNull(thingToDo);
			assertNotNull(thingToDo.getDate());
			try {
				DateTime eventDate = Event.DATE_TIME_FORMAT
						.parseDateTime(thingToDo.getDate());
				String eventDateStr = eventDate.toString();
				assertTrue(eventDateStr.equals(today));
			} catch (IllegalArgumentException arg) {
			}
		}
	}

}
