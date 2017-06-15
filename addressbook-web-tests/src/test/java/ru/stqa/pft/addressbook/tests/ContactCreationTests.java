package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {


    @Test
    public void testContactCreation() {

        app.goTo().contactPage();
        Contacts before = app.contact().allContact();
        File photo = new File("src/test/resourses/cat.png");
        ContactData contact = new ContactData().withName("Harry").withSurname("Potter").withAdres("1/2 Street").withPhoto(photo);
        app.contact().create(contact, true);
        assertThat(app.contact().getContactCount(), equalTo(before.size() + 1));
        Contacts after = app.contact().allContact();
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }

    @Test
    public void testCurrentDir(){
        File currentDir = new File(".");
        System.out.println(currentDir.getAbsolutePath());
        File photo = new File("src/test/resourses/cat.png");
        System.out.println(photo.getAbsolutePath());
        System.out.println(photo.exists());
    }
}
