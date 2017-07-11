package ru.stqa.pft.addressbook.appmanager;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static ru.stqa.pft.addressbook.tests.TestBase.app;

/**
 * Created by Алёна on 23.05.2017.
 */
public class ContactHelper extends HelperBase {
    // private FirefoxDriver wd;


    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void returnToContactPage() {
        click(By.linkText("home"));
    }

    public void submitContactCreation() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void click(By locator) {
        wd.findElement(locator).click();
    }

    public void fillContactForm(ContactData contactData, boolean creation) {

        type(By.name("firstname"), contactData.getName());
        type(By.name("lastname"), contactData.getSurname());
        type(By.name("address"), contactData.getAddress());
        type(By.name("email"), contactData.getEmail());
        //type(By.name("new_group"), contactData.getGroup());
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("work"), contactData.getWorkPhone());
        attach(By.name("photo"), contactData.getPhoto());

        if (creation) {
            if (contactData.getGroups().size() > 0) {
                Assert.assertTrue(contactData.getGroups().size() == 1);
                new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
            }
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void type(By locator, String text) {
        click(locator);
        if (text != null) {
            String existingText = wd.findElement(locator).getAttribute("value");
            if (!text.equals(existingText)) {
                wd.findElement(locator).clear();
                wd.findElement(locator).sendKeys(text);
            }
        }
    }

    public void attach(By locator, File file) {
        if (file != null) {
            wd.findElement(locator).sendKeys(file.getAbsolutePath()); // преобразование относительного пути к файлу в абсолютный используется getAbsolutePath()
        }
    }

    public void initContactCreation() {
        click(By.linkText("add new"));
    }

    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();

    }

    private void SelectedGroupById(String id) {
        new Select(wd.findElement(By.name("group"))).selectByValue(id);
    }

    public void initContactModificationById(int id) {

        // click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
        //wd.findElements(By.cssSelector("img[alt=\"Edit\"]")).get(index).click();
        wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
    }

    public void submitContactModification() {
        click(By.xpath("//div[@id='content']/form[1]/input[22]"));
    }


    public void deleteContactModification() {
        click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    }

    public void okeyTest() {
        wd.switchTo().alert().accept();
    }

    public void create(ContactData contact, boolean creation) {
        initContactCreation();
        fillContactForm(contact, true);
        submitContactCreation();
        returnToContactPage();
    }

    public void modify(ContactData contact) {
        selectContactById(contact.getId());
        initContactModificationById(contact.getId());
        fillContactForm(contact, false);
        submitContactModification();
        returnToContactPage();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteContactModification();
        okeyTest();
        returnToContactPage();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public List<ContactData> list() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            String name = element.findElement(By.xpath(".//td[3]")).getText(); //обращаемся к строке td к полю по его индексу
            String surname = element.findElement(By.xpath(".//td[2]")).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
            ContactData contact = new ContactData().withId(id).withName(name).withSurname(surname);
            contacts.add(contact);
        }
        return contacts;
    }

    public Contacts allContact() {
        Contacts contacts = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            String name = element.findElement(By.xpath(".//td[3]")).getText(); //обращаемся к строке td к полю по его индексу
            String surname = element.findElement(By.xpath(".//td[2]")).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
            String allPhones = element.findElement(By.xpath(".//td[6]")).getText();
            // String[] phones = allPhones.split("\n");
            String allEmails = element.findElement(By.xpath(".//td[5]")).getText();
            //String[] emails = allEmails.split("\n");

            String allAddress = element.findElement(By.xpath(".//td[4]")).getText();
            ContactData contact = new ContactData().withId(id).withName(name).withSurname(surname)
                    .withAllPhones(allPhones)
                    .withAllEmails(allEmails)
                    .withAdres(allAddress);

            contacts.add(contact);
        }
        return contacts;
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String homephone = wd.findElement(By.name("home")).getAttribute("value");
        String mobilephone = wd.findElement(By.name("mobile")).getAttribute("value");
        String workphone = wd.findElement(By.name("work")).getAttribute("value");
        String email1 = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getText();
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withName(firstname).withSurname(lastname)
                .withAdres(address)
                .withHomePhone(homephone).withMobilePhone(mobilephone).withWorkPhone(workphone)
                .withEmail(email1).withEmail2(email2).withEmail3(email3);
    }


    private Contacts contactCache = null;

    public void deleteFromGroup(ContactData contact, GroupData group) {
        app.goTo().contactPage();
        SelectedGroupById(String.valueOf(group.getId()));
        selectContactById(contact.getId());
        click(By.name("remove"));
        app.goTo().contactPage();
        SelectedGroupById("");
        contactCache = null;
        app.goTo().contactPage();
    }


    public void addition(ContactData contact, GroupData group) {
        app.goTo().contactPage();
        selectContactById(contact.getId());
        addContactToGroup(group);
        contactCache = null;
        app.goTo().contactPage();
    }

    private void addContactToGroup(GroupData group) {
        new Select(wd.findElement(By.name("to_group"))).selectByValue(String.valueOf(group.getId()));
        click(By.xpath("//div[@id='content']/form[2]/div[4]/input"));
    }

    public GroupData getGroupToAddition(Groups groups, ContactData contact) {
        Groups beforeAdditionGroups = contact.getGroups();
        for (GroupData group : groups) {
            if (!beforeAdditionGroups.contains(group)) {
                return group;
            }
        }
        return null;
    }

    public void addContactToGroup(int id) {
        wd.findElement(By.xpath("//select[@name='group']//option[@value='" + "" + "']")).click();
        click(By.cssSelector("input[name='add']"));

    }

}


