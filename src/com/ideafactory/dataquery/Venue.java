/**
 * 
 */
package com.ideafactory.dataquery;

import java.util.HashMap;

/**
 * @author Tony Ngan
 *
 */
public class Venue {
	protected String id;
	  protected String name;
	  protected String cityName;
	  protected String address;
	  protected String longitude; //Use for support map finder later
	  protected String latitude;

	  // Not sure the data type for this attribute yet
	  //protected image picture;

	  /**
	   * Construtor
	   *
	   * @param HashMap <String, String>
	   * @return Class::Venue
	   */
	  public Venue(HashMap<String, String> hint)
	      throws IllegalArgumentException {
	    this.id = hint.get("id");
	    this.name = hint.get("name");
	    this.longitude = hint.get("longitude");
	    this.latitude = hint.get("latitude");
	    this.cityName = hint.get("cityName");
	    this.address = hint.get("address");
	  }

	  public String getId() {
	    return this.id;
	  }

	  public String getName() {
	    return this.name;
	  }

	  //public Point2D.Double getCoordinate() {
	  //  return new Point2D.Double(Double.parseDouble(this.longitude),Double.parseDouble(this.latitude)); //Double.parseDouble
	  //}

	  public String getCityName() {
	    return this.cityName;
	  }

	  public String getAddress() {
	    return this.address;
	  }
}
