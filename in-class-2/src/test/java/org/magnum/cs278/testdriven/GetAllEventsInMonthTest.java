package org.magnum.cs278.testdriven;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class GetAllEventsInMonthTest {
	private App app = new App();
	@Test
	public void test() throws Exception {
		List <Event> evts = app.getAllEventsInMonth("Jan-2014");
		for(Event e : evts){
			assertTrue(e.getMonth().equals("Jan-2014"));
		}
		
	}

}
