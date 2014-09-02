package org.magnum.cs278.testdriven;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.joda.time.DateTime;
import org.junit.Test;

public class AppTest {

	private App app = new App();
	
	@Test
	public void testGetThreeThingsToDo() throws Exception {
		List<Event> whatToDo = app.getThreeThingsToDo();
		assertEquals(3, whatToDo.size());
		
		DateTime today = DateTime.now();
		
		for(Event thingToDo : whatToDo){
			assertNotNull(thingToDo);
			assertNotNull(thingToDo.getDate());
			
			try{
				DateTime eventDate = Event.DATE_TIME_FORMAT.parseDateTime(thingToDo.getDate());
				assertTrue(eventDate.isAfter(today));
			}catch(IllegalArgumentException arg){
				//The data in data.nashville.gov is..unfortunately...not
				//perfectly clean and we have to ignore the garbage...
			}
		}
	}
	
	@Test
	public void testGetParkSpecialPermits() throws Exception {
		List<Event> events = app.getParkSpecialPermits();
		assertTrue(events.size() > 0);
		for(Event event : events){
			assertNotNull(event);
			assertNotNull(event.getLocation());
			assertNotNull(event.getName());
			assertNotNull(event.getAttendance());
			assertNotNull(event.getDate());
		}
	}
	
	@Test
	public void testGetEventsInJune() throws Exception {
		List<Event> events = app.getEventsInJune();
		for(Event event : events) {
			assertTrue(event.getMonth().toLowerCase().contains("jun"));
		}
	}
	
	@Test
	public void testAttendanceGreaterThanFive() throws Exception{
		List<Event> events = app.AttendanceGreaterThanFive();
		
		for(Event event: events){
			assertTrue(Integer.parseInt(event.getAttendance()) > 5);
		}
	}

	//list of Riverfront park special permits
	@Test
	public void testLocationNashville() throws Exception {
		List<Event> events = app.getRiverfrontParkSpecialPermits();
        assertTrue(events.size() > 0);
		for(Event event : events) {
			assertTrue(event.getLocation().toLowerCase().equals("riverfront park"));
		}
	}
}
