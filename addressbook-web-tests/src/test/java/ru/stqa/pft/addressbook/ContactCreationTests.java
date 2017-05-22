package ru.stqa.pft.addressbook;

import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.*;

public class ContactCreationTests extends TestBase{


    @Test
    public void testContactCreation() {

        initContactCreation();
        fillContactForm(new ContactData("Alena", "Popova", "Lenina 49", "fr@ya.ru"));
        submitContactCreation();
        returnToContactPage();
    }


}
