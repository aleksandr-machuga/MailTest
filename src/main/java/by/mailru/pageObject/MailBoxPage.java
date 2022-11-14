package by.mailru.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MailBoxPage extends BasePage {
    public static final String EMAIL_TOPIC = "span[class*='subject'] span";

    @FindBy(css = "span[class*='ph-project__user-name']")
    private WebElement emailField;

    @FindBy(css = "a[href*='/compose/']")
    private WebElement sendLetterButton;

    @FindBy(css = "div[class*='ph-project-promo-close-icon'")
    private WebElement closeAlertButton;

    @FindBy(css = "span[class*='tomyself']")
    private WebElement emailToMyselfGroup;

    @FindBy(xpath = "//div[@class='metathread-footer']/../../preceding-sibling::a")
    private List<WebElement> emailsToMyselfList;


    public MailBoxPage() {
        super();
    }

    public boolean isEmailUserDisplayed() {
        return isElementDisplayed(emailField);
    }

    public EmailPage clickSendLetterButton() {
        waitForElementClickable(closeAlertButton).click();
        waitForElementClickable(sendLetterButton).click();
        return new EmailPage();
    }

    public MailBoxPage clickEmailToMyselfGroup() {
        waitForElementClickable(emailToMyselfGroup).click();
        waitForElementsDisplayed(emailsToMyselfList);
        return this;
    }

    public String getEmailTopic(int index) {
        return waitForElementsDisplayed(emailsToMyselfList)
                .get(index).findElement(By.cssSelector(EMAIL_TOPIC)).getText();
    }
}
