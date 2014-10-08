/**
 * An unit test for Class::Performer
 *
 * @author Mond Wan
 */

package com.ideafac.eventquery.unittest;

import org.junit.Test;

import java.util.HashMap;

import com.ideafac.eventquery.Performer;

/**
 * Test for {@link Performer}
 */
public class PerformerTest {
  @Test
    public void getInstance() {
      HashMap<String, String> hint = new HashMap<String, String>();

      // Empty hint
      Performer p = new Performer(hint);
      org.junit.Assert.assertNull(p.getEngName());

      hint.put("engName", "Jay Chou");
      hint.put("nationality", "Taiwan");
      hint.put("id", "abc123");

      // Real test
      p = new Performer(hint);
      org.junit.Assert.assertEquals("Jay Chou", p.getEngName());
      org.junit.Assert.assertEquals("Taiwan", p.getNationality());
      org.junit.Assert.assertEquals("abc123", p.getId());
    }
}