package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase{


    @Test
    public void testContactCreation() {

        app.initContactCreation();
        app.fillContactForm(new ContactData("Alena", "Popova", "Lenina 49", "fr@ya.ru"));
        app.submitContactCreation();
        app.returnToContactPage();
    }


}
