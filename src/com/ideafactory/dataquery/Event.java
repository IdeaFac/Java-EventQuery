/**
 * A data object for storing information about an event
 *
 * @author Mond Wan
 */


package com.ideafactory.dataquery;

import java.util.Date;
import java.util.HashMap;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class Event {
  protected String eventName;
  protected String eventId;
  protected String venueId;
  protected Date startTime;
  protected Date endTime;

  /**
   * Construtor
   *
   * @param HashMap <String, String>
   * @return Class::Event
   * @throw IllegalArgumentException
   */
  public Event(HashMap<String, String> hint) throws IllegalArgumentException {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    // Must throw errors if this is not an expected date format
    formatter.setLenient(false);

    this.eventName = hint.get("eventName");
    this.venueId = hint.get("venueId");
    this.eventId = hint.get("eventId");
    try {
      this.startTime = hint.get("startTime") != null ? formatter.parse(hint.get("startTime")) : null;
      this.endTime = hint.get("endTime") != null ? formatter.parse(hint.get("endTime")) : null;
    } catch(ParseException e) {
      throw new IllegalArgumentException(e);
    }
  }

  public String getEventName() {
    return this.eventName;
  }

  public String getEventId() {
    return this.eventId;
  }

  public Date getStartTime() {
    return this.startTime;
  }

  public Date getEndTime() {
    return this.endTime;
  }
  
  public String getVenueId() {
	return this.venueId;
  }
}