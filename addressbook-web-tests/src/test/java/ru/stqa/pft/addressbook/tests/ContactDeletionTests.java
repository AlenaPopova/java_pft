package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Алёна on 24.05.2017.
 */
public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().contactPage();
    if (app.contact().list().size() == 0){
      app.contact().create(new ContactData().withName("Harry").withSurname("Potter").withAdres("1/2 Street"),true);
    }
  }

  @Test
  public void testDeletionContact(){
    Contacts before = app.contact().allContact();
    ContactData deleteContact = before.iterator().next();
    app.contact().delete(deleteContact);
    Contacts after = app.contact().allContact();
    Assert.assertEquals(after.size(), before.size()-1);

    assertThat(after, CoreMatchers.equalTo(before.withOut(deleteContact)));

   }


}
