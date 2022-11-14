package by.mailru.pageObject;

import by.mailru.framework.DriverConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public abstract class BasePage {
    public static final int WAIT_TIMEOUT = 5;
    protected WebDriver driver;

    public BasePage() {
        this.driver = DriverConfigurator.getWebDriver();
        PageFactory.initElements(driver, this);
    }

    public boolean isElementDisplayed(WebElement webElement) {
        return webElement != null && webElement.isDisplayed();
    }

    protected WebElement waitForElementClickable(WebElement webElement) {
        Wait<WebDriver> wait = new WebDriverWait(driver, ofSeconds(WAIT_TIMEOUT));
        return wait.until(elementToBeClickable(webElement));
    }

    protected WebElement waitForElementDisplayed(WebElement webElement) {
        Wait<WebDriver> wait = new WebDriverWait(driver, ofSeconds(WAIT_TIMEOUT));
        return wait.until(visibilityOf(webElement));
    }

    protected List<WebElement> waitForElementsDisplayed(List<WebElement> webElements) {
        Wait<WebDriver> wait = new WebDriverWait(driver, ofSeconds(WAIT_TIMEOUT));
        return wait.until(visibilityOfAllElements(webElements));
    }

    protected boolean waitForElementNotDisplayed(WebElement webElement) {
        Wait<WebDriver> wait = new WebDriverWait(driver, ofSeconds(WAIT_TIMEOUT));
        return wait.until(ExpectedConditions.invisibilityOf(webElement));
    }


}
