package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by popovaa on 06.07.2017.
 */
public class NavigationHelper extends HelperBase {

    private WebDriver wd;
    private WebDriverWait wait;

    public NavigationHelper(ApplicationManager app) {
        super(app);
        wd = app.getDriver();
        wait = new WebDriverWait(wd, 5);
    }


    public void goToHomePage() {
        if (isElementPresent(By.id("maintable"))) {
            return;
        }
        click(By.linkText("home page"));
    }

    public void manage() {
        //wd.findElement(By.cssSelector("button#menu-toggler")).click(); - если в методе login нет строки wd.manage().window().maximize();,
        // тогда браузер открывается не в полноэкранном размере и приходится выбирать пункт "управление" из скрытого списка
        //click(By.partialLinkText("управление"));
        click(By.xpath("//li[6]/a/span"));
    }

    public void login(String username, String password) {
        wd.manage().window().maximize();
        wd.get("http://localhost/mantisbt-2.5.1/login_page.php");
        By user = By.cssSelector("input[name=username]");
        By pass = By.cssSelector("input[name=password]");
        wd.findElement(user);
        click(user);
        type(user,"administrator");
        wd.findElement(By.cssSelector("input[type=submit]")).click();
        wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wd.findElement(pass);
        click(pass);
        type(pass,"root");
        wd.findElement(By.cssSelector("input[type=submit]")).click();

    }

    public void usersManage() {
        click(By.linkText("Manage Users"));

    }


}
