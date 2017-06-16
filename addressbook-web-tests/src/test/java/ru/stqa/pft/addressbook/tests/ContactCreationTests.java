package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {
    @DataProvider
    public Iterator<Object[]> validGroups() throws IOException {
        List<Object[]> list = new ArrayList<Object[]>();
       // list.add(new Object[]{new ContactData().withName("Paris").withSurname("Hilton")});
       // list.add(new Object[]{new ContactData().withName("Aria").withSurname("Stark")});
       // list.add(new Object[]{new ContactData().withName("Elton").withSurname("John")});
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resourses/contacts.csv")));
        String line = reader.readLine();
        while (line !=null) {
            String[] split = line.split(";");
            list.add(new Object[] {new ContactData().withName(split[0]).withSurname(split[1])});
            line = reader.readLine();
        }
        return list.iterator();
    }

    // Чтобы в результатах теста в консоли отображались нужные поля, необходимо изменить метод public String toString() в ContactData
    @Test(dataProvider = "validGroups")
    public void testContactCreation(ContactData contact) {
        app.goTo().contactPage();
        Contacts before = app.contact().allContact();
        File photo = new File("src/test/resourses/cat.png");
        app.contact().create(contact, true);
        assertThat(app.contact().getContactCount(), equalTo(before.size() + 1));
        Contacts after = app.contact().allContact();
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }

    @Test
    public void testCurrentDir() {
        File currentDir = new File(".");
        System.out.println(currentDir.getAbsolutePath());
        File photo = new File("src/test/resourses/cat.png");
        System.out.println(photo.getAbsolutePath());
        System.out.println(photo.exists());
    }
}
