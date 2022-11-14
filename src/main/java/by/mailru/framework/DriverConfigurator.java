package by.mailru.framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;
import static java.time.Duration.ofSeconds;

public class DriverConfigurator {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void setDriver() {
        if (driver.get() == null) {
            chromedriver().setup();
            driver.set(new ChromeDriver());
            driver.get().manage().window().maximize();
            driver.get().manage().timeouts().pageLoadTimeout(ofSeconds(15));
            driver.get().manage().timeouts().implicitlyWait(ofSeconds(15));
        }
    }

    public static WebDriver getWebDriver() {
        return driver.get();
    }

    public static void closeDriver() {
        driver.get().quit();
        driver.remove();
    }
}
