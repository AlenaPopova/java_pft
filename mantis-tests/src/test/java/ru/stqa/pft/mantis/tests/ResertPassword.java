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
        UserData user = app.db().users().iterator().next();// Выбор пользователя из базы
        app.resetPassword().select(user.getUsername());
        app.resetPassword().resert(); // нажатие на "Сбросить пароль"
        List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
        String email = user+"@localhost.localdomain";
        String confirmationLink = findConfirmationLink(mailMessages, email);
        String newPassword = String.valueOf(System.currentTimeMillis());
        app.resetPassword().finish(confirmationLink, newPassword);
        Assert.assertTrue(app.newSession().login(user.getUsername(), newPassword));
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
