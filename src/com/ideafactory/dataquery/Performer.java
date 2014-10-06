/**
 * A data object for storing information about a performer
 * 
 * @author Mond Wan
 */

package com.ideafactory.dataquery;

import java.util.HashMap;

public class Performer {
  protected String engName;

  protected String nationality;
  
  protected String id;

  // Not sure the datatype for this attribute yet
  //protected image picture;

  /**
   * Construtor
   *
   * @param HashMap <String, String>
   * @return Clsas::Performer
   */
  public Performer(HashMap<String, String> hint)
      throws IllegalArgumentException {
    this.engName = hint.get("engName");
    this.nationality = hint.get("nationality");
    this.id = hint.get("id");
  }

  public String getEngName() {
    return this.engName;
  }

  public String getNationality() {
    return this.nationality;
  }

  public String getId() {
    return this.id;
  }
}