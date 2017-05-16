package ru.stqa.pft.sendbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Алёна on 16.05.2017.
 */
@Test
public class PointTest3 {
  public void testArea(){
    Point p1 = new Point(-1,-3);
    Point p2 = new Point(-1,-8);
    Assert.assertEquals(p1.distance(p2), 5.0);
  }
}
