package org.magnum.cs278.testdriven;

import java.net.URL;
import java.util.ArrayList;
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
		List<Event> evts = app.getParkSpecialPermits();
		for (Event e : evts) {
			System.out.println(e);
		}
	}

	public List<Event> getThreeThingsToDo() throws Exception {
		List<Event> toDo = new ArrayList<Event>();
		List<Event> evts = getParkSpecialPermits();

		DateTime now = DateTime.now();
		for (Event evt : evts) {
			if (evt.getDateTime().isAfter(now)) {
				toDo.add(evt);
				if (toDo.size() >= 3) {
					break;
				}
			}
		}

		return toDo;
	}

	public List<Event> getParkSpecialPermits() throws Exception {
		return objectMapper.readValue(new URL(
				PARK_SPECIAL_PERMITS),
				eventListType
				);
	}

	public List<Event> getEventsInJune() throws Exception {
		List<Event> juneEvents = new ArrayList<Event>();
		List<Event> events = getParkSpecialPermits();
		
		for (Event event : events) {
			if (event.getMonth().contains("jun")) {
				juneEvents.add(event);
			}
		}
		
		return juneEvents;
	}
	
    public List<Event> getRiverfrontParkSpecialPermits() throws Exception {
        List<Event> evts = new ArrayList<Event>();

        for (Event evt : getParkSpecialPermits()) {
            if (evt.getLocation().toLowerCase().equals("riverfront park")) {
                evts.add(evt);
            }
        }
        return evts;
    }
	
	public List<Event> AttendanceGreaterThanFive() throws Exception {
		
		List<Event> toReturn = new ArrayList<Event>();
		List<Event> evts = getParkSpecialPermits();

		for (Event evt : evts) {
			
			if(Integer.parseInt(evt.getAttendance()) > 5){
				toReturn.add(evt);
			}
		}

		return toReturn;
	}
	
	public List<Event> getEventsForYear(String yearString) throws Exception{
		List<Event> evtsForYear = new ArrayList<Event>();
		List<Event> evts = getParkSpecialPermits();
		
		yearString = yearString.substring(yearString.length() - 2);

		for (Event evt : evts) {
			String eventDate = evt.getDate();
			eventDate.substring(eventDate.length() - 2);
			if (eventDate == yearString) {
				evtsForYear.add(evt);
			}
		}

		return evtsForYear;
	}
}
