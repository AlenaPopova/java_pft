package ru.stqa.pft.sendbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Алёна on 16.05.2017.
 */
//expected <>actual result
@Test
public class PointTest2 {
  public void testArea() {
    Point p1 = new Point(-2,0);
    Point p2 = new Point(6,1);
    Assert.assertEquals(p1.distance(p2),8.06225774829855);
  }
}
