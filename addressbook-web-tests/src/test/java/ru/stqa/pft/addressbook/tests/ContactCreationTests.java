package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactCreationTests extends TestBase{


    @Test
    public void testContactCreation() {

        app.getNavigationHelper().goToContactPage();

        List<ContactData> before = app.getContactHelper().getContactList();

        //int before = app.getContactHelper().getContactCount();
        app.getContactHelper().createContact(new ContactData("Mike", "Jordan", "2 Street", "mj@ya.ru", "test1"),true);

        List<ContactData> after = app.getContactHelper().getContactList();

        //int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after.size(), before.size()+1);
    }


}
