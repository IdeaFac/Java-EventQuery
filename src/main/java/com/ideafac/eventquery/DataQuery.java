/**
 * An abstract class for our data query model.
 *
 * @author Mond Wan
 */

package com.ideafac.eventquery;

import java.util.List;

import com.ideafac.eventquery.Event;
import com.ideafac.eventquery.Performer;

public abstract class DataQuery {
  abstract public List<Event> searchEventByPerformer(Performer p);
  abstract public List<Performer> searchPerformerByName(String n);
  abstract public List<Event> searchEventByVenue(Venue v);
  abstract public void setApiKey(String key);
}