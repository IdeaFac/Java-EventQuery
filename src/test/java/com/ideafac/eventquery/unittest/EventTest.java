/**
 * An unit test for Class::Event
 *
 * @author Mond Wan
 */

package com.ideafac.eventquery.unittest;

import java.util.Date;
import java.util.HashMap;

import org.junit.Test;

import com.ideafac.eventquery.Event;

public class EventTest {
  @Test
  public void getInstanceWithCorrectArg() {
    final HashMap<String, String> hint = new HashMap<String, String>();

    // Correct hint
    hint.put("eventName", "My Concert");
    hint.put("eventId", "C1");
    hint.put("startTime", "2014-08-11");
    hint.put("endTime", "2014-08-20");

    final Event e = new Event(hint);
    org.junit.Assert.assertEquals("My Concert", e.getEventName());
    org.junit.Assert.assertEquals("C1", e.getEventId());
    org.junit.Assert.assertTrue(e.getStartTime() instanceof Date);
  }

  @Test(expected = IllegalArgumentException.class)
  public void getInstanceWithInvalidArgs() {
    final HashMap<String, String> hint = new HashMap<String, String>();

    // Correct hint
    hint.put("eventName", "My Concert");
    hint.put("eventId", "C1");
    hint.put("startTime", "aa");
    hint.put("endTime", "2014-08-20");

    // Should throw IllegalArgument
    @SuppressWarnings("unused")
    final Event e = new Event(hint);
  }

  @Test(expected = IllegalArgumentException.class)
  public void getInstanceWithPoorFormat() {
    final HashMap<String, String> hint = new HashMap<String, String>();

    // Correct hint
    hint.put("eventName", "My Concert");
    hint.put("eventId", "C1");
    hint.put("startTime", "2014-089-11");
    hint.put("endTime", "2014-08-20");

    // Should throw IllegalArgument
    @SuppressWarnings("unused")
    final Event e = new Event(hint);
  }
}
