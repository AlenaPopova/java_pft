package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;

public class ContactCreationTests extends TestBase {


    @Test
    public void testContactCreation() {

        app.getNavigationHelper().goToContactPage();

        List<ContactData> before = app.getContactHelper().getContactList();
        //System.out.println(before.get(before.size()+1).getId());
        ContactData contact = new ContactData("Harry", "Potter", "1/4 Street", "ew@ya.ru", "test1");
        app.getContactHelper().createContact(contact, true);
        List<ContactData> after = app.getContactHelper().getContactList();
       // System.out.println(after.get(after.size()).getId());
        Assert.assertEquals(after.size(), before.size() + 1);

// поиск элемента с максимальным идентификатором
      // int max = 0;
      //  for (ContactData g : after) {
      //    if (g.getId() > max) {
       //        max = g.getId();
      //     }
      // }

        int max1 = after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId();
        contact.setId(max1);
        before.add(contact);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));

    }
}
