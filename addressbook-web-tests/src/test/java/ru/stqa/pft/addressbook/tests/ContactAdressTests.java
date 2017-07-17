package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by popovaa on 15.06.2017.
 */
public class ContactAdressTests extends TestBase {

    public class ContactEmailTests extends TestBase {
        @BeforeMethod
        public void ensurePreconditions(){
            app.goTo().contactPage();
            if (app.contact().list().size() == 0){
                app.contact().create(new ContactData().withName("Jessy").withSurname("J").withAdres("NY Street")
                        .withEmail("jj@ya.com").withHomePhone("554144")
                        .withMobilePhone("89214562233").withWorkPhone("632336"));
            }
        }

    }

    @Test
    public void contactAdressTests(){
        app.goTo().contactPage();
        ContactData contact = app.contact().allContact().iterator().next(); // загружаем множество контактов
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
        assertThat(contact.getAllAdress(), equalTo(contactInfoFromEditForm.getAllAdress()));
    }

}

