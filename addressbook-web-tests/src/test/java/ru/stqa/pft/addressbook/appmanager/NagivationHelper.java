package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by Алёна on 23.05.2017.
 */
public class NagivationHelper {
  private FirefoxDriver wd;

  public NagivationHelper(FirefoxDriver wd) {
    this.wd = wd;
  }

  public void gotoGroupPage() {
      wd.findElement(By.linkText("groups")).click();
  }
}
