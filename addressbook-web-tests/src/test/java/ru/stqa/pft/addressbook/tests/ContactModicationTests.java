package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

/**
 * Created by Алёна on 24.05.2017.
 */
public class ContactModicationTests extends TestBase{
  @Test
  public void testContactModification(){

    app.getNavigationHelper().goToContactPage();


    if (! app.getContactHelper().isThereAContact()){
      app.getContactHelper().createContact(new ContactData("Mike", "Jordan", "2 Street", "mj@ya.ru", "test1"),true);
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    //int before = app.getContactHelper().getContactCount();
    app.getContactHelper().selectContact(before.size()-3);
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("Harry", "Potter", "1/4 Street", "ew@ya.ru", null),false);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToContactPage();
    List<ContactData> after = app.getContactHelper().getContactList();
    //int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after.size(), before.size());
  }
}




