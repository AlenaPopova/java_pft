package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {




    @Test
    public void testContactCreation() {

        app.goTo().contactPage();

        List<ContactData> before = app.contact().list();
        //System.out.println(before.get(before.size()+1).getId());
        ContactData contact = new ContactData().withName("Harry").withSurname("Potter").withAdres("1/2 Street");
        app.contact().create(contact, true);
        List<ContactData> after = app.contact().list();
       // System.out.println(after.get(after.size()).getId());
        Assert.assertEquals(after.size(), before.size() + 1);

// поиск элемента с максимальным идентификатором
      // int max = 0;
      //  for (ContactData g : after) {
      //    if (g.getId() > max) {
       //        max = g.getId();
      //     }
      // }

        //int max1 = after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId();
        //contact.setId(max1);

        before.add(contact);

        //сортировка
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId()) ;
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
       // MatcherAssert.assertThat(after, CoreMatchers.equalTo(before));
    }
}
