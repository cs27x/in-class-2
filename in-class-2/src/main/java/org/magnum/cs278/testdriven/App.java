package org.magnum.cs278.testdriven;

import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import org.joda.time.DateTime;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * A simple application that demonstrates using Java dynamic Proxy objects and
 * reflection to swap out the implementation of one method on an object at
 * runtime.
 * 
 * @author jules
 *
 */
public class App {

	private static final String PARK_SPECIAL_PERMITS = "http://data.nashville.gov/resource/vygj-v677.json";

	private final ObjectMapper objectMapper = new ObjectMapper();
	private final JavaType eventListType = objectMapper.getTypeFactory()
			.constructCollectionType(List.class, Event.class);

	/**
	 * The entry point to Java applications is a "main" method with the exact
	 * signature shown below.
	 * 
	 * @param args
	 */
	public static void main(String[] args) throws Exception {

		App app = new App();
		List<Event> eventList = app.getParkSpecialPermits();
		for (Event e : eventList) {
			System.out.println(e);
		}
	}

	public List<Event> getParkSpecialPermits() throws Exception {
		return objectMapper.readValue(new URL(
				PARK_SPECIAL_PERMITS),
				eventListType
		);
	}
	
	// used to pass lambda to filter
	interface filterLambda{
		public boolean op(Event evt);
	}

	// filters all events by a boolean lambda expression
	public List<Event> filter(filterLambda lambda) throws Exception {
		List<Event> retList = new ArrayList<Event>();
		for (Event evt : getParkSpecialPermits()) {
			if (lambda.op(evt)){
				retList.add(evt);
			}
		}
		return retList;
	}

	public List<Event> getThreeThingsToDo() throws Exception {
		DateTime now = DateTime.now();
		return filter((evt) -> evt.getDateTime().isAfter(now)).subList(0, 3);
	}

	public List<Event> getTodaysEvents() throws Exception {
		Calendar calendar = Calendar.getInstance();
	    calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 0, 0, 0);
	    return filter((evt) -> evt.getDateTime().equals(calendar.getTimeInMillis()));
	}

	public List<Event> getMarchEvents2014() throws Exception{
		return getAllEventsInMonth("mar-2014");
	}
	
	public Event getFirstEventOfMonth(String month) throws Exception {
		List<Event> eventList = filter((evt) -> evt.getMonth().equals(month));
		Collections.sort(eventList, new EventDateComparator());
		return eventList.get(0);	
	}

	public List<Event> getAllEventsInMonth(String date) throws Exception {
		return filter((evt) -> evt.getMonth().equalsIgnoreCase(date));
	}

	public List<Event> getEventsAttendanceLargerThan(int i)  throws Exception {
		return filter((evt) -> Integer.parseInt(evt.getAttendance()) > i);
	}
	
	public List<Event> getEventsAttendanceLargerThanFive() throws Exception {
		return getEventsAttendanceLargerThan(5);
	}

	public List<Event> getEventsInJune() throws Exception {
		return getAllEventsInMonth("Jun-2014");
	}
	
    public List<Event> getRiverfrontEvents() throws Exception {
    	return filter((evt) -> evt.getLocation().equalsIgnoreCase("riverfront park"));
    }

	public List<Event> getEventsByAttendance() throws Exception {
		List<Event> eventList = getParkSpecialPermits();
		Collections.sort(eventList, new EventAttendanceComparator());
		return eventList;
	}
	
	public List<Event> getEventsWithLocation(String location) throws Exception {
		return filter((evt) -> evt.getLocation().equals(location));
	}

}
