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
    if (app.group().list().size() == 0){
      app.contact().create(new ContactData("Mike", "Jordan", "2 Street", "mj@ya.ru", "test1"),true);
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
