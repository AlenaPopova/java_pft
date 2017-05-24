package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by Алёна on 24.05.2017.
 */
public class ContactDeletionGroup extends TestBase {

  @Test
  public void testDelectionContact(){
    app.getContactHelper().goToContactPage();
    app.getContactHelper().selectContact();
    app.getContactHelper().initContactModification();
    app.getContactHelper().deleteContactModification();
    app.getContactHelper().goToContactPage();
  }
}
