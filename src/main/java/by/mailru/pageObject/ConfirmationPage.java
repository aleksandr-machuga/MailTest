package by.mailru.pageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ConfirmationPage extends BasePage {
    @FindBy(css = "span[title='Закрыть']")
    private WebElement closeButton;

    public ConfirmationPage() {
        super();
    }

    public MailBoxPage closePopup() {
        waitForElementClickable(closeButton).click();
        waitForElementNotDisplayed(closeButton);
        return new MailBoxPage();
    }
}
