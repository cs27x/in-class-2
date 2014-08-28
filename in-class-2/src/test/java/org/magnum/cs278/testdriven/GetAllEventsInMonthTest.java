package org.magnum.cs278.testdriven;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class GetAllEventsInMonthTest {
	App app = new App();
	@Test
	public void test() {
		List <Event> evts = getAllEventsInMonth("january");
		for(Event e : evts){
			assertTrue(e.getMonth().toLowerCase().equals("january"));
		}
		
	}

}
