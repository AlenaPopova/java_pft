package ru.stqa.pft.addressbook.tests;


import org.apache.commons.lang3.RandomStringUtils;
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
    ContactData testContact = new ContactData();
    GroupData testGroup = new GroupData();



    @BeforeMethod
    public void ensurePreconditions() {
        Groups group = app.db().groups();

        Groups allGroups = app.db().groups();
        if (allGroups.size() == 0){
            GroupData newGroup = new GroupData().withName(RandomStringUtils.randomAlphabetic(10));
            app.goTo().groupPage();;
            app.group().create(newGroup);
            allGroups = app.db().groups();
        }
        Contacts contacts = app.db().contacts();
        if (contacts.size() != 0) {
            try {
                testContact = contacts.stream().filter((c) -> c.getGroups().size() != 0).iterator().next();
            } catch (Exception ex) {
                testContact = null;
            }
            if (testContact == null) {
                ContactData contact = contacts.iterator().next();
                GroupData randomGroup = group.iterator().next();
                app.contact().addition(contact, randomGroup);
                contacts = app.db().contacts();
                testContact = contacts.stream().filter((c) -> c.getGroups().size() != 0).iterator().next();
            }
        } else {
            ContactData newContact = new ContactData().withName(RandomStringUtils.randomAlphabetic(10)).withSurname(RandomStringUtils.randomAlphabetic(10)).inGroup(allGroups.stream().iterator().next());
            app.goTo().contactPage();
            app.contact().create(new ContactData().withName("Elton").withSurname("John").withAdres("5 Avenue").inGroup(allGroups.stream().iterator().next()));
            testContact = app.db().contacts().stream().filter((c) -> (c.getGroups().size() != 0)).iterator().next();
        }
        Groups groupsWithContact = testContact.getGroups();
        testGroup = groupsWithContact.stream().iterator().next();

    }

    @Test
    public void testDeletionContactFromGroup() {

        ContactData before = app.db().contacts().stream().filter((c) -> (c.getId() == testContact.getId())).iterator().next();
        app.contact().deleteFromGroup(testContact, testGroup);
        ContactData after = app.db().contacts().stream().filter((c) -> (c.getId() == testContact.getId())).iterator().next();
        System.out.println("before: " + before);
        System.out.println("after: " + after);
        org.junit.Assert.assertThat(before, CoreMatchers.equalTo(after.inGroup(testGroup)));
    }

}
