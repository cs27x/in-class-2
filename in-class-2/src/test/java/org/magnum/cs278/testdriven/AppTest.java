package org.magnum.cs278.testdriven;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.joda.time.DateTime;
import org.junit.Test;

public class AppTest {

	private App app = new App();
	
	/**
	 * Tests getThreeThingsToDo by checking and confirming the size of the list 
	 * is of length 3; it also tests to ensure events in the list are not null.
	 * @throws Exception
	 */
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
	
	/**
	 * Tests getParkSpecialPermits by checking for a positive list size 
	 * and not null list elements. 
	 * @throws Exception
	 */
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
	
	/**
	 * Tests getEventsInJune by checking if the events in the list have
	 * the month "jun" as their month value. 
	 * @throws Exception
	 */
	@Test
	public void testGetEventsInJune() throws Exception {
		List<Event> events = app.getEventsInJune();
		for(Event event : events) {
			assertTrue(event.getMonth().toLowerCase().contains("jun"));
		}
	}

	/**
	 * Tests AttendanceGreaterThanFive by checking the attendance value is 
	 * greater than 5. 
	 * @throws Exception
	 */
	@Test
	public void testAttendanceGreaterThanFive() throws Exception {
		List<Event> events = app.AttendanceGreaterThanFive();
		for(Event event: events){
			assertTrue(Integer.parseInt(event.getAttendance()) > 5);
		}
	}

	/**
	 * Tests getRiverfrontParkSpecialPermits by checking that the location 
	 * is in some part of Nashville, specifically "riverfront park".
	 * @throws Exception
	 */
	@Test
	public void testLocationNashville() throws Exception {
		List<Event> events = app.getRiverfrontParkSpecialPermits();
        assertTrue(events.size() > 0);
		for(Event event : events) {
			assertTrue(event.getLocation().toLowerCase().equals("riverfront park"));
		}
	}

	/**
	 * Tests getFirstEventOfMonth using a known month and event name.
	 * @throws Exception
	 */
    @Test
	public void testGetFirstEventOfMonth() throws Exception {
		String month = "Feb-2014";
		String testEventName = "Cupid's Chase";
		
		Event first = app.getFirstEventOfMonth(month);
		
		assertTrue(first.getName().equals(testEventName));
	}

    /**
     * Tests getParkSpecialPermitsByAttendance by checking for positive event size
     * and going through the event list and checking that the events have non null values
     * @throws Exception
     */
    @Test
	public void testGetParkSpecialPermitsByAttendance() throws Exception {
		List<Event> events = app.getParkSpecialPermitsByAttendance();
		assertTrue(events.size() > 0);
		boolean sorted = true;
		double last = Double.POSITIVE_INFINITY;
		for(Event event : events){
			if (Double.parseDouble(event.getAttendance()) > last){
				sorted = false;
			}
			else {
				last = Double.parseDouble(event.getAttendance());
			}
			assertNotNull(event);
			assertNotNull(event.getLocation());
			assertNotNull(event.getName());
			assertNotNull(event.getAttendance());
			assertNotNull(event.getDate());
		}
		assertTrue(sorted);
	}

    /**
     * Tests checkLocation by checking for positive list size and checking if 
     * all the events in the list match the location they checked for. 
     * @throws Exception
     */
    @Test
    public void testCheckLocation() throws Exception {

        List<Event> events = app.getEventsWithLocation("East Park");
        assertTrue(events.size() > 0);

        for(Event event : events){
            assertTrue(event.getLocation().equals("East Park"));
        }
    }

    /**
     * Tests getEventsForMonth by checking that there is only one Month-2014
     * element in the list and checking that the tested month is actually the month
     * in all the events in the list.
     * @throws Exception
     */
	@Test
	public void testGetEventsForMonth() throws Exception {
		
		List<Event> events = app.getEventsForMonth("Jan-2014");
		assertTrue(events.size() == 1);
		assertEquals("Jan-2014", events.get(0).getMonth());
	}

	/**
	 * Tests getEventsLargerThan() by going through the list of events
	 * and checks that the attendance is larger than the tested integer value
	 * @throws Exception
	 */
	@Test
	public void testGetEventsLargerThan() throws Exception {
		List<Event> events = app.getEventsLargerThan(1000);
		for (Event event : events)
			assertTrue(Integer.parseInt(event.getAttendance()) > 1000);
	}

	/**
	 * Tests getAllEventsInMonth by checking that all the events in the list
	 * match a tested month.
	 * @throws Exception
	 */
	@Test
	public void testGetAllEventsInMonth()  throws Exception{
		List <Event> evts = app.getAllEventsInMonth("january");
		for(Event e : evts){
			assertTrue(e.getMonth().toLowerCase().equals("january"));
		}
	}
	
	@Test
	public void testGetSanFrancisco() throws Exception {
		List<Event> sanFranEvents = app.getEventsWithLocation("San Francisco");
		
		DateTime today = DateTime.now();
		
		for(Event event : sanFranEvents){
			assertNotNull(event);
			assertNotNull(event.getLocation());
			assertNotNull(event.getName());
			assertNotNull(event.getAttendance());
			assertNotNull(event.getDate());
			assertEquals(event.getLocation(), "San Francisco");
		}
	}
	
	@Test
	public void test() throws Exception {
		List<Event> pubs = app.getMarchEvents2014();
		assertTrue(pubs.size() > 0); // At least one March-2014 event.
		for(Event temp : pubs) {
			assertEquals(temp.getMonth(),"Mar-2014");
		}
	}
	
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
	
	@Test
	public void testZeroEventsIn1945() throws Exception {
		List<Event> events = app.getEventsForYear("1945");
		assertTrue(events.size() == 0);
	}
}
