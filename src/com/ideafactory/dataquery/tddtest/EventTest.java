/**
 * An unit test for Class::Event
 *
 * @author Mond Wan
 */

package com.ideafactory.dataquery.tddtest;

import org.junit.Test;

import java.util.HashMap;
import java.util.Date;

import com.ideafactory.dataquery.Event;

public class EventTest {
  @Test
    public void getInstanceWithCorrectArg() {
      HashMap<String, String> hint = new HashMap<String, String>();

      // Correct hint
      hint.put("eventName", "My Concert");
      hint.put("eventId", "C1");
      hint.put("startTime", "2014-08-11");
      hint.put("endTime", "2014-08-20");

      Event e = new Event(hint);
      org.junit.Assert.assertEquals("My Concert", e.getEventName());
      org.junit.Assert.assertEquals("C1", e.getEventId());
      org.junit.Assert.assertTrue(e.getStartTime() instanceof Date);
    }

  @Test(expected=IllegalArgumentException.class)
    public void getInstanceWithInvalidArgs() {
      HashMap<String, String> hint = new HashMap<String, String>();

      // Correct hint
      hint.put("eventName", "My Concert");
      hint.put("eventId", "C1");
      hint.put("startTime", "aa");
      hint.put("endTime", "2014-08-20");
      
      // Should throw IllegalArgument
      Event e = new Event(hint);
    }

  @Test(expected=IllegalArgumentException.class)
    public void getInstanceWithPoorFormat() {
      HashMap<String, String> hint = new HashMap<String, String>();

      // Correct hint
      hint.put("eventName", "My Concert");
      hint.put("eventId", "C1");
      hint.put("startTime", "2014-089-11");
      hint.put("endTime", "2014-08-20");
      
      // Should throw IllegalArgument
      Event e = new Event(hint);
    }
}