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
        Groups groups = app.db().groups();
        if (app.db().contacts().size() == 0) {
            app.contact().create(new ContactData().withName("Elton").withSurname("John").withAdres("5 Avenue"), true);
        }
    }

    @Test
    public void testAddContactToGroup() {
        int contactId = 0;
        boolean completed = false;
        Groups beforeAdditionGroups = null;
        Groups beforeAddedGroups = null;
        Groups existedGroups = app.db().groups();
        Contacts contacts = app.db().contacts();

        for (ContactData editedContact : contacts) {
            beforeAdditionGroups = editedContact.getGroups();
            GroupData newGroup = app.contact().getGroupToAddition(existedGroups, editedContact);
            if (newGroup != null) {
                app.contact().addition(editedContact, newGroup);
                contactId = editedContact.getId();
                beforeAddedGroups = beforeAdditionGroups.withAdded(newGroup);
                completed = true;
                break;
            }
        }
        if (!completed) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test4").withHeader("test4").withFooter("test4"));
            Groups extendedGroups = app.db().groups();
            GroupData lastAddedGroup = extendedGroups.stream()
                    .max((g1, g2) -> Integer.compare(g1.getId(), g2.getId())).get();
            ContactData contact = contacts.iterator().next();
            contactId = contact.getId();
            app.contact().addition(contact, lastAddedGroup);
            beforeAddedGroups = beforeAdditionGroups.withAdded(lastAddedGroup);
        }
        Groups groupAfter = app.db().contactById(contactId).getGroups();
        MatcherAssert.assertThat(groupAfter, CoreMatchers.equalTo(beforeAddedGroups));
    }
}



