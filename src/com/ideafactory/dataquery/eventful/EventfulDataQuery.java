/**
 * A concrete implementation for Class::DataQuery
 *
 * @author Mond Wan
 */

package com.ideafactory.dataquery.eventful;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

import com.ideafactory.dataquery.DataQuery;
import com.ideafactory.dataquery.Event;
import com.ideafactory.dataquery.Performer;
import com.ideafactory.dataquery.eventful.Connection;

public class EventfulDataQuery extends DataQuery {
  protected String apiKey = "CfdZBmff5hcGvCxH";

  @Override
    public List<Event> searchEventByPerformer(Performer p) {
      return null;
    }

  /**
   * Return a list of Class::Performer
   *
   * @param name string
   * @return List<Performer>
   */
  @Override
    public List<Performer> searchPerformerByName(String name) {
      List<Performer> ret = new ArrayList<Performer>();
      Connection conn = new Connection();

      // Constracut an URL for request
      HashMap<String, String> params = this.createDefaultHashMap();
      params.put("keywords", name);
      String url = conn.prepareURL("performers/search", params);

      // Get a json reply
      try {
        JSONObject res = conn.query(url);
        JSONObject tmp = res.getJSONObject("performers");

        JSONArray performers = null;

        try {
          // There is more than one results
          performers = tmp.getJSONArray("performer");
        } catch(JSONException e) {
          // There is only one result
          performers = new JSONArray();
          performers.put(tmp.getJSONObject("performer"));
        }

        int numOfPerformer = performers.length();
        int i = 0;

        for (i = 0; i < numOfPerformer; i++) {
          JSONObject performer = performers.getJSONObject(i);
          HashMap<String, String> hint = new HashMap<String, String>();
          hint.put("engName", performer.getString("name"));
          hint.put("id", performer.getString("id"));
          Performer p = new Performer(hint);
          ret.add(p);
        }
      } catch (Exception e) {
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
    HashMap<String, String> ret = new HashMap<String, String>();

    ret.put("app_key", this.apiKey);

    return ret;
  }
}