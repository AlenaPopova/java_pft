package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

/**
 * Created by popovaa on 03.07.2017.
 */
public class ContactAdditionToGroup extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.group().all().size() == 0) {
            app.group().create(new GroupData().withName("test4"));
        }

        app.goTo().contactPage();
        if (app.db().contacts().size() == 0) {
            app.contact().create(new ContactData().withName("Elton").withSurname("John").withAdres("5 Avenue"), true);
        }
    }

    @Test
    public void testAddContactToGroup() {
        app.goTo().contactPage();
        if (app.db().contactNotInGroup().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test group"));
            app.goTo().contactPage();
        }
        Contacts before = app.db().contacts();
        app.goTo().contactPage();
        Groups group = app.db().groups();
        ContactData modifiedContact = app.db().contactNotInGroup().iterator().next();
        GroupData addedGroup = group.iterator().next();
        app.contact().selectContact(modifiedContact.getId());
        app.contact().addContactToGroup(addedGroup.getId());
        Contacts after = app.db().contacts();
        MatcherAssert.assertThat(after, CoreMatchers.equalTo(before.withOut(modifiedContact).withAdded(modifiedContact.inGroup(addedGroup))));
    }
}
