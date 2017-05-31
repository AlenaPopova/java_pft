package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

/**
 * Created by Алёна on 24.05.2017.
 */
public class ContactDeletionTests extends TestBase {


  @Test
  public void testDelectionContact(){
    app.getNavigationHelper().goToContactPage();
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteContactModification();
    app.getContactHelper().okeyTest();
    app.getNavigationHelper().goToContactPage();

   }
}
