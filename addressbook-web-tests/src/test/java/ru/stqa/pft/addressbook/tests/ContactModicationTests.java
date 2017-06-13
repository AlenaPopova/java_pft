package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

/**
 * Created by Алёна on 24.05.2017.
 */
public class ContactModicationTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().contactPage();
    if (app.group().list().size() == 0){
      app.contact().create(new ContactData("Mike", "Jordan", "2 Street", "mj@ya.ru", "test1"),true);
    }
  }

  @Test
  public void testContactModification(){
    List<ContactData> before = app.contact().list();
    int index = before.size()-1;
    ContactData contact = new ContactData(before.get(index).getId(),"Jess", "Macc", "1/4 Street", "ew@ya.ru", null);
    app.contact().modify(index, contact);
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size());

    before.remove(index);
    before.add(contact);

    //сортировка
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId()) ;
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before,after);
  }




}




