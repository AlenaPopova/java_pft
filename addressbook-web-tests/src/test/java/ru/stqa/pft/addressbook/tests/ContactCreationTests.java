package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ContactCreationTests extends TestBase {




    @Test
    public void testContactCreation() {

        app.goTo().contactPage();
        Set<ContactData> before = app.contact().allContact();
        //System.out.println(before.get(before.size()+1).getId());
        ContactData contact = new ContactData().withName("Harry").withSurname("Potter").withAdres("1/2 Street");
        app.contact().create(contact, true);
       Set<ContactData> after = app.contact().allContact();
       // System.out.println(after.get(after.size()).getId());
        Assert.assertEquals(after.size(), before.size() + 1);

        contact.withId(after.stream().mapToInt((c)->c.getId()).max().getAsInt());
        before.add(contact);


        Assert.assertEquals(before, after);
       // MatcherAssert.assertThat(after, CoreMatchers.equalTo(before));
    }
}
