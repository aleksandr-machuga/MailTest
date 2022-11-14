package by.mailru.pageObject;

import by.mailru.entity.User;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EmailPage extends BasePage {

    @FindBy(css = "div[data-type='to'] input")
    private WebElement recipient;

    @FindBy(css = "input[name='Subject'")
    private WebElement topicInput;

    @FindBy(css = "div[role='textbox']")
    private WebElement bodyInput;

    @FindBy(css = "button[data-test-id='send']")
    private WebElement sendButton;

    public ConfirmationPage sendLetter(User user, String topic, String body) {
        waitForElementDisplayed(recipient).clear();
        recipient.sendKeys(user.getEmail());
        waitForElementDisplayed(topicInput).clear();
        topicInput.sendKeys(topic);
        waitForElementDisplayed(bodyInput).clear();
        bodyInput.sendKeys(body);
        waitForElementClickable(sendButton).click();
        waitForElementNotDisplayed(sendButton);
        return new ConfirmationPage();
    }
}
