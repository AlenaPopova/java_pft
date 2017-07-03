package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Алёна on 24.05.2017.
 */
public class ContactModificationTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions(){
    if (app.db().contacts().size()==0){
      app.goTo().contactPage();
      app.contact().create(new ContactData().withName("Harry").withSurname("Potter").withAdres("1/2 Street"),true);
    }
  }

  @Test
  public void testContactModification(){
    Contacts before = app.db().contacts();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().withId(modifiedContact.getId()).withName("Jess").withSurname("Macc").withAdres("1/4 Street").withEmail("ew@ya.ru").withGroup("test1");
    app.goTo().contactPage();
    app.contact().modify(contact);
    assertThat(app.contact().getContactCount(), equalTo(before.size()));
    Contacts after = app.db().contacts();
    assertThat(after, CoreMatchers.equalTo(before.withOut(modifiedContact).withAdded(contact)));
    verifyContactListInUI();
  }




}




