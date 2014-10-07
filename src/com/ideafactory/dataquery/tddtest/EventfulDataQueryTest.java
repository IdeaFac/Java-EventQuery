/**
 * An unit test for Class::EventfulDataQuery
 *
 * @author Mond Wan
 */

package com.ideafactory.dataquery.tddtest;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.List;

import com.ideafactory.dataquery.Performer;
import com.ideafactory.dataquery.eventful.EventfulDataQuery;

public class EventfulDataQueryTest {
  protected EventfulDataQuery cursor = new EventfulDataQuery();
  @Test
    public void searchPerformerByNameSmokeTest() {
      List<Performer> l = this.cursor.searchPerformerByName("Jay Chou");
      if(l!=null){
	      assertNotNull(l);
	      assertEquals(1, l.size());
	      Performer p = l.get(0);
	      assertEquals("P0-001-000045643-9", p.getId());
	      assertEquals("Jay Chou", p.getEngName());
      }
    }
}