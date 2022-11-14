package by.mailru.pageObject;

import by.mailru.entity.User;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
    private static final String MAIL_URL = "https://mail.ru/";

    @FindBy(css = "button[data-testid='enter-mail-primary']")
    private WebElement loginButton;

    @FindBy(css = "iframe[src*='login']")
    private WebElement loginPopup;

    @FindBy(css = "input[name*='username']")
    private WebElement loginInput;

    @FindBy(css = "button[data-test-id='next-button']")
    private WebElement passwordButton;

    @FindBy(css = "input[name='password']")
    private WebElement passwordInput;

    @FindBy(css = "button[data-test-id='submit-button']")
    private WebElement passwordInputButton;

    public HomePage() {
        super();
    }

    public HomePage open() {
        driver.get(MAIL_URL);
        return this;
    }

    public boolean isLoginButtonDisplayed() {
        return isElementDisplayed(loginButton);
    }

    public HomePage clickLoginButton() {
        waitForElementClickable(loginButton).click();
        waitForElementDisplayed(loginPopup);
        return this;
    }

    public MailBoxPage loginAs(User user) {
        driver.switchTo().frame(loginPopup);
        loginInput.sendKeys(user.getLogin());
        waitForElementClickable(passwordButton).click();
        passwordInput.sendKeys(user.getPassword());
        waitForElementClickable(passwordInputButton).click();
        driver.switchTo().defaultContent();
        return new MailBoxPage();
    }
}
