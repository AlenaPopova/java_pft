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
public class DeletionContactFromGroup extends TestBase {

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

        if (app.db().contactInGroup().size() == 0) {
            app.goTo().contactPage();
            Groups group = app.db().groups();
            ContactData modifiedContact = app.db().contactInGroup().iterator().next();
            GroupData addedGroup = group.iterator().next();
            app.contact().selectContactById(modifiedContact.getId());
            app.contact().addContactToGroup(addedGroup.getId());
        }
    }

    @Test
    public void testDeletionContactFromGroup() {
        app.goTo().contactPage();
        Groups group = app.db().groups();
        Contacts before = app.db().contacts();
        app.goTo().contactPage();
        for (GroupData groups : group) {
            app.contact().selectDeletedGroupFromList(groups);
        }
        Contacts contacts = app.db().contacts();
        ContactData initContact = app.db().contactById(contacts.iterator().next().getId());
        app.goTo().contactPage();
        app.contact().deleteContactFromGroup(initContact);
        Contacts after = app.db().contacts();
        ContactData contactFromDb = app.db().contactById(initContact.getId());
        MatcherAssert.assertThat(after, CoreMatchers.equalTo(before.withOut(initContact).withAdded(contactFromDb)));
    }


}
