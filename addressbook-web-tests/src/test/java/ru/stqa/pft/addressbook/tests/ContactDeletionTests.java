package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

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
    List<ContactData> before = app.contact().list();
    int index = before.size()-1;
    app.contact().delete(index);

    List<ContactData> after = app.contact().list();

    Assert.assertEquals(after.size(), index);

    before.remove(index);
    Assert.assertEquals(before, after);
   }


}
