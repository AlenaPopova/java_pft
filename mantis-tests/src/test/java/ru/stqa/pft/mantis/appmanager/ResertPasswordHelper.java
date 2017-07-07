package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.mantis.model.UserData;

/**
 * Created by popovaa on 06.07.2017.
 */
public class ResertPasswordHelper extends HelperBase {
    private ApplicationManager app;
    private WebDriver wd;

    public ResertPasswordHelper(ApplicationManager app) {
        super(app);
        wd = app.getDriver();
    }

    public void select(String user){
        click(By.linkText(user));
       // click(By.linkText("user1499257457781"));
    }


    public void resert() {
        click(By.xpath("//input[@value='Сбросить пароль']"));

    }

    public void finish(String confirmationLink, String password) {
        wd.get(confirmationLink);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        //click(By.xpath("//button[@type='submit']"));
        click(By.cssSelector("input[type=submit]"));

    }
}
