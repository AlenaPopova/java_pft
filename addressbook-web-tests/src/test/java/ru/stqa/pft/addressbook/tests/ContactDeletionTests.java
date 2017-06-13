package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;
import java.util.Set;

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
    Set<ContactData> before = app.contact().allContact();
    ContactData deleteContact = before.iterator().next();
    app.contact().delete(deleteContact);

    Set<ContactData> after = app.contact().allContact();
    Assert.assertEquals(after.size(), before.size()-1);
    before.remove(deleteContact);
    Assert.assertEquals(before, after);
   }


}
