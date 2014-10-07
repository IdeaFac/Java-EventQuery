/**
 * An abstract class for our data query model.
 *
 * @author Mond Wan
 */

package com.ideafactory.dataquery;

import java.util.List;

import com.ideafactory.dataquery.Event;
import com.ideafactory.dataquery.Performer;

public abstract class DataQuery {
  abstract public List<Event> searchEventByPerformer(Performer p);
  abstract public List<Performer> searchPerformerByName(String n);
  abstract public List<Event> searchEventByVenue(Venue v);
  abstract public void setApiKey(String key);
}