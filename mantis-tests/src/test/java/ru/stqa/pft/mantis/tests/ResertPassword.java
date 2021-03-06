package ru.stqa.pft.mantis.tests;

import org.junit.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

/**
 * Created by popovaa on 06.07.2017.
 */
public class ResertPassword extends TestBase {

    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void testResetPassword() throws IOException, MessagingException {

        app.goTo().login("administrator", "root");
        app.goTo().manage(); //нажатие на "Управление"
        app.goTo().usersManage(); //"Нажатие на Управление пользователями"
        //UserData user = app.db().users().iterator().next();// Выбор пользователя из базы

        String user=app.db().getUserName();// Выбор пользователя из базы !!! рабочий вариант!!!!!!!
        app.resetPassword().select(user);
        //String username="user1499257457781";
        //app.resetPassword().select(user.getUsername());
        app.resetPassword().resert(); // нажатие на "Сбросить пароль"
        List<MailMessage> mailMessages = app.mail().waitForMail(1, 100000);
        //String email = user+"@localhost.localdomain";
        String email = user+"@localhost.localdomain";
        String confirmationLink = findConfirmationLink(mailMessages, email); // получаем ссылку для смены пароля
        String newPassword = String.valueOf(System.currentTimeMillis());
        app.resetPassword().finish(confirmationLink, newPassword);
       Assert.assertTrue(app.newSession().login(user, newPassword));
       //Assert.assertTrue(app.newSession().login(user.getUsername(), newPassword));
    }

    protected String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }


    @AfterMethod
    public void stopMailServer() {
        app.mail().stop();
    }
}
