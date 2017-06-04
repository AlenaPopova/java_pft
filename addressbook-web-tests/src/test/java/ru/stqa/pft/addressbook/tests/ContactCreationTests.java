package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase{


    @Test
    public void testContactCreation() {

        app.getNavigationHelper().goToContactPage();
        int before = app.getContactHelper().getContactCount();
        app.getContactHelper().createContact(new ContactData("Mike", "Jordan", "2 Street", "mj@ya.ru", "test1"),true);
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after, before+1);
    }


}
