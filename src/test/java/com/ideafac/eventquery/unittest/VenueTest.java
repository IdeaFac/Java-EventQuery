/**
 * An unit test for Class::Venue
 *
 * @author Tony Ngan
 */

package com.ideafac.eventquery.unittest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.HashMap;

import org.junit.Test;

import com.ideafac.eventquery.Venue;

/**
 * Test for {@link Venue}
 */
public class VenueTest {
  @Test
  public void getInstance() {
    final HashMap<String, String> hint = new HashMap<String, String>();

    /**
     * protected String id;
     * protected String name;
     * protected String cityName;
     * protected String address;
     * protected double longitude; //Use for support map finder later
     * protected double latitude;
     */

    // Empty hint
    Venue v = new Venue(hint);
    assertNull(v.getName());

    hint.put("id", "V0-001-000102741-0");
    hint.put("name", "Humphrey's Concerts By the Bay");
    hint.put("longitude", "-117.222");
    hint.put("latitude", "32.7173");
    hint.put("cityName", "San Diego");
    hint.put("address", "2241 Shelter Island Drive");

    // Real test
    v = new Venue(hint);
    assertEquals("V0-001-000102741-0", v.getId());
    assertEquals("Humphrey's Concerts By the Bay", v.getName());
    assertEquals("San Diego", v.getCityName());
    assertEquals("2241 Shelter Island Drive", v.getAddress());
  }
}
