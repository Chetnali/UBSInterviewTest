package com.ubs.opsit.interviews;

import static org.junit.Assert.*;

import org.junit.Test;

public class TimeConverterTest {

	TimeConverterImpl berlinClock = new TimeConverterImpl();
	
	@Test
	public void testShouldGiveCorrectBerlinString() {
		String time="18:26:15";
		String berlinTime = berlinClock.convertTime(time);
		String expectedBerlinTime = new String("O:RRRO:RRRO:YYRYYOOOOOO:YOOO");
		assertEquals(expectedBerlinTime, berlinTime);
	}
	
	
	@Test
	public void testShouldGiveInCorrectBerlinString() {
		String time="15:25:15";
		String berlinTime = berlinClock.convertTime(time);
		String expectedBerlinTime = new String("O:RRRO:RRRO:YYRYYOOOOOO:YOOO");
		assertNotEquals(expectedBerlinTime, berlinTime);
	}
	
	
	@Test (expected = IllegalArgumentException.class)
	public void shouldThrowIllegalArgumentException() {
		String time="";
		berlinClock.convertTime(time);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void shouldThrowIllegalArgumentException1() {
		String time="123";
		berlinClock.convertTime(time);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void shouldThrowIllegalArgumentException2() {
		String time="54:89:90";
		berlinClock.convertTime(time);
	}
	
	@Test
    public void shouldhaveFourLampsInTopHourRow() {
        assertEquals(4, berlinClock.getTopRowHours(18).length());
    }
	
	
	@Test
    public void shouldhaveFourLampsInBottomHourRow() {
        assertEquals(4, berlinClock.getBottomRowHours(23).length());
    }
	
	
	@Test
    public void shouldhave11LampsInTopMinRow() {
        assertEquals(11, berlinClock.getTopRowMins(18).length());
    }
	
	@Test
    public void shouldhave4LampsInBottomMinRow() {
        assertEquals(4, berlinClock.getBottomRowMins(4).length());
    }
	
	
	@Test
    public void shouldgetOnOffEveryTwoSeconds() {
        assertEquals("Y", berlinClock.getSecondsFromBerlinClock(8));
        assertEquals("O", berlinClock.getSecondsFromBerlinClock(13));
        assertNotEquals("O", berlinClock.getSecondsFromBerlinClock(10));
        assertNotEquals("Y", berlinClock.getSecondsFromBerlinClock(5));
    }

}
