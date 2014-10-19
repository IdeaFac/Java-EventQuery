/**
 * An unit test for Class::EventfulDataQuery
 *
 * @author Mond Wan
 */

package com.ideafac.eventquery.unittest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.List;

import org.junit.Test;

import com.ideafac.eventquery.Event;
import com.ideafac.eventquery.Performer;
import com.ideafac.eventquery.Venue;
import com.ideafac.eventquery.eventful.EventfulDataQuery;

public class EventfulDataQueryTest {
  protected EventfulDataQuery cursor = new EventfulDataQuery();

  @Test
  public void searchPerformerByNameSmokeTest() {
    final List<Performer> l = this.cursor.searchPerformerByName("Jay Chou");
    if (l != null) {
      assertNotNull(l);
      assertEquals(1, l.size());
      final Performer p = l.get(0);
      assertEquals("P0-001-000045643-9", p.getId());
      assertEquals("Jay Chou", p.getEngName());
    }
  }

  @Test
  public void searchEventByVenueSmokeTest() {
    final HashMap<String, String> hint = new HashMap<String, String>();

    hint.put("id", "V0-001-000104270-1");
    hint.put("name", "Mysterious Galaxy Books");
    hint.put("longitude", "-117.1651930");
    hint.put("latitude", "32.8317369");
    hint.put("cityName", "San Diego");
    hint.put("address", "7051 Clairemont Mesa Boulevard Suite #302");

    final Venue v = new Venue(hint);

    final List<Event> el = this.cursor.searchEventByVenue(v);
    if (el != null) {
      assertNotNull(el);
      assertEquals(10, el.size());
      final Event e = el.get(0);
      assertEquals("V0-001-000104270-1", e.getVenueId());
    }

  }
}
