package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

/**
 * Created by Алёна on 24.05.2017.
 */
public class ContactDeletionTests extends TestBase {


  @Test
  public void testDelectionContact(){

    app.getNavigationHelper().goToContactPage();
    int before = app.getContactHelper().getContactCount();
    if (! app.getContactHelper().isThereAContact()){
      app.getContactHelper().createContact(new ContactData("Mike", "Jordan", "2 Street", "mj@ya.ru", null),true);
    }
    app.getContactHelper().selectContact(0);
    app.getContactHelper().deleteContactModification();
    app.getContactHelper().okeyTest();
    app.getNavigationHelper().goToContactPage();
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before-1);
   }
}
