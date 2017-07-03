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
        Groups group = app.db().groups();
        if (app.db().contacts().size() == 0) {
            if (app.db().contacts().size() == 0) {
                app.contact().create(new ContactData().withName("Elton").withSurname("John").withAdres("5 Avenue"), true);
            } else {
                ContactData contact = app.db().contacts().iterator().next();
                if (contact.getGroups().size() == 0) {
                    contact.inGroup(group.iterator().next());
                }
            }
        }
    }

    @Test
    public void testDeletionContactFromGroup() {
        ContactData contact =app.db().contacts().iterator().next();
        Groups before = contact.getGroups();
        GroupData deletedGroup = before.iterator().next();
        app.contact().deleteFromGroup(contact,deletedGroup);
        Groups after=app.db().contactById(contact.getId()).getGroups();
        MatcherAssert.assertThat(after,CoreMatchers.equalTo(before.withOut(deletedGroup)));
    }


}
