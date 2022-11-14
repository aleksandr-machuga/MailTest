package by.mailru.test;

import by.mailru.framework.DriverConfigurator;
import by.mailru.pageObject.EmailPage;
import by.mailru.pageObject.HomePage;
import by.mailru.pageObject.MailBoxPage;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static by.mailru.enums.TestUsers.SOME_TEST;
import static org.assertj.core.api.Assertions.assertThat;

public class MailTest {

    public static final String LOGIN_BUTTON_IS_NOT_DISPLAYED_MESSAGE = "Login button is not displayed";
    public static final String EMAIL_FIELD_IS_NOT_DISPLAYED_MESSAGE = "Email field is not displayed";
    public static final String EMAIL_NOT_FOUND_MESSAGE = "Email not found";
    private HomePage homePage;
    private MailBoxPage mailBoxPage;
    private EmailPage emailPage;

    @BeforeTest
    public void setupDriver() {
        DriverConfigurator.setDriver();
        homePage = new HomePage();
        homePage.open();
        mailBoxPage = new MailBoxPage();
        emailPage = new EmailPage();
    }

    @Test
    public void sendEmail() {
        String topic = RandomStringUtils.randomAlphabetic(5);
        String body = RandomStringUtils.randomAlphabetic(15);
        assertThat(homePage.isLoginButtonDisplayed())
                .as(LOGIN_BUTTON_IS_NOT_DISPLAYED_MESSAGE)
                .isTrue();
        homePage.clickLoginButton()
                .loginAs(SOME_TEST.getUser());
        assertThat(mailBoxPage.isEmailUserDisplayed())
                .as(EMAIL_FIELD_IS_NOT_DISPLAYED_MESSAGE)
                .isTrue();
        String actualTopic = mailBoxPage.clickSendLetterButton()
                .sendLetter(SOME_TEST.getUser(), topic, body)
                .closePopup()
                .clickEmailToMyselfGroup()
                .getEmailTopic(0);
        assertThat(actualTopic).as(EMAIL_NOT_FOUND_MESSAGE).isEqualTo(topic);
    }

    @AfterTest
    public void closeDriver() {
        DriverConfigurator.closeDriver();
    }
}
