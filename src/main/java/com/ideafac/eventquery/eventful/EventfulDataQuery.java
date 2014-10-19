/**
 * A concrete implementation for Class::DataQuery
 *
 * @author Mond Wan, Tony Ngan
 */

package com.ideafac.eventquery.eventful;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.ideafac.eventquery.DataQuery;
import com.ideafac.eventquery.Event;
import com.ideafac.eventquery.Performer;
import com.ideafac.eventquery.Venue;

public class EventfulDataQuery extends DataQuery {
  protected String apiKey = "CfdZBmff5hcGvCxH";
  private final Connection conn = new Connection(); // Declare once only

  /**
   * Set the api key for authentication
   *
   * @param String key
   */
  @Override
  public void setApiKey(String key) {
    this.apiKey = key;
  }

  /**
   * Return a list of Class::Event
   * http://api.eventful.com/docs/events/search
   *
   * @param object Class::Venue
   * @return List<Event>
   */
  @Override
  public List<Event> searchEventByVenue(Venue v) {
    final List<Event> ret = new ArrayList<Event>();
    final String vID = v.getId();
    final HashMap<String, String> params = this.createDefaultHashMap();
    params.put("location", vID);
    final String url = this.conn.prepareURL("events/search", params);
    try {
      final JSONObject res = this.conn.query(url);
      final JSONObject tmp = res.getJSONObject("events");
      JSONArray events = null;
      try {
        // There is more than one results
        events = tmp.getJSONArray("event");
      } catch (final JSONException e) {
        // There is only one result
        events = new JSONArray();
        events.put(tmp.getJSONObject("event"));
      }

      for (int i = 0, numOfEvent = events.length(); i < numOfEvent; ++i) {
        final JSONObject event = events.getJSONObject(i);
        final HashMap<String, String> hint = new HashMap<String, String>();
        hint.put("eventName", event.getString("title"));
        hint.put("eventId", event.getString("id"));
        hint.put("venueId", event.getString("venue_id"));
        hint.put("startTime", event.get("start_time") == null ? event
            .getString("start_time") : null);
        hint.put("endTime", event.get("stop_time") == null ? event
            .getString("stop_time") : null);
        final Event e = new Event(hint);
        ret.add(e);
      }
    } catch (final Exception e) {
      throw new Error(e);
    }
    return ret;
  }

  /**
   * Return a list of Class::Event
   *
   * @param object Class::Performer
   * @return List<Event>
   */
  @Override
  public List<Event> searchEventByPerformer(Performer p) {
    final List<Event> ret = new ArrayList<Event>();
    final String pID = p.getId();
    final HashMap<String, String> params = this.createDefaultHashMap();
    params.put("location", pID);
    final String url = this.conn.prepareURL("performers/events/list", params);
    try {
      final JSONObject res = this.conn.query(url);
      final JSONObject tmp = res.getJSONObject("events");
      JSONArray events = null;
      try {
        // There is more than one results
        events = tmp.getJSONArray("event");
      } catch (final JSONException e) {
        // There is only one result
        events = new JSONArray();
        events.put(tmp.getJSONObject("event"));
      }

      for (int i = 0, numOfEvent = events.length(); i < numOfEvent; ++i) {
        final JSONObject event = events.getJSONObject(i);
        final HashMap<String, String> hint = new HashMap<String, String>();
        hint.put("eventName", event.getString("title"));
        hint.put("eventId", event.getString("id"));
        hint.put("startTime", event.get("start_time") == null ? event
            .getString("start_time") : null);
        hint.put("endTime", event.get("stop_time") == null ? event
            .getString("stop_time") : null);
        final Event e = new Event(hint);
        ret.add(e);
      }
    } catch (final Exception e) {
      throw new Error(e);
    }
    return ret;
  }

  /**
   * Return a list of Class::Performer
   *
   * @param name string
   * @return List<Performer>
   */
  @Override
  public List<Performer> searchPerformerByName(String name) {
    final List<Performer> ret = new ArrayList<Performer>();

    // Constracut an URL for request
    final HashMap<String, String> params = this.createDefaultHashMap();
    params.put("keywords", name);
    final String url = this.conn.prepareURL("performers/search", params);

    // Get a json reply
    try {
      final JSONObject res = this.conn.query(url);
      final JSONObject tmp = res.getJSONObject("performers");

      JSONArray performers = null;

      try {
        // There is more than one results
        performers = tmp.getJSONArray("performer");
      } catch (final JSONException e) {
        // There is only one result
        performers = new JSONArray();
        performers.put(tmp.getJSONObject("performer"));
      }

      for (int i = 0, numOfPerformer = performers.length(); i < numOfPerformer; ++i) {
        final JSONObject performer = performers.getJSONObject(i);
        final HashMap<String, String> hint = new HashMap<String, String>();
        hint.put("engName", performer.getString("name"));
        hint.put("id", performer.getString("id"));
        final Performer p = new Performer(hint);
        ret.add(p);
      }
    } catch (final Exception e) {
      throw new Error(e);
    }
    return ret;
  }

  /**
   * Helper function for constructing a default HashMap
   *
   * @return HashMap<String, String>
   */
  protected HashMap<String, String> createDefaultHashMap() {
    final HashMap<String, String> ret = new HashMap<String, String>();

    ret.put("app_key", this.apiKey);

    return ret;
  }
}
