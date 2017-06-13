package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

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
        type(By.name("address"), contactData.getAdres());
        type(By.name("email"), contactData.getMail());

        //if (creation){
        //new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        //} else {
        //Assert.assertFalse(isElementPresent(By.name("new_group")));
        // }
    }


    public void type(By locator, String text) {
        click(locator);
        wd.findElement(locator).clear();
        wd.findElement(locator).sendKeys(text);
    }

    public void initContactCreation() {
        click(By.linkText("add new"));
    }

    public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();

    }

    public void initContactModification(int index) {

        // click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
        wd.findElements(By.cssSelector("img[alt=\"Edit\"]")).get(index).click();
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

    public void modify(int index, ContactData contact) {
        selectContact(index); // выбираем последний элемент в списке
        initContactModification(index);
        fillContactForm(contact, false);
        submitContactModification();
        returnToContactPage();
    }

    public void delete(int index) {
        selectContact(index);
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
            ContactData contact = new ContactData(id, name, surname, null, null, null);
            contacts.add(contact);
        }
        return contacts;
    }
}
