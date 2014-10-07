/**
 * A class provides a bunch of methods to query eventful database
 *
 * @author Mond Wan
 */

package com.ideafactory.dataquery.eventful;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class Connection {
  // Hard coding a list of supported methods
  protected static final String[] methods = {
    "performers/search",
    "performers/events/list",
    "/venues/search",
    "events/search"
  };

  // Request JSON respond
  protected static final String baseURL = "http://api.eventful.com/json/";

  /**
   * Helper function for creating URL for asking eventful database
   *
   * @param method String
   * @param params HashMap<String, String>
   * @return String
   * @throws IllegalArgumentException
   *   - if invalid method is given
   *   - if UTF-8 is not a supported encoding on URLEncoder
   */
  public String prepareURL(String method, HashMap<String, String> params)
    throws IllegalArgumentException {

    String ret = Connection.baseURL + method + "?";
    boolean first = true;
    boolean illegalMethod = true;

    // Validate given method is a supported method
    for (String s: Connection.methods) {
      if (s.equals(method)) {
        illegalMethod = false;
        break;
      }
    }
    if (illegalMethod) {
      throw new IllegalArgumentException(
        String.format("Invalid method |%s|", method)
      );
    }

    for (Map.Entry<String, String>entry : params.entrySet()) {
      try {
        // Skip null
        if ((entry.getValue() == null) || (entry.getKey() == null)) {
          continue;
        }
        if (entry.getValue().length() == 0) {
          continue;
        }

        if (first) {
          first = false;
        } else {
          ret += "&";
        }

        ret += String.format(
          "%s=%s",
          URLEncoder.encode(entry.getKey(), "UTF-8"),
          URLEncoder.encode(entry.getValue(), "UTF-8")
        );
      } catch(UnsupportedEncodingException e) {
        throw new IllegalArgumentException(e);
      }
    }

    return ret;
  }

  /**
   * Query the eventful database
   *
   * @param location String
   * @return JSONObject
   * @throws IOException
   *   - if there are errors on http protocol
   * @throws JSONException
   *   - if response from eventful is not a JSON format
   */
  public JSONObject query(String location) throws IOException, JSONException {
    // Android depends on DefaultHttpClient
	HttpClient httpclient = new DefaultHttpClient();
    HttpGet httpget = new HttpGet(location);
    String ctx = null;
    JSONObject ret = null;

    try {
      // Get the http response from the request
      HttpResponse res = httpclient.execute(httpget);

      // Get the response body
      ctx = EntityUtils.toString(res.getEntity());

      // Convert the body to a JSON object
      ret = new JSONObject(ctx);
    } catch (ClientProtocolException e) {
      throw new IOException(e);
    }
    
    return ret;
  }
  
}