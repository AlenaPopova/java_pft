package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by popovaa on 14.06.2017.
 */
public class ContactEmailTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().contactPage();
        if (app.contact().list().size() == 0){
            app.contact().create(new ContactData().withName("Jessy").withSurname("J").withAdres("NY Street")
                    .withEmail("jj@ya.com").withHomePhone("554144")
                    .withMobilePhone("89214562233").withWorkPhone("632336"),true);
        }
    }

    @Test

    public void testContactEmail(){
        app.goTo().contactPage();
        ContactData contact = app.contact().allContact().iterator().next(); // загружаем множество контактов
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    }
}
