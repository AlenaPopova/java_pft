package ru.stqa.pft.sendbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Алёна on 16.05.2017.
 */

//expexted = actual result
@Test
public class PointTest1 {
  public void testArea() {
    Point p1 = new Point(1,3);
    Point p2 = new Point(1,1);
    Assert.assertEquals(p1.distance(p2),2.0);
  }
}
