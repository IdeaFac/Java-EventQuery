/**
 * Unit test for class::Connection
 *
 * @author Mond Wan
 */

package com.ideafac.eventquery.unittest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;

import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import com.ideafac.eventquery.eventful.Connection;

public class ConnectionTest {
  @Test
  public void constructPerformerSearchURL() {
    final Connection c = new Connection();

    // Construct a HashMap
    final HashMap<String, String> params = this.createDefaultHashMap();
    params.put("keywords", "jay chou");

    final String url = c.prepareURL("performers/search", params);
    Assert.assertTrue(url.contains("app_key=CfdZBmff5hcGvCxH")
        && url.contains("keywords=jay+chou"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void passIllegalArgumentMethodToPrepareURL() {
    final Connection c = new Connection();

    // Construct a HashMap
    final HashMap<String, String> params = this.createDefaultHashMap();
    params.put("keywords", "jay chou");

    c.prepareURL("oops", params);
  }

  @Test
  public void queryPerformer() {
    final Connection c = new Connection();

    // Construct a HashMap
    final HashMap<String, String> params = this.createDefaultHashMap();
    params.put("keywords", "jay chou");

    final String url = c.prepareURL("performers/search", params);

    try {
      final JSONObject obj = c.query(url);
      assertNotNull(obj);

      // Test the value inside this
      assertEquals("10", obj.getString("page_size"));
      final JSONObject performers = obj.getJSONObject("performers");
      assertNotNull(performers);
      final JSONObject performer = performers.getJSONObject("performer");
      assertNotNull(performer);
      assertEquals("P0-001-000045643-9", performer.getString("id"));

    } catch (final Exception e) {
      // Should not be come here
      throw new Error(e);
    }

  }

  /**
   * Helper function for constructing a default HashMap for testing
   *
   * @return HashMap<String, String>
   */
  protected HashMap<String, String> createDefaultHashMap() {
    final HashMap<String, String> ret = new HashMap<String, String>();
    ret.put("app_key", "CfdZBmff5hcGvCxH");
    return ret;
  }
}
