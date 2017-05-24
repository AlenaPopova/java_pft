package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by Алёна on 24.05.2017.
 */
public class ContactModicationTests extends TestBase{
  @Test
  public void testContactModification(){
    app.getContactHelper().goToContactPage();
    app.getContactHelper().selectContact();
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("Elena", "Popova", "Lenina 49", "fr@ya.ru"));
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToContactPage();
  }
}



