package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {


    @Test
    public void testContactCreation() {

        app.goTo().contactPage();
        Contacts before = app.contact().allContact();
        //System.out.println(before.get(before.size()+1).getId());
        ContactData contact = new ContactData().withName("Harry").withSurname("Potter").withAdres("1/2 Street");
        app.contact().create(contact, true);
        assertThat(app.contact().getContactCount(), equalTo(before.size() + 1));
        Contacts after = app.contact().allContact();
        // System.out.println(after.get(after.size()).getId());
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }
}
