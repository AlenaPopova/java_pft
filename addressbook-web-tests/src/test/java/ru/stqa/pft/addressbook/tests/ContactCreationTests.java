package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase{


    @Test
    public void testContactCreation() {

        app.getContactHelper().initContactCreation();
        app.getContactHelper().fillContactForm(new ContactData("Mike", "Jordan", "2 Street", "mj@ya.ru"));
        app.getContactHelper().submitContactCreation();
        app.getContactHelper().returnToContactPage();
    }


}
